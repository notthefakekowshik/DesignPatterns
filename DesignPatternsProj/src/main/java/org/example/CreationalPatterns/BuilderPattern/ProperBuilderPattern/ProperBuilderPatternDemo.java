package org.example.CreationalPatterns.BuilderPattern.ProperBuilderPattern;

// The final product should be IMMUTABLE
class CorrectHouse {
    // All fields are final, guaranteeing immutability
    private final boolean poolAvailable;
    private final int numberOfRooms;
    private final String houseName;

    // The constructor is private and takes the builder as an argument
    // This is the ONLY time the fields are assigned.
    private CorrectHouse(CorrectHouseBuilder builder) {
        this.poolAvailable = builder.poolAvailable;
        this.numberOfRooms = builder.numberOfRooms;
        this.houseName = builder.houseName;
    }

    @Override
    public String toString() {
        return "CorrectHouse [poolAvailable=" + poolAvailable + ", numberOfRooms=" + numberOfRooms + ", houseName=" + houseName + "]";
    }

    // Static nested Builder class
    public static class CorrectHouseBuilder {
        // The builder has its OWN fields to store the configuration
        private boolean poolAvailable;
        private int numberOfRooms;
        private String houseName;

        // No-arg constructor for the builder
        public CorrectHouseBuilder() {
            // The final object is NOT created here
        }

        // Methods modify the BUILDER's fields, not the final object's
        public CorrectHouseBuilder setPoolAvailable(boolean poolAvailable) {
            this.poolAvailable = poolAvailable;
            return this;
        }

        public CorrectHouseBuilder setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public CorrectHouseBuilder setHouseName(String houseName) {
            this.houseName = houseName;
            return this;
        }

        // The build() method creates and returns the FINAL, IMMUTABLE object
        public CorrectHouse build() {
            // A new CorrectHouse is created here, and only here!
            return new CorrectHouse(this);
        }
    }
}

public class ProperBuilderPatternDemo {
    public static void main(String[] args) {
        // houseName is required by the builder's constructor
        CorrectHouse correctHouse = new CorrectHouse.CorrectHouseBuilder()
                .setNumberOfRooms(5)
                .setPoolAvailable(true)
                .build();

        CorrectHouse correctHouse1 = new CorrectHouse.CorrectHouseBuilder().build();


        System.out.println(correctHouse + " " + correctHouse1);
    }
}