package com.kow.designs.FlightManagementSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Flight Management System Design
 * 
 * This is a low-level system design for a flight management system
 * with the core components and their relationships.
 */

// Core domain entities
class Aircraft {
    private String id;
    private String model;
    private String registrationNumber;
    private int capacity;
    private AircraftStatus status;
    
    public Aircraft(String model, String registrationNumber, int capacity) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.status = AircraftStatus.AVAILABLE;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getModel() { return model; }
    public String getRegistrationNumber() { return registrationNumber; }
    public int getCapacity() { return capacity; }
    public AircraftStatus getStatus() { return status; }
    public void setStatus(AircraftStatus status) { this.status = status; }
}

enum AircraftStatus {
    AVAILABLE, IN_SERVICE, MAINTENANCE, RETIRED
}

class Flight {
    private String flightNumber;
    private Airport origin;
    private Airport destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Aircraft aircraft;
    private FlightStatus status;
    private Map<String, Seat> seats;
    
    public Flight(String flightNumber, Airport origin, Airport destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraft = aircraft;
        this.status = FlightStatus.SCHEDULED;
        this.seats = initializeSeats(aircraft.getCapacity());
    }
    
    private Map<String, Seat> initializeSeats(int capacity) {
        Map<String, Seat> seats = new HashMap<>();
        int rows = capacity / 6 + (capacity % 6 > 0 ? 1 : 0);
        
        for (int row = 1; row <= rows; row++) {
            for (char col = 'A'; col <= 'F' && (row-1)*6 + (col-'A') < capacity; col++) {
                String seatNumber = row + String.valueOf(col);
                seats.put(seatNumber, new Seat(seatNumber, SeatClass.ECONOMY, SeatStatus.AVAILABLE));
            }
        }
        return seats;
    }
    
    // Getters and setters
    public String getFlightNumber() { return flightNumber; }
    public Airport getOrigin() { return origin; }
    public Airport getDestination() { return destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public Aircraft getAircraft() { return aircraft; }
    public FlightStatus getStatus() { return status; }
    public void setStatus(FlightStatus status) { this.status = status; }
    public Map<String, Seat> getSeats() { return seats; }
}

enum FlightStatus {
    SCHEDULED, BOARDING, DEPARTED, IN_AIR, LANDED, DELAYED, CANCELLED
}

class Airport {
    private String code;
    private String name;
    private String city;
    private String country;
    
    public Airport(String code, String name, String city, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }
    
    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
}

class Seat {
    private String seatNumber;
    private SeatClass seatClass;
    private SeatStatus status;
    
    public Seat(String seatNumber, SeatClass seatClass, SeatStatus status) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.status = status;
    }
    
    // Getters and setters
    public String getSeatNumber() { return seatNumber; }
    public SeatClass getSeatClass() { return seatClass; }
    public SeatStatus getStatus() { return status; }
    public void setStatus(SeatStatus status) { this.status = status; }
}

enum SeatClass {
    ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST
}

enum SeatStatus {
    AVAILABLE, RESERVED, OCCUPIED, BLOCKED
}

class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;
    
    public User(String name, String email, String phone, UserRole role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public UserRole getRole() { return role; }
}

enum UserRole {
    PASSENGER, CREW, ADMIN, PILOT
}

class Booking {
    private String bookingId;
    private User passenger;
    private Flight flight;
    private String seatNumber;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    
    public Booking(User passenger, Flight flight, String seatNumber) {
        this.bookingId = UUID.randomUUID().toString();
        this.passenger = passenger;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.status = BookingStatus.CONFIRMED;
        this.bookingTime = LocalDateTime.now();
        
        // Update seat status in the flight
        flight.getSeats().get(seatNumber).setStatus(SeatStatus.RESERVED);
    }
    
    // Getters and setters
    public String getBookingId() { return bookingId; }
    public User getPassenger() { return passenger; }
    public Flight getFlight() { return flight; }
    public String getSeatNumber() { return seatNumber; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public LocalDateTime getBookingTime() { return bookingTime; }
}

enum BookingStatus {
    CONFIRMED, CANCELLED, CHECKED_IN
}

// Service interfaces
interface FlightService {
    Flight createFlight(String flightNumber, Airport origin, Airport destination, 
                        LocalDateTime departureTime, LocalDateTime arrivalTime, Aircraft aircraft);
    Flight getFlight(String flightNumber);
    List<Flight> searchFlights(Airport origin, Airport destination, LocalDateTime date);
    void updateFlightStatus(String flightNumber, FlightStatus status);
    void cancelFlight(String flightNumber);
}

interface BookingService {
    Booking bookFlight(User passenger, Flight flight, String seatNumber);
    Booking getBooking(String bookingId);
    List<Booking> getBookingsForUser(String userId);
    void cancelBooking(String bookingId);
    void checkIn(String bookingId);
}

interface AircraftService {
    Aircraft addAircraft(String model, String registrationNumber, int capacity);
    Aircraft getAircraft(String id);
    void updateAircraftStatus(String id, AircraftStatus status);
    List<Aircraft> getAllAvailableAircraft();
}

// Implementation classes
class FlightServiceImpl implements FlightService {
    private final Map<String, Flight> flights = new HashMap<>();
    
