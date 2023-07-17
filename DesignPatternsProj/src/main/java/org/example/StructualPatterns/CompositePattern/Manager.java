package org.example.StructualPatterns.CompositePattern;

public class Manager implements Employee{

    @Override
    public void showDetails() {
        System.out.println("showing details of manager");
    }

    public void manageTeam() {
        System.out.println("managing team");
    }


}
