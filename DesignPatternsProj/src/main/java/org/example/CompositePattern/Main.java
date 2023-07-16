package org.example.CompositePattern;

public class Main {

    public static void main(String[] args) {

        Employee devEmp = new Developer();
        devEmp.showDetails();
        ((Developer) devEmp).writeCode();

        Employee managerEmp = new Manager();
        managerEmp.showDetails();
        ((Manager) managerEmp).manageTeam();

    }
}
