//package org.example.HotelBooking;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//// --- Enum Definitions ---
//enum RoomType {
//    SINGLE, DOUBLE, SUITE, DELUXE
//}
//
//// --- Class Definitions ---
//
///**
// * Represents a room in the hotel.
// */
//class Room {
//    private final String roomId;
//    private final String roomNumber;
//    private final RoomType type;
//    private final double pricePerNight;
//
//    public Room(String roomId, String roomNumber, RoomType type, double pricePerNight) {
//        this.roomId = Objects.requireNonNull(roomId, "Room ID cannot be null");
//        this.roomNumber = Objects.requireNonNull(roomNumber, "Room number cannot be null");
//        this.type = Objects.requireNonNull(type, "Room type cannot be null");
//        this.pricePerNight = pricePerNight;
//        if (pricePerNight < 0) {
//            throw new IllegalArgumentException("Price per night cannot be negative.");
//        }
//    }
//
//    // --- Getters ---
//    public String getRoomId() {
//        return roomId;
//    }
//
//    public String getRoomNumber() {
//        return roomNumber;
//    }
//
//    public RoomType getType() {
//        return type;
//    }
//
//    public double getPricePerNight() {
//        return pricePerNight;
//    }
//
//    @Override
//    public String toString() {
//        return "Room [id=" + roomId + ", number=" + roomNumber + ", type=" + type + ", price=₹" + pricePerNight + "]";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Room room = (Room) o;
//        return roomId.equals(room.roomId);
//    }
//
//    @Override
//    public int hashCode() {
//        return roomId.hashCode();
//    }
//}
//
///**
// * Represents a booking reservation.
// */
//class Booking {
//    private final String bookingId;
//    private final String roomId; // Link to Room
//    private final String customerId; // Link to Customer
//    private final LocalDate startDate; // Inclusive
//    private final LocalDate endDate;   // Exclusive (Booking is for nights from startDate up to, but not including, endDate)
//
//    public Booking(String roomId, String customerId, LocalDate startDate, LocalDate endDate) {
//        if (startDate == null || endDate == null || !endDate.isAfter(startDate)) {
//            throw new IllegalArgumentException("End date must be after start date and neither can be null.");
//        }
//        this.bookingId = UUID.randomUUID().toString(); // Generate a unique ID
//        this.roomId = Objects.requireNonNull(roomId, "Room ID cannot be null for booking");
//        this.customerId = Objects.requireNonNull(customerId, "Customer ID cannot be null for booking");
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
//
//    // --- Getters ---
//    public String getBookingId() {
//        return bookingId;
//    }
//
//    public String getRoomId() {
//        return roomId;
//    }
//
//    public String getCustomerId() {
//        return customerId;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    /**
//     * Checks if this booking overlaps with a given date range.
//     * Note: The check uses exclusive end dates for both ranges.
//     * Overlap occurs if: reqStart < bookEnd AND reqEnd > bookStart
//     * @param reqStart Start date of the requested range (inclusive).
//     * @param reqEnd End date of the requested range (exclusive).
//     * @return true if there is an overlap, false otherwise.
//     */
//    public boolean overlaps(LocalDate reqStart, LocalDate reqEnd) {
//        if (reqStart == null || reqEnd == null || !reqEnd.isAfter(reqStart)) {
//            // Consider throwing an exception for invalid input range, or log a warning
//            return false; // Cannot overlap with an invalid range
//        }
//        // Check if requested start is before this booking ends AND requested end is after this booking starts
//        return reqStart.isBefore(this.endDate) && reqEnd.isAfter(this.startDate);
//    }
//
//    @Override
//    public String toString() {
//        return "Booking [id=" + bookingId + ", roomId=" + roomId + ", customerId=" + customerId +
//                ", dates=" + startDate + " to " + endDate + "]";
//    }
//}
//
///**
// * Abstract base class for users.
// */
//abstract class Human {
//    protected final String userId;
//    protected String name;
//
//    public Human(String userId, String name) {
//        this.userId = Objects.requireNonNull(userId, "User ID cannot be null");
//        this.name = Objects.requireNonNull(name, "Name cannot be null");
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        return "User [userId=" + userId + ", name=" + name + "]";
//    }
//}
//
///**
// * Represents a customer user.
// */
//class Customer extends Human {
//    public Customer(String userId, String name) {
//        super(userId, name);
//    }
//
//    /**
//     * Attempts to book the first available room for the specified dates.
//     * Uses the customer's own ID for the booking.
//     * @param startDate The desired check-in date (inclusive).
//     * @param endDate The desired check-out date (exclusive).
//     * @return The Booking object if successful, null otherwise.
//     */
//    public Booking makeBooking(LocalDate startDate, LocalDate endDate) {
//        System.out.println("Customer '" + this.getName() + "' (ID: " + this.getUserId() +
//                ") attempting to book a room from " + startDate + " to " + endDate);
//        return Hotel.getInstance().bookFirstAvailableRoom(this.getUserId(), startDate, endDate);
//    }
//
//    /**
//     * Lists rooms available for a given period.
//     * @param startDate Start date (inclusive).
//     * @param endDate End date (exclusive).
//     * @return List of available rooms.
//     */
//    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
//        System.out.println("Customer '" + this.getName() + "' searching for available rooms from " + startDate + " to " + endDate);
//        return Hotel.getInstance().findAvailableRooms(startDate, endDate);
//    }
//}
//
///**
// * Represents an administrator user.
// */
//class Admin extends Human {
//    public Admin(String userId, String name) {
//        super(userId, name);
//    }
//
//    /**
//     * Adds a new room to the hotel system.
//     * Enforces that only an Admin can initiate this action.
//     * @param room The Room object to add.
//     */
//    public void addRoomToHotel(Room room) {
//        System.out.println("Admin '" + this.getName() + "' attempting to add room: " + (room != null ? room.getRoomId() : "null"));
//        Hotel.getInstance().addRoom(room);
//    }
//
//    /**
//     * Removes a room from the hotel system.
//     * Enforces that only an Admin can initiate this action.
//     * @param roomId The ID of the room to remove.
//     * @return true if removal was successful (as reported by Hotel), false otherwise.
//     */
//    public boolean removeRoomFromHotel(String roomId) {
//        System.out.println("Admin '" + this.getName() + "' attempting to remove room: " + roomId);
//        return Hotel.getInstance().removeRoom(roomId);
//    }
//}
//
//
///**
// * Represents the single hotel instance (Singleton).
// * Manages rooms and bookings.
// */
//class Hotel {
//    private static final Hotel instance = new Hotel(); // Eager initialization
//
//    private final List<Room> rooms;
//    private final List<Booking> bookings;
//
//    // Private constructor for Singleton pattern
//    private Hotel() {
//        rooms = new ArrayList<>();
//        bookings = new ArrayList<>();
//        initializeRooms(); // Load initial set of rooms
//    }
//
//    // Static method to get the single instance
//    public static Hotel getInstance() {
//        return instance;
//    }
//
//    // --- Room Management (Admin Actions) ---
//
//    /**
//     * Adds a room to the hotel's inventory. Intended for Admin use.
//     */
//    public synchronized void addRoom(Room room) { // Added synchronized for basic thread safety
//        if (room != null && rooms.stream().noneMatch(r -> r.getRoomId().equals(room.getRoomId()))) {
//            rooms.add(room);
//            System.out.println("[Hotel System] Added room: " + room);
//        } else if (room == null) {
//            System.err.println("[Hotel System] Failed to add room: Room object is null.");
//        } else {
//            System.err.println("[Hotel System] Failed to add room: Room ID '" + room.getRoomId() + "' already exists.");
//        }
//    }
//
//    /**
//     * Removes a room from the hotel's inventory. Intended for Admin use.
//     * Prevents removal if any bookings (past or future) exist for the room.
//     */
//    public synchronized boolean removeRoom(String roomId) { // Added synchronized for basic thread safety
//        if (roomId == null || roomId.trim().isEmpty()) {
//            System.err.println("[Hotel System] Failed to remove room: Room ID is invalid.");
//            return false;
//        }
//
//        boolean hasBookings = bookings.stream().anyMatch(b -> b.getRoomId().equals(roomId));
//        if (hasBookings) {
//            System.err.println("[Hotel System] Failed to remove room '" + roomId + "': It has associated booking records.");
//            return false;
//        }
//
//        Room roomToRemove = rooms.stream()
//                .filter(r -> r.getRoomId().equals(roomId))
//                .findFirst()
//                .orElse(null);
//
//        if (roomToRemove != null) {
//            rooms.remove(roomToRemove);
//            System.out.println("[Hotel System] Removed room: " + roomToRemove);
//            return true;
//        } else {
//            System.err.println("[Hotel System] Failed to remove room: Room ID '" + roomId + "' not found.");
//            return false;
//        }
//    }
//
//    // --- Booking Management (Customer Actions) ---
//
//    /**
//     * Finds all rooms available for the entire specified duration.
//     */
//    public synchronized List<Room> findAvailableRooms(LocalDate reqStartDate, LocalDate reqEndDate) { // Added synchronized
//        if (reqStartDate == null || reqEndDate == null || !reqEndDate.isAfter(reqStartDate)) {
//            System.err.println("[Hotel System] Invalid date range provided for finding available rooms.");
//            return new ArrayList<>();
//        }
//
//        // Find IDs of rooms that have overlapping bookings in the requested period
//        Set<String> bookedRoomIdsForPeriod = this.bookings.stream()
//                .filter(booking -> booking.overlaps(reqStartDate, reqEndDate))
//                .map(Booking::getRoomId)
//                .collect(Collectors.toSet());
//
//        // Return rooms whose IDs are not in the booked set
//        return this.rooms.stream()
//                .filter(room -> !bookedRoomIdsForPeriod.contains(room.getRoomId()))
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Books the first available room for the given customer and dates.
//     */
//    public synchronized Booking bookFirstAvailableRoom(String customerId, LocalDate reqStartDate, LocalDate reqEndDate) { // Added synchronized
//        if (customerId == null || customerId.trim().isEmpty()) {
//            System.err.println("[Hotel System] Invalid customer ID provided for booking.");
//            return null;
//        }
//        // findAvailableRooms handles date validation
//
//        List<Room> availableRooms = findAvailableRooms(reqStartDate, reqEndDate);
//
//        if (!availableRooms.isEmpty()) {
//            Room roomToBook = availableRooms.get(0); // Get the first one found
//            try {
//                Booking newBooking = new Booking(roomToBook.getRoomId(), customerId, reqStartDate, reqEndDate);
//                this.addBookingInternal(newBooking); // Use internal add method
//                System.out.println("[Hotel System] Successfully booked Room " + roomToBook.getRoomNumber() +
//                        " (ID: " + roomToBook.getRoomId() +") for customer " + customerId +
//                        " from " + reqStartDate + " to " + reqEndDate);
//                return newBooking;
//            } catch (IllegalArgumentException e) {
//                System.err.println("[Hotel System] Error creating booking: " + e.getMessage());
//                return null;
//            }
//        } else {
//            System.out.println("[Hotel System] Sorry, no rooms available for the selected dates: " +
//                    reqStartDate + " to " + reqEndDate);
//            return null;
//        }
//    }
//
//    /**
//     * Internal method to add a booking record. Called by bookFirstAvailableRoom.
//     */
//    private void addBookingInternal(Booking booking) { // Made private, added by booking logic
//        if (booking != null) {
//            bookings.add(booking);
//            // System.out.println("[Hotel System] Added booking record: " + booking); // Maybe too verbose
//        }
//    }
//
//    // --- Utility and Helper Methods ---
//
//    /**
//     * Returns a copy of the list of all rooms.
//     */
//    public synchronized List<Room> getAllRooms() { // Added synchronized
//        return new ArrayList<>(rooms); // Return a copy
//    }
//
//    /**
//     * Returns a copy of the list of all bookings.
//     */
//    public synchronized List<Booking> getAllBookings() { // Added synchronized
//        return new ArrayList<>(bookings); // Return a copy
//    }
//
//    /**
//     * Initializes the hotel with some sample rooms.
//     */
//    private void initializeRooms() {
//        System.out.println("[Hotel System] Initializing Hotel...");
//        addRoom(new Room("R101", "101", RoomType.SINGLE, 1200.0));
//        addRoom(new Room("R102", "102", RoomType.SINGLE, 1250.0));
//        addRoom(new Room("R201", "201", RoomType.DOUBLE, 2500.0));
//        addRoom(new Room("R202", "202", RoomType.DOUBLE, 2600.0));
//        addRoom(new Room("R301", "301", RoomType.SUITE, 5500.0));
//        System.out.println("[Hotel System] Initialization complete. " + rooms.size() + " rooms available.");
//    }
//}
//
//
///**
// * Main class to run the simulation.
// */
//public class HotelBookingSystem { // Renamed Main to match filename convention
//
//    public static void main(String[] args) {
//        System.out.println("--- Hotel Room Booking System Simulation ---");
//
//        // Current date context (April 26, 2025)
//        LocalDate today = LocalDate.of(2025, 4, 26); // Fixed date for predictable testing
//        LocalDate tomorrow = today.plusDays(1);
//        LocalDate dayAfterTomorrow = today.plusDays(2);
//        LocalDate nextWeekStart = today.plusWeeks(1);
//        LocalDate nextWeekEnd = nextWeekStart.plusDays(3); // Book for 3 nights
//
//        // Get the single instance of the Hotel
//        Hotel hotel = Hotel.getInstance();
//
//        // --- Create Users ---
//        System.out.println("\n--- Creating Users ---");
//        Admin admin = new Admin("ADM-01", "Mr. Chandra");
//        Customer customer1 = new Customer("CUST-101", "Priya Sharma");
//        Customer customer2 = new Customer("CUST-102", "Rahul Verma");
//        System.out.println("Created Admin: " + admin.getName());
//        System.out.println("Created Customer: " + customer1.getName());
//        System.out.println("Created Customer: " + customer2.getName());
//
//        // --- Admin Actions ---
//        System.out.println("\n--- Admin Actions ---");
//        // Admin adds a new room
//        Room newRoom = new Room("R401", "401", RoomType.DELUXE, 7500.0);
//        admin.addRoomToHotel(newRoom);
//
//        // Admin tries to add the same room again (should fail)
//        admin.addRoomToHotel(newRoom);
//
//        // Admin removes an existing room (R101)
//        admin.removeRoomFromHotel("R101");
//
//        // Admin tries to remove a non-existent room
//        admin.removeRoomFromHotel("R999");
//
//
//        // --- Customer Actions ---
//        System.out.println("\n--- Customer Actions ---");
//
//        // Customer 1 searches for rooms for tomorrow night (1 night)
//        System.out.println("\n" + customer1.getName() + " searching for rooms:");
//        List<Room> availableRooms = customer1.findAvailableRooms(tomorrow, dayAfterTomorrow);
//        if (!availableRooms.isEmpty()) {
//            System.out.println("Available rooms for " + tomorrow + " to " + dayAfterTomorrow + ":");
//            availableRooms.forEach(r -> System.out.println("  " + r));
//        } else {
//            System.out.println("No rooms available for " + tomorrow + " to " + dayAfterTomorrow);
//        }
//
//        // Customer 1 books the first available room (likely R102) for tomorrow night
//        Booking booking1 = customer1.makeBooking(tomorrow, dayAfterTomorrow);
//        if (booking1 != null) {
//            System.out.println(customer1.getName() + " received booking confirmation: " + booking1.getBookingId() + " for room " + booking1.getRoomId());
//        }
//
//        // Customer 2 tries to book a room for the SAME night
//        System.out.println("\n" + customer2.getName() + " searching for rooms for the same night:");
//        List<Room> availableRoomsLater = customer2.findAvailableRooms(tomorrow, dayAfterTomorrow);
//        if (!availableRoomsLater.isEmpty()) {
//            System.out.println("Available rooms for " + tomorrow + " to " + dayAfterTomorrow + ":");
//            availableRoomsLater.forEach(r -> System.out.println("  " + r));
//            // Customer 2 books the next available room (likely R201)
//            Booking booking2 = customer2.makeBooking(tomorrow, dayAfterTomorrow);
//            if (booking2 != null) {
//                System.out.println(customer2.getName() + " received booking confirmation: " + booking2.getBookingId() + " for room " + booking2.getRoomId());
//            }
//        } else {
//            System.out.println("No rooms available left for " + tomorrow + " to " + dayAfterTomorrow + " for " + customer2.getName());
//        }
//
//
//        // Customer 1 books another room (likely R202) for next week (3 nights)
//        System.out.println("\n" + customer1.getName() + " searching for rooms next week:");
//        Booking booking3 = customer1.makeBooking(nextWeekStart, nextWeekEnd);
//        if (booking3 != null) {
//            System.out.println(customer1.getName() + " received booking confirmation: " + booking3.getBookingId() + " for room " + booking3.getRoomId());
//        }
//
//        // --- Further Admin Action (Post-Booking) ---
//        System.out.println("\n--- Admin Actions After Bookings ---");
//        // Admin tries to remove the room booked by Customer 1 (R102 if booking1 was successful)
//        if (booking1 != null) {
//            System.out.println("Admin trying to remove room " + booking1.getRoomId() + " which has bookings.");
//            admin.removeRoomFromHotel(booking1.getRoomId()); // Should fail
//        }
//        // Admin tries to remove the room booked by Customer 2 (R201 if booking2 was successful)
//        if (booking2 != null) {
//            System.out.println("Admin trying to remove room " + booking2.getRoomId() + " which has bookings.");
//            admin.removeRoomFromHotel(booking2.getRoomId()); // Should fail
//        }
//
//
//        // Display final state
//        System.out.println("\n--- Final Hotel State ---");
//        System.out.println("Current Rooms (" + hotel.getAllRooms().size() + "):");
//        hotel.getAllRooms().forEach(r -> System.out.println("  " + r));
//        System.out.println("\nCurrent Bookings (" + hotel.getAllBookings().size() + "):");
//        hotel.getAllBookings().forEach(b -> System.out.println("  " + b));
//
//        System.out.println("\n--- Simulation End ---");
//    }
//}