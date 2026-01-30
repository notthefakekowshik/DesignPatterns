package com.kow.designs.LoadBalancerDesign;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// Enhanced Server interface with connection tracking
interface Server {
    String getAddress();
    boolean isHealthy();
    void handleRequest(Request request);
    int getCurrentConnections();
    double getWeight();
    void incrementConnections();
    void decrementConnections();
}

interface LoadBalancingStrategy {
    Server getNextServer(List<Server> servers);
}

// Enhanced ServerInstance implementation
class ServerInstance implements Server {
    private final String address;
    private final double weight;
    private boolean healthy;
    private final AtomicInteger currentConnections;
    private final Map<String, Long> lastResponseTimes;

    public ServerInstance(String address, double weight) {
        this.address = address;
        this.weight = weight;
        this.healthy = true;
        this.currentConnections = new AtomicInteger(0);
        this.lastResponseTimes = new ConcurrentHashMap<>();
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean isHealthy() {
        return healthy;
    }

    @Override
    public void handleRequest(Request request) {
        try {
            incrementConnections();
            // Simulate request processing
            System.out.println("Server " + address + " handling request: " + request.getId());
            Thread.sleep(new Random().nextInt(100)); // Simulate varying response times
            lastResponseTimes.put(request.getId(), System.currentTimeMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            decrementConnections();
        }
    }

    @Override
    public int getCurrentConnections() {
        return currentConnections.get();
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void incrementConnections() {
        currentConnections.incrementAndGet();
    }

    @Override
    public void decrementConnections() {
        currentConnections.decrementAndGet();
    }

    public void setHealth(boolean healthy) {
        this.healthy = healthy;
    }
}

// Enhanced Request class with session support
class Request {
    private final String id;
    private final String data;
    private final String sessionId;

    public Request(String id, String data, String sessionId) {
        this.id = id;
        this.data = data;
        this.sessionId = sessionId;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getSessionId() {
        return sessionId;
    }
}

// Weighted Round Robin Strategy
class WeightedRoundRobinStrategy implements LoadBalancingStrategy {
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public Server getNextServer(List<Server> servers) {
        if (servers.isEmpty()) {
            return null;
        }

        double totalWeight = servers.stream()
                .mapToDouble(Server::getWeight)
                .sum();

        double random = Math.random() * totalWeight;
        double weightSum = 0;

        for (Server server : servers) {
            weightSum += server.getWeight();
            if (weightSum >= random) {
                return server;
            }
        }

        return servers.get(0);
    }
}

// Least Connections Strategy
class LeastConnectionsStrategy implements LoadBalancingStrategy {
    @Override
    public Server getNextServer(List<Server> servers) {
        if (servers.isEmpty()) {
            return null;
        }

        return servers.stream()
                .min(Comparator.comparingInt(Server::getCurrentConnections))
                .orElse(null);
    }
}

// Session Persistence Strategy
class SessionPersistenceStrategy implements LoadBalancingStrategy {
    private final LoadBalancingStrategy fallbackStrategy;
    private final Map<String, Server> sessionMap = new ConcurrentHashMap<>();

    public SessionPersistenceStrategy(LoadBalancingStrategy fallbackStrategy) {
        this.fallbackStrategy = fallbackStrategy;
    }

    @Override
    public Server getNextServer(List<Server> servers) {
        Request currentRequest = RequestContext.getCurrentRequest();
        if (currentRequest == null || currentRequest.getSessionId() == null) {
            return fallbackStrategy.getNextServer(servers);
        }

        Server existingServer = sessionMap.get(currentRequest.getSessionId());
        if (existingServer != null && servers.contains(existingServer) && existingServer.isHealthy()) {
            return existingServer;
        }

        Server newServer = fallbackStrategy.getNextServer(servers);
        if (newServer != null) {
            sessionMap.put(currentRequest.getSessionId(), newServer);
        }
        return newServer;
    }
}

// Custom Health Checker implementation
class CustomHealthChecker {
    private final LoadBalancer loadBalancer;
    private final ScheduledExecutorService scheduler;
    private final int healthCheckInterval;
    private final int healthCheckTimeout;
    private final int unhealthyThreshold;

    public CustomHealthChecker(LoadBalancer loadBalancer, int healthCheckInterval,
            int healthCheckTimeout, int unhealthyThreshold) {
        this.loadBalancer = loadBalancer;
        this.healthCheckInterval = healthCheckInterval;
        this.healthCheckTimeout = healthCheckTimeout;
        this.unhealthyThreshold = unhealthyThreshold;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startHealthCheck() {
        scheduler.scheduleAtFixedRate(this::checkServers,
                0, healthCheckInterval, TimeUnit.MILLISECONDS);
    }

    private void checkServers() {
        for (Server server : loadBalancer.getServers()) {
            CompletableFuture.supplyAsync(() -> checkServerHealth(server))
                    .completeOnTimeout(false, healthCheckTimeout, TimeUnit.MILLISECONDS)
                    .thenAccept(healthy -> updateServerHealth(server, healthy));
        }
    }

    private boolean checkServerHealth(Server server) {
        return server.isHealthy();
    }

    private void updateServerHealth(Server server, boolean healthy) {
        if (server instanceof ServerInstance) {
            ((ServerInstance) server).setHealth(healthy);
        }
    }

    public void stop() {
        scheduler.shutdown();
    }
}

// Request Context for handling session information
class RequestContext {
    private static final ThreadLocal<Request> currentRequest = new ThreadLocal<>();

    public static void setCurrentRequest(Request request) {
        currentRequest.set(request);
    }

    public static Request getCurrentRequest() {
        return currentRequest.get();
    }

    public static void clear() {
        currentRequest.remove();
    }
}

// Enhanced Load Balancer with all features
class LoadBalancer {
    private final List<Server> servers;
    private final LoadBalancingStrategy strategy;
    private final CustomHealthChecker healthChecker;

    public LoadBalancer(LoadBalancingStrategy strategy,
            int healthCheckInterval,
            int healthCheckTimeout,
            int unhealthyThreshold) {
        this.servers = new CopyOnWriteArrayList<>();
        this.strategy = strategy;
        this.healthChecker = new CustomHealthChecker(this,
                healthCheckInterval, healthCheckTimeout, unhealthyThreshold);
        startHealthCheck();
    }

    public void addServer(Server server) {
        servers.add(server);
    }

    public void removeServer(Server server) {
        servers.remove(server);
    }

    public List<Server> getServers() {
        return Collections.unmodifiableList(servers);
    }

    public void handleRequest(Request request) {
        try {
            RequestContext.setCurrentRequest(request);
            List<Server> healthyServers = getHealthyServers();
            if (healthyServers.isEmpty()) {
                throw new RuntimeException("No healthy servers available");
            }

            Server server = strategy.getNextServer(healthyServers);
            if (server != null) {
                server.handleRequest(request);
            } else {
                throw new RuntimeException("Could not find available server");
            }
        } finally {
            RequestContext.clear();
        }
    }

    private List<Server> getHealthyServers() {
        return servers.stream()
                .filter(Server::isHealthy)
                .collect(Collectors.toList());
    }

    private void startHealthCheck() {
        healthChecker.startHealthCheck();
    }
}

// Example usage
public class LoadBalancerDemo {
    public static void main(String[] args) {
        // Create load balancer with session persistence and least connections strategy
        LoadBalancer loadBalancer = new LoadBalancer(
                new SessionPersistenceStrategy(new LeastConnectionsStrategy()),
                5000, // Health check interval
                2000, // Health check timeout
                3     // Unhealthy threshold
        );

        // Add servers with weights
        loadBalancer.addServer(new ServerInstance("server1:8080", 1.0));
        loadBalancer.addServer(new ServerInstance("server2:8080", 2.0));
        loadBalancer.addServer(new ServerInstance("server3:8080", 1.5));

        // Simulate requests with session persistence
        Random random = new Random();
        String[] sessions = {"session1", "session2", "session3"};

        for (int i = 0; i < 10; i++) {
            String sessionId = sessions[random.nextInt(sessions.length)];
            Request request = new Request("REQ-" + i, "Some data", sessionId);
            try {
                loadBalancer.handleRequest(request);
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println("Error handling request: " + e.getMessage());
            }
        }
    }
}
