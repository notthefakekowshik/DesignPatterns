package com.kow.designs.DesignDelayedTaskExecutor;

import java.util.PriorityQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A Thread-Safe Delayed Scheduler (Senior Engineer Implementation).
 * * Key Features:
 * 1. Uses PriorityQueue (Min-Heap) for O(log N) scheduling.
 * 2. Uses ReentrantLock + Condition for efficient waiting (0% CPU usage while
 * idle).
 * 3. Uses System.nanoTime() (Monotonic Clock) to be immune to system clock
 * rollbacks (NTP).
 */
public class DelayedSchedulerBetterSolution {

    // 1. The Min-Heap: Keeps the soonest task at the HEAD.
    private final PriorityQueue<Task> queue = new PriorityQueue<>();

    // 2. The Lock: Protects the shared queue.
    private final ReentrantLock lock = new ReentrantLock();

    // 3. The Condition: Allows the worker thread to sleep and wake up dynamically.
    private final Condition available = lock.newCondition();

    // 4. Lifecycle flag
    private volatile boolean running = true;

    /**
     * Starts the background worker thread.
     */
    public void start() {
        new Thread(this::workerLoop, "Scheduler-Worker").start();
    }

    /**
     * Schedules a task to run after a specific delay.
     * * @param action The runnable task.
     * 
     * @param delay The time to wait.
     * @param unit  The time unit.
     */
    public void schedule(Runnable action, long delay, TimeUnit unit) {
        // SENIOR LEVEL DETAIL:
        // We convert the delay to an absolute point in "nano time".
        // We use nanoTime because it is "Monotonic" (it never jumps back).
        // If we used currentTimeMillis(), an NTP update (changing clock from 10:05 to
        // 10:00)
        // would cause the task to hang for an extra 5 minutes.
        long scheduledTime = System.nanoTime() + unit.toNanos(delay);
        Task task = new Task(action, scheduledTime);

        lock.lock();
        try {
            queue.add(task);

            // CRITICAL OPTIMIZATION:
            // We only need to wake up the worker thread if the NEW task is
            // sooner than the task currently at the Head of the queue.
            //
            // Scenario:
            // 1. Worker is sleeping for 1 hour (waiting for Task A).
            // 2. You insert Task B (due in 5 seconds).
            // 3. Task B becomes the new Head.
            // 4. We MUST signal() to wake up the worker so it can re-adjust
            // its sleep time from 1 hour to 5 seconds.
            if (queue.peek() == task) {
                available.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * The background loop that executes tasks.
     */
    private void workerLoop() {
        while (running) {
            lock.lock();
            try {
                // 1. If queue is empty, wait forever until a producer signals us.
                while (queue.isEmpty()) {
                    available.await();
                }

                // 2. Peek at the first task (Do not remove it yet!)
                Task task = queue.peek();
                long now = System.nanoTime();
                long delayNanos = task.scheduledTime - now;

                if (delayNanos <= 0) {
                    // CASE A: The task is due right now (or in the past).
                    queue.poll(); // Remove from queue

                    // Unlock BEFORE running the task.
                    // Why? If the task takes 5 seconds to run, we don't want to block
                    // other threads from calling schedule() during that time.
                    lock.unlock();

                    try {
                        task.action.run();
                    } catch (Exception e) {
                        System.err.println("Task failed: " + e.getMessage());
                    }

                    // Re-acquire lock to safely loop back and check the queue again.
                    lock.lock();
                } else {
                    // CASE B: The task is in the future.
                    // Sleep for the EXACT remaining duration.
                    //
                    // Why awaitNanos() instead of Thread.sleep()?
                    // Because awaitNanos() releases the lock! This allows producers
                    // to add new tasks while we sleep. If a new "sooner" task is added,
                    // they will call signal(), causing awaitNanos() to return early.
                    available.awaitNanos(delayNanos);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                // Robustness check: Ensure we release lock if we still hold it
                // (e.g., if exception occurred inside the loop logic)
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * Stops the scheduler.
     */
    public void stop() {
        running = false;
        // Wake up worker so it can exit the loop
        lock.lock();
        try {
            available.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // Inner class for tasks
    private static class Task implements Comparable<Task> {
        final Runnable action;
        final long scheduledTime; // in System.nanoTime()

        public Task(Runnable action, long scheduledTime) {
            this.action = action;
            this.scheduledTime = scheduledTime;
        }

        @Override
        public int compareTo(Task other) {
            // Sort by who needs to run first
            return Long.compare(this.scheduledTime, other.scheduledTime);
        }
    }

    // ==========================================
    // MAIN METHOD FOR TESTING
    // ==========================================
    public static void main(String[] args) throws InterruptedException {
        DelayedSchedulerBetterSolution scheduler = new DelayedSchedulerBetterSolution();
        scheduler.start();

        System.out.println("Time: 0s - Scheduling tasks...");

        // Task 1: Runs in 2 seconds
        scheduler.schedule(() -> System.out.println("Task A executed (Target: 2s)"), 2, TimeUnit.SECONDS);

        // Task 2: Runs in 5 seconds
        scheduler.schedule(() -> System.out.println("Task B executed (Target: 5s)"), 5, TimeUnit.SECONDS);

        // Task 3: Runs in 1 second (Inserted AFTER the others to test the "Wake Up"
        // logic)
        // This simulates the worker sleeping for Task A (2s), then being interrupted
        // for Task C (1s).
        Thread.sleep(100); // Slight pause to ensure worker was already sleeping
        System.out.println("Time: 0.1s - Scheduling urgent task...");
        scheduler.schedule(() -> System.out.println("Task C executed (Target: 1s)"), 1, TimeUnit.SECONDS);
    }
}