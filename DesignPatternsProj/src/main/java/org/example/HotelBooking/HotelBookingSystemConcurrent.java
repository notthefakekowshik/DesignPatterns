//package org.example.HotelBooking;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map; // Import Map interface
//import java.util.Objects;
//import java.util.Set;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap; // Import
//import java.util.concurrent.CopyOnWriteArrayList; // Import
//import java.util.stream.Collectors;
//
//import lombok.Getter;
//
//// --- Enum Definitions ---
//enum RoomType {
//    SINGLE, DOUBLE, SUITE, DELUXE
//}
//
//// --- Class Definitions ---
//
///**
// * Represents a room in the hotel. (No changes needed)
// */
//class Room {
//    private final String roomId;
//    private final String roomNumber;
//    private final RoomType type;
//    private final double pricePerNight;
//
//    public Room(String roomId, String roomNumber, RoomType type, double pricePerNight) {
//        // This throws an NPE if any of the parameters are null
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
//    public String getRoomId() { return roomId; }
//    public String getRoomNumber() { return roomNumber; }
//    public RoomType getType() { return type; }
//    public double getPricePerNight() { return pricePerNight; }
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
//        return roomId.equals(room.roomId); // Equality based on ID
//    }
//
//    @Override
//    public int hashCode() {
//        return roomId.hashCode(); // HashCode based on ID
//    }
//}
//
///**
// * Represents a booking reservation. (No changes needed)
// */
//class Booking {
//    private final String bookingId;
//    private final String roomId; // Link to Room
//    private final String customerId; // Link to Customer
//    private final LocalDate startDate; // Inclusive
//    private final LocalDate endDate;   // Exclusive
//
//    public Booking(String roomId, String customerId, LocalDate startDate, LocalDate endDate) {
//        if (startDate == null || endDate == null || !endDate.isAfter(startDate)) {
//            throw new IllegalArgumentException("End date must be after start date and neither can be null.");
//        }
//        this.bookingId = UUID.randomUUID().toString();
//        this.roomId = Objects.requireNonNull(roomId, "Room ID cannot be null for booking");
//        this.customerId = Objects.requireNonNull(customerId, "Customer ID cannot be null for booking");
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
//
//    // --- Getters ---
//    public String getBookingId() { return bookingId; }
//    public String getRoomId() { return roomId; }
//    public String getCustomerId() { return customerId; }
//    public LocalDate getStartDate() { return startDate; }
//    public LocalDate getEndDate() { return endDate; }
//
//    public boolean overlaps(LocalDate reqStart, LocalDate reqEnd) {
//        if (reqStart == null || reqEnd == null || !reqEnd.isAfter(reqStart)) {
//            return false;
//        }
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
// * Abstract base class for users. (Renamed from Human)
// */
//abstract class User {
//    protected final String userId;
//    protected String name;
//
//    public User(String userId, String name) {
//        this.userId = Objects.requireNonNull(userId, "User ID cannot be null");
//        this.name = Objects.requireNonNull(name, "Name cannot be null");
//    }
//
//    public String getUserId() { return userId; }
//    public String getName() { return name; }
//
//    @Override
//    public String toString() {
//        return "User [userId=" + userId + ", name=" + name + "]";
//    }
//}
//
///**
// * Represents a customer user. (No changes needed)
// */
//class Customer extends User {
//    public Customer(String userId, String name) {
//        super(userId, name);
//    }
//
//    public Booking makeBooking(LocalDate startDate, LocalDate endDate) {
//        System.out.println("Customer '" + this.getName() + "' (ID: " + this.getUserId() +
//                ") attempting to book a room from " + startDate + " to " + endDate);
//        return Hotel.getInstance().bookFirstAvailableRoom(this.getUserId(), startDate, endDate);
//    }
//
//    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
//        System.out.println("Customer '" + this.getName() + "' searching for available rooms from " + startDate + " to " + endDate);
//        return Hotel.getInstance().findAvailableRooms(startDate, endDate);
//    }
//}
//
///**
// * Represents an administrator user. (No changes needed)
// */
//class Admin extends User {
//    public Admin(String userId, String name) {
//        super(userId, name);
//    }
//
//    public void addRoomToHotel(Room room) {
//        System.out.println("Admin '" + this.getName() + "' attempting to add room: " + (room != null ? room.getRoomId() : "null"));
//        Hotel.getInstance().addRoom(room);
//    }
//
//    public boolean removeRoomFromHotel(String roomId) {
//        System.out.println("Admin '" + this.getName() + "' attempting to remove room: " + roomId);
//        return Hotel.getInstance().removeRoom(roomId);
//    }
//}
//
//
///**
// * Represents the single hotel instance (Singleton) using Concurrent Collections.
// * Manages rooms and bookings with higher potential concurrency.
// */
//class Hotel {
//    @Getter private static final Hotel instance = new Hotel();
//
//    // Use ConcurrentHashMap for rooms: Key=roomId, Value=Room object
//    private final Map<String, Room> rooms = new ConcurrentHashMap<>();
//    // Map room ID to its list of bookings for efficient lookup
//    private final Map<String, List<Booking>> bookingsByRoomId = new ConcurrentHashMap<>();
//    // Optional: Keep a primary list of all bookings using CopyOnWriteArrayList
//    private final List<Booking> allBookings = new CopyOnWriteArrayList<>();
//
//
//    private Hotel() {
//        initializeRooms();
//    }
//
//    // --- Room Management (Admin Actions) ---
//
//    // Not synchronized - relies on ConcurrentHashMap's thread safety
//    public void addRoom(Room room) {
//        if (room == null) {
//            System.err.println("[Hotel System] Failed to add room: Room object is null.");
//            return;
//        }
//        if (room.getRoomId() == null || room.getRoomId().trim().isEmpty()) {
//            System.err.println("[Hotel System] Failed to add room: Room ID is invalid.");
//            return;
//        }
//
//        // Use putIfAbsent for atomic check-and-add (O(1) average time)
//        Room existingRoom = rooms.putIfAbsent(room.getRoomId(), room);
//
//        if (existingRoom == null) {
//            System.out.println("[Hotel System] Added room: " + room);
//        } else {
//            System.err.println("[Hotel System] Failed to add room: Room ID '" + room.getRoomId() + "' already exists.");
//        }
//    }
//
//    // Not synchronized - relies on ConcurrentHashMap/CopyOnWriteArrayList thread safety
//    // Note: There's a small race condition window between checking bookings and removing the room.
//    // If another thread books the room *exactly* between the check and the remove, deletion might
//    // incorrectly succeed. Synchronizing this method would prevent that.
//    public boolean removeRoom(String roomId) {
//        if (roomId == null || roomId.trim().isEmpty()) {
//            System.err.println("[Hotel System] Failed to remove room: Room ID is invalid.");
//            return false;
//        }
//
//        // Check if any bookings exist for this room using the map
//        List<Booking> roomBookings = bookingsByRoomId.get(roomId);
//        // Check if the list reference exists and if it contains any bookings
//        boolean hasBookings = roomBookings != null && !roomBookings.isEmpty();
//        if (hasBookings) {
//            System.err.println("[Hotel System] Failed to remove room '" + roomId + "': It has associated booking records in the map.");
//            return false;
//        }
//
//        // Improvement:
//        /*
//            here, we check if a room has ever booked or not.
//            we should not remove the room if there are only past bookings, no current and future bookings.
//         */
//        LocalDate currDate = LocalDate.now();
//        boolean hasAnyCurrentOrFutureBookings = roomBookings.stream()
//                .filter(currRoom -> currRoom.getRoomId().equals(roomId))
//                .anyMatch(currRoom -> currRoom.getEndDate().isAfter(currDate));
//
//        if (hasAnyCurrentOrFutureBookings) {
//            System.err.println("[Hotel System] Failed to remove room '" + roomId + "': It has associated booking records in the map.");
//            return false;
//        }
//
//        // Remove from rooms map (thread-safe)
//        Room removedRoom = rooms.remove(roomId); // O(1) average remove by key
//
//        if (removedRoom != null) {
//            // Also remove from the booking map (thread-safe)
//            bookingsByRoomId.remove(roomId);
//            // Optional: remove from allBookings list too
//            // allBookings.removeIf(b -> b.getRoomId().equals(roomId)); // Can be slow on COWList
//            System.out.println("[Hotel System] Removed room: " + removedRoom);
//            return true;
//        } else {
//            System.err.println("[Hotel System] Failed to remove room: Room ID '" + roomId + "' not found.");
//            return false;
//        }
//    }
//
//
//    // --- Booking Management ---
//
//    // Not synchronized - uses concurrent map/list lookups, may see slightly stale data
//    public List<Room> findAvailableRooms(LocalDate reqStartDate, LocalDate reqEndDate) {
//        if (reqStartDate == null || reqEndDate == null || !reqEndDate.isAfter(reqStartDate)) {
//            System.err.println("[Hotel System] Invalid date range provided for finding available rooms.");
//            return new ArrayList<>();
//        }
//
//        // Find IDs of rooms that have overlapping bookings using the efficient map
//        Set<String> bookedRoomIdsForPeriod = new HashSet<>();
//        // Iterate thread-safe map - entrySet() reflects a point-in-time state
//        for (Map.Entry<String, List<Booking>> entry : bookingsByRoomId.entrySet()) {
//            String currentRoomId = entry.getKey();
//            List<Booking> roomBookingsList = entry.getValue(); // This is a CopyOnWriteArrayList
//            // Iterate thread-safe list (snapshot)
//            for (Booking booking : roomBookingsList) {
//                if (booking.overlaps(reqStartDate, reqEndDate)) {
//                    bookedRoomIdsForPeriod.add(currentRoomId);
//                    break; // No need to check other bookings for this room
//                }
//            }
//        }
//
//        // Filter against the *current* collection of room values from the map
//        // Note: Iteration order over map values is not guaranteed
//        return rooms.values().stream() // Stream over Collection<Room>
//                .filter(room -> !bookedRoomIdsForPeriod.contains(room.getRoomId()))
//                .collect(Collectors.toList());
//    }
//
//
//    // Synchronized - To make the compound action (check-then-act) atomic
//    public synchronized Booking bookFirstAvailableRoom(String customerId, LocalDate reqStartDate, LocalDate reqEndDate) {
//        if (customerId == null || customerId.trim().isEmpty()) {
//            System.err.println("[Hotel System] Invalid customer ID provided for booking.");
//            return null;
//        }
//        // findAvailableRooms internally handles date validation and is thread-safe for reads
//
//        // *** CHECK *** (performed by findAvailableRooms, gets latest available based on current state)
//        List<Room> availableRooms = findAvailableRooms(reqStartDate, reqEndDate);
//
//        if (!availableRooms.isEmpty()) {
//            // *** DECIDE ***
//            // Get the first room from the list returned by findAvailableRooms.
//            // Note: The order depends on the iteration order of rooms.values() used in findAvailableRooms.
//            Room roomToBook = availableRooms.get(0);
//
//            // Optional final validation (paranoid check against race condition with removeRoom):
//            // if (!rooms.containsKey(roomToBook.getRoomId())) {
//            //    System.err.println("[Hotel System] Race condition: Room " + roomToBook.getRoomId() + " removed just before booking could complete.");
//            //    return null; // Or potentially retry findAvailableRooms?
//            // }
//
//            try {
//                // *** ACT ***
//                Booking newBooking = new Booking(roomToBook.getRoomId(), customerId, reqStartDate, reqEndDate);
//                addBookingInternal(newBooking); // Add booking to concurrent data structures
//
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
//    // Internal helper - not synchronized directly, called by synchronized method or needs care
//    private void addBookingInternal(Booking booking) {
//        if (booking != null) {
//            allBookings.add(booking); // Add to main list (thread-safe)
//            // Add to the map's list for the specific room (thread-safe)
//            // computeIfAbsent ensures the list is created atomically if needed
//            bookingsByRoomId.computeIfAbsent(booking.getRoomId(), k -> new CopyOnWriteArrayList<>())
//                    .add(booking); // Add to the CopyOnWriteArrayList is thread-safe
//        }
//    }
//
//    // --- Utility Methods ---
//    // Not synchronized - relies on ConcurrentHashMap thread safety for reads
//    public List<Room> getAllRooms() {
//        // Return a mutable copy based on the current snapshot of map values
//        return new ArrayList<>(rooms.values());
//    }
//
//    // Not synchronized - relies on CopyOnWriteArrayList thread safety for reads
//    public List<Booking> getAllBookings() {
//        // Return a mutable copy based on the current snapshot of the list
//        return new ArrayList<>(allBookings);
//    }
//
//    // Private helper for initialization
//    private void initializeRooms() {
//        System.out.println("[Hotel System] Initializing Hotel with Concurrent Collections...");
//        // Use internal addRoom which now uses ConcurrentHashMap
//        addRoom(new Room("R101", "101", RoomType.SINGLE, 1200.0));
//        addRoom(new Room("R102", "102", RoomType.SINGLE, 1250.0));
//        addRoom(new Room("R201", "201", RoomType.DOUBLE, 2500.0));
//        addRoom(new Room("R202", "202", RoomType.DOUBLE, 2600.0));
//        addRoom(new Room("R301", "301", RoomType.SUITE, 5500.0));
//        System.out.println("[Hotel System] Initialization complete.");
//    }
//}
//
//
///**
// * Main class to run the simulation.
// */
//// Make class public if it's the only one in the file, otherwise package-private is fine.
//public class HotelBookingSystemConcurrent {
//
//    public static void main(String[] args) {
//        System.out.println("--- Hotel Room Booking System Simulation (Concurrent Collections) ---");
//
//        // Using fixed date for consistent testing relative to example runs
//        LocalDate today = LocalDate.of(2025, 4, 26);
//        LocalDate tomorrow = today.plusDays(1);
//        LocalDate dayAfterTomorrow = today.plusDays(2);
//        LocalDate nextWeekStart = today.plusWeeks(1);
//        LocalDate nextWeekEnd = nextWeekStart.plusDays(3);
//
//        Hotel hotel = Hotel.getInstance();
//
//        System.out.println("\n--- Creating Users ---");
//        Admin admin = new Admin("ADM-01", "Mr. Chandra");
//        Customer customer1 = new Customer("CUST-101", "Priya Sharma");
//        Customer customer2 = new Customer("CUST-102", "Rahul Verma");
//        System.out.println("Created Admin: " + admin.getName());
//        System.out.println("Created Customer: " + customer1.getName());
//        System.out.println("Created Customer: " + customer2.getName());
//
//        System.out.println("\n--- Admin Actions ---");
//        Room newRoom = new Room("R401", "401", RoomType.DELUXE, 7500.0);
//        admin.addRoomToHotel(newRoom);
//        admin.addRoomToHotel(newRoom); // Attempt duplicate add
//        admin.removeRoomFromHotel("R101"); // Remove existing
//        admin.removeRoomFromHotel("R999"); // Attempt remove non-existent
//
//
//        System.out.println("\n--- Customer Actions ---");
//        System.out.println("\n" + customer1.getName() + " searching for rooms:");
//        List<Room> availableRooms = customer1.findAvailableRooms(tomorrow, dayAfterTomorrow);
//        if (!availableRooms.isEmpty()) {
//            System.out.println("Available rooms for " + tomorrow + " to " + dayAfterTomorrow + ":");
//            availableRooms.forEach(r -> System.out.println("  " + r));
//        } else { System.out.println("No rooms available."); }
//
//        Booking booking1 = customer1.makeBooking(tomorrow, dayAfterTomorrow);
//        if (booking1 != null) { System.out.println(customer1.getName() + " booked Room: " + booking1.getRoomId()); }
//
//        System.out.println("\n" + customer2.getName() + " searching for rooms for the same night:");
//        List<Room> availableRoomsLater = customer2.findAvailableRooms(tomorrow, dayAfterTomorrow);
//        if (!availableRoomsLater.isEmpty()) {
//            System.out.println("Available rooms now:");
//            availableRoomsLater.forEach(r -> System.out.println("  " + r));
//            Booking booking2 = customer2.makeBooking(tomorrow, dayAfterTomorrow);
//            if (booking2 != null) { System.out.println(customer2.getName() + " booked Room: " + booking2.getRoomId()); }
//        } else { System.out.println("No rooms available left for " + customer2.getName()); }
//
//        System.out.println("\n" + customer1.getName() + " searching for rooms next week:");
//        Booking booking3 = customer1.makeBooking(nextWeekStart, nextWeekEnd);
//        if (booking3 != null) { System.out.println(customer1.getName() + " booked Room: " + booking3.getRoomId()); }
//
//
//        System.out.println("\n--- Admin Actions After Bookings ---");
//        if (booking1 != null) {
//            System.out.println("Admin trying to remove room " + booking1.getRoomId() + " which has bookings.");
//            admin.removeRoomFromHotel(booking1.getRoomId()); // Should fail due to booking check
//        }
//
//
//        System.out.println("\n--- Final Hotel State ---");
//        System.out.println("Current Rooms (" + hotel.getAllRooms().size() + "):");
//        hotel.getAllRooms().forEach(r -> System.out.println("  " + r));
//        System.out.println("\nCurrent Bookings (" + hotel.getAllBookings().size() + "):");
//        hotel.getAllBookings().forEach(b -> System.out.println("  " + b));
//
//        System.out.println("\n--- Simulation End ---");
//    }
//}