    @Override
    public Flight createFlight(String flightNumber, Airport origin, Airport destination,
                              LocalDateTime departureTime, LocalDateTime arrivalTime, Aircraft aircraft) {
        Flight flight = new Flight(flightNumber, origin, destination, departureTime, arrivalTime, aircraft);
        flights.put(flightNumber, flight);
        aircraft.setStatus(AircraftStatus.IN_SERVICE);
        return flight;
    }
    
    @Override
    public Flight getFlight(String flightNumber) {
        return flights.get(flightNumber);
    }
    
    @Override
    public List<Flight> searchFlights(Airport origin, Airport destination, LocalDateTime date) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (flight.getOrigin().equals(origin) && 
                flight.getDestination().equals(destination) &&
                flight.getDepartureTime().toLocalDate().equals(date.toLocalDate())) {
                results.add(flight);
            }
        }
        return results;
    }
    
    @Override
    public void updateFlightStatus(String flightNumber, FlightStatus status) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            flight.setStatus(status);
        }
    }
    
    @Override
    public void cancelFlight(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            flight.setStatus(FlightStatus.CANCELLED);
            flight.getAircraft().setStatus(AircraftStatus.AVAILABLE);
        }
    }
}

class BookingServiceImpl implements BookingService {
    private final Map<String, Booking> bookings = new HashMap<>();
    
    @Override
    public Booking bookFlight(User passenger, Flight flight, String seatNumber) {
        if (flight.getSeats().get(seatNumber).getStatus() != SeatStatus.AVAILABLE) {
            throw new IllegalStateException("Seat is not available");
        }
        
        Booking booking = new Booking(passenger, flight, seatNumber);
        bookings.put(booking.getBookingId(), booking);
        return booking;
    }
    
    @Override
    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
    
    @Override
    public List<Booking> getBookingsForUser(String userId) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getPassenger().getId().equals(userId)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
    
    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELLED);
            booking.getFlight().getSeats().get(booking.getSeatNumber()).setStatus(SeatStatus.AVAILABLE);
        }
    }
    
    @Override
    public void checkIn(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() == BookingStatus.CONFIRMED) {
            booking.setStatus(BookingStatus.CHECKED_IN);
            booking.getFlight().getSeats().get(booking.getSeatNumber()).setStatus(SeatStatus.OCCUPIED);
        }
    }
}

class AircraftServiceImpl implements AircraftService {
    private final Map<String, Aircraft> aircraft = new HashMap<>();
    
    @Override
    public Aircraft addAircraft(String model, String registrationNumber, int capacity) {
        Aircraft newAircraft = new Aircraft(model, registrationNumber, capacity);
        aircraft.put(newAircraft.getId(), newAircraft);
        return newAircraft;
    }
    
    @Override
    public Aircraft getAircraft(String id) {
        return aircraft.get(id);
    }
    
    @Override
    public void updateAircraftStatus(String id, AircraftStatus status) {
        Aircraft a = aircraft.get(id);
        if (a != null) {
            a.setStatus(status);
        }
    }
    
    @Override
    public List<Aircraft> getAllAvailableAircraft() {
        List<Aircraft> availableAircraft = new ArrayList<>();
        for (Aircraft a : aircraft.values()) {
            if (a.getStatus() == AircraftStatus.AVAILABLE) {
                availableAircraft.add(a);
            }
        }
        return availableAircraft;
    }
}

// Main class to demonstrate the system
public class FlightManagementSystem {
    public static void main(String[] args) {
        // Initialize services
        AircraftService aircraftService = new AircraftServiceImpl();
        FlightService flightService = new FlightServiceImpl();
        BookingService bookingService = new BookingServiceImpl();
        
        // Create sample data
        Aircraft boeing737 = aircraftService.addAircraft("Boeing 737", "N12345", 180);
        
        Airport jfk = new Airport("JFK", "John F. Kennedy International", "New York", "USA");
        Airport lax = new Airport("LAX", "Los Angeles International", "Los Angeles", "USA");
        
        LocalDateTime departure = LocalDateTime.now().plusDays(7).withHour(9).withMinute(0);
        LocalDateTime arrival = departure.plusHours(6);
        
        Flight flight = flightService.createFlight("AA123", jfk, lax, departure, arrival, boeing737);
        
        User passenger = new User("John Doe", "john@example.com", "123-456-7890", UserRole.PASSENGER);
        
        // Book a seat
        Booking booking = bookingService.bookFlight(passenger, flight, "10A");
        
        // Check in
        bookingService.checkIn(booking.getBookingId());
        
        // Update flight status
        flightService.updateFlightStatus(flight.getFlightNumber(), FlightStatus.BOARDING);
        
        // Print booking details
        System.out.println("Booking confirmed for " + passenger.getName());
        System.out.println("Flight: " + flight.getFlightNumber() + 
                          " from " + flight.getOrigin().getCode() + 
                          " to " + flight.getDestination().getCode());
        System.out.println("Departure: " + flight.getDepartureTime());
        System.out.println("Seat: " + booking.getSeatNumber());
        System.out.println("Status: " + booking.getStatus());
    }
} 
