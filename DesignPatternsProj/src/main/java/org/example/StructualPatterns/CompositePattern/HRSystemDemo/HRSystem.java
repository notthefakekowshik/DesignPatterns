package org.example.StructualPatterns.CompositePattern.HRSystemDemo;

import java.util.ArrayList;
import java.util.List;

// 1. The Component Interface
// Defines what operations are common to everyone (Interns, CEOs, Departments)
interface OrgComponent {
    double calculateTotalCost();
    void showHierarchy(int level);
}

// 2. The Leaf (Individual Contributors: Interns, SDEs, etc.)
class IndividualContributor implements OrgComponent {
    private String name;
    private String role;
    private double salary;

    public IndividualContributor(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    @Override
    public double calculateTotalCost() {
        return this.salary;
    }

    @Override
    public void showHierarchy(int level) {
        String indent = "  ".repeat(level);
        System.out.println(indent + "- " + role + ": " + name + " [$" + salary + "]");
    }
}

// 3. The Composite (Manager)
// A Manager is an Employee (has salary) BUT also holds a list of subordinates
class Manager implements OrgComponent {
    private String name;
    private double salary;
    private List<OrgComponent> team = new ArrayList<>();

    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void addTeamMember(OrgComponent member) {
        if (!(member instanceof Manager)) {
            team.add(member);
        } else {
            System.out.println("Can't add manager to manager!!!");
        }
    }

    @Override
    public double calculateTotalCost() {
        double totalCost = this.salary; // Start with manager's own salary

        // Add the cost of the entire team recursively
        for (OrgComponent member : team) {
            totalCost += member.calculateTotalCost();
        }
        return totalCost;
    }

    @Override
    public void showHierarchy(int level) {
        String indent = "  ".repeat(level);
        System.out.println(indent + "+ Manager: " + name + " [$" + salary + "]");

        for (OrgComponent member : team) {
            member.showHierarchy(level + 1);
        }
    }
}

// 4. The Composite (Department)
// A Department is a pure container. It doesn't have a salary, it just holds people.
class Department implements OrgComponent {
    private String name;
    private List<OrgComponent> members = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addMember(OrgComponent member) {
        members.add(member);
    }

    @Override
    public double calculateTotalCost() {
        double totalCost = 0;
        for (OrgComponent member : members) {
            totalCost += member.calculateTotalCost();
        }
        return totalCost;
    }

    @Override
    public void showHierarchy(int level) {
        String indent = "  ".repeat(level);
        System.out.println(indent + "* DEPARTMENT: " + name);
        for (OrgComponent member : members) {
            member.showHierarchy(level + 1);
        }
    }
}

// 5. Client Code
public class HRSystem {
    public static void main(String[] args) {
        // --- ENGINEERING DEPARTMENT ---

        // 1. Create Interns & ICs
        OrgComponent intern1 = new IndividualContributor("Alice", "Intern", 20000);
        OrgComponent sde1 = new IndividualContributor("Bob", "SDE-1", 80000);
        OrgComponent sde2 = new IndividualContributor("Charlie", "SDE-2", 120000);

        // 2. Create an Engineering Manager and add the ICs
        Manager engManager = new Manager("Dave", 160000);
        engManager.addTeamMember(sde1);
        engManager.addTeamMember(sde2);
        engManager.addTeamMember(intern1); // Interns report to Manager too

        // --- SALES DEPARTMENT ---

        // 1. Create Sales people
        OrgComponent salesRep1 = new IndividualContributor("Eve", "Sales Rep", 50000);
        OrgComponent salesRep2 = new IndividualContributor("Frank", "Sales Rep", 50000);

        // 2. Create Sales Manager
        Manager salesManager = new Manager("Grace", 100000);
        salesManager.addTeamMember(salesRep1);
        salesManager.addTeamMember(salesRep2);

        // --- HEAD OFFICE (Root) ---

        // Create the Department Container
        Department engineeringDept = new Department("Engineering");
        engineeringDept.addMember(engManager);

        Department salesDept = new Department("Sales");
        salesDept.addMember(salesManager);

        // A generic list of all departments
        Department headOffice = new Department("Head Office");
        headOffice.addMember(engineeringDept);
        headOffice.addMember(salesDept);

        // --- CALCULATIONS ---

        System.out.println("--- Organization Hierarchy ---");
        headOffice.showHierarchy(0);

        System.out.println("\n--- Cost Calculations ---");
        // We can treat a Department, a Manager, and an Intern exactly the same!
        System.out.println("Total Cost of Engineering Dept: $" + engineeringDept.calculateTotalCost());
        System.out.println("Total Cost of Sales Dept:       $" + salesDept.calculateTotalCost());
        System.out.println("Total Cost of Entire Company:   $" + headOffice.calculateTotalCost());
    }
}





/*

// BAD CODE (The Anti-Pattern)
class Department {
    List<Manager> managers;
    List<Intern> interns;
    List<SDE> sdes;

    double getCost() {
        double total = 0;
        for (Manager m : managers) total += m.salary + m.getReporteesCost();
        for (Intern i : interns) total += i.stipend;
        for (SDE s : sdes) total += s.salary;
        return total;
    }
}

Critique:
Every time HR creates a new role (e.g., "Contractor"), you have to open the Department class, add a List<Contractor>, and update the getCost() loop.
With Composite, you just create class Contractor implements OrgComponent, and the Department code never changes.
This is the essence of the Open/Closed Principle.

 */