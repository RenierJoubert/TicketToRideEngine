package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// The Main class launches the TicketToRide CLI.
@ExcludeFromJacocoGeneratedReport
public class Main {

    // EFFECTS: creates the TicketToRideApp GUI
    public static void main(String[] args) {
        new GUI();
        new TicketToRideApp();
    }
}

