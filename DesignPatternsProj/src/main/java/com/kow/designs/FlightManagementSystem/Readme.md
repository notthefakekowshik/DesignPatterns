# Flight Management System

This is a low-level system design for a basic flight management system that would be typical in a system design interview. The design focuses on core domain entities and their relationships without relying on specific frameworks.

## Core Components

### Domain Entities
- **Aircraft**: Represents airplanes with properties like model, registration number, capacity, and status
- **Flight**: Represents a scheduled flight between airports with properties for origin, destination, times, and seats
- **Airport**: Represents airports with code, name, city, and country
- **User**: Represents system users (passengers, crew, admins, pilots)
- **Booking**: Represents a reservation of a seat on a flight by a passenger
- **Seat**: Represents individual seats on an aircraft with number, class, and status

### Service Interfaces
- **FlightService**: Manages flight creation, retrieval, and status updates
- **BookingService**: Handles flight bookings, cancellations, and check-ins
- **AircraftService**: Manages aircraft fleet operations and status

## Design Patterns Used
- **Service Layer Pattern**: Separates business logic from the domain model
- **Repository Pattern**: Used for data access (implemented with in-memory maps in this demo)
- **Domain Model Pattern**: Rich domain objects that encapsulate both data and behavior

## Class Relationships
- A Flight is operated by one Aircraft
- A Flight has many Seats
- A Booking connects a User to a Flight and Seat
- Flights connect two Airports (origin and destination)

## Design Considerations
- The design uses simple in-memory data structures for demonstration purposes
- Error handling is minimal and would need to be expanded in a real system
- Authentication and authorization are not implemented
- Concurrency control for booking would be required in a production system
- The design could be extended with additional features like payment processing, loyalty programs, crew scheduling, etc.

## System Usage
The main method in the FlightManagementSystem class demonstrates the basic usage of the system:
1. Creating an aircraft
2. Creating airports
3. Scheduling a flight
4. Creating a user
5. Booking a seat
6. Checking in
7. Updating flight status 