package org.example.BehavioralPatterns.ChainOfResp;

import lombok.Getter;

class HelpDeskTicket {
    @Getter
    private String issue;
    private final boolean needsTechnicalSupport;
    private final boolean needsManagerialApproval;

    public HelpDeskTicket(String issue, boolean needsTechnicalSupport, boolean needsManagerialApproval) {
        this.issue = issue;
        this.needsTechnicalSupport = needsTechnicalSupport;
        this.needsManagerialApproval = needsManagerialApproval;
    }

    public boolean needsTechnicalSupport() {
        return needsTechnicalSupport;
    }

    public boolean needsManagerialApproval() {
        return needsManagerialApproval;
    }
}

// Handler interface
interface HelpDeskHandler {
    void processTicket(HelpDeskTicket ticket);

    void setNextHandler(HelpDeskHandler handler);
}

// Concrete handler for basic ticket validation
class BasicValidationHandler implements HelpDeskHandler {
    private HelpDeskHandler nextHandler;

    public void setNextHandler(HelpDeskHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void processTicket(HelpDeskTicket ticket) {
        System.out.println("BasicValidationHandler is processing the ticket.");
        // Perform basic validation logic

        if (nextHandler != null) {
            nextHandler.processTicket(ticket);
        } else {
            System.out.println("simple basic validator is enough. he has done the task");
        }
    }
}

// Concrete handler for technical support
class TechnicalSupportHandler implements HelpDeskHandler {
    private HelpDeskHandler nextHandler;

    public void setNextHandler(HelpDeskHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void processTicket(HelpDeskTicket ticket) {
        if (ticket.needsTechnicalSupport()) {
            System.out.println("TechnicalSupportHandler is providing technical support.");
            // Perform technical support logic
        }

        if (nextHandler != null) {
            nextHandler.processTicket(ticket);
        }
    }
}

// Concrete handler for managerial approval
class ManagerialApprovalHandler implements HelpDeskHandler {
    @Override
    public void processTicket(HelpDeskTicket ticket) {
        if (ticket.needsManagerialApproval()) {
            System.out.println("ManagerialApprovalHandler is granting managerial approval.");
            // Perform managerial approval logic
        } else {
            System.out.println("ManagerialApprovalHandler is not required for this ticket.");
        }
    }

    @Override
    public void setNextHandler(HelpDeskHandler handler) {
        // manager is the top as of now.
    }
}

// Client class
public class HelpDeskExample {
    public static void main(String[] args) {
        // Create handlers
        HelpDeskHandler basicValidationHandler = new BasicValidationHandler();
        HelpDeskHandler technicalSupportHandler = new TechnicalSupportHandler();
        HelpDeskHandler managerialApprovalHandler = new ManagerialApprovalHandler();

        // Set up the chain of responsibility
        //basicValidationHandler.setNextHandler(technicalSupportHandler);
        technicalSupportHandler.setNextHandler(managerialApprovalHandler);

        // Create a help desk ticket
        HelpDeskTicket ticket = new HelpDeskTicket("Network issue", false, false);

        // Process the help desk ticket
        basicValidationHandler.processTicket(ticket);
    }
}

