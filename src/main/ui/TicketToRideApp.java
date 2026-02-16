package ui;

import model.Hand;
import model.Ticket;
import model.Route;
import model.GameMap;
import model.FindPath;

import java.util.Scanner;
import java.util.Set;

// The TicketToRideApp class represents a CLI UI for this program and allows the
// user to add a ticket to their hand with specifications of their choosing, remove a
// ticket from their hand, view all the tickets currently in their hand, and see the optimal 
// path(s) to take in order to maximize the number of points obtained.
public class TicketToRideApp {

    private final Scanner scanner;
    private final Hand hand;
    private final GameMap map;
    private final FindPath pathFinder;

    // EFFECTS: Constructs the CLI and starts the main loop
    public TicketToRideApp() {
        scanner = new Scanner(System.in);
        hand = new Hand();
        map = new GameMap();
        pathFinder = new FindPath(map);
        mainLoop();
    }

    // MODIFIES: this
    // EFFECTS: prompts user for commands until exit
    private void mainLoop() {
    }

    // EFFECTS: prints menu options to the user
    private void printMenu() {
    }

    // MODIFIES: hand
    // EFFECTS: prompts user for ticket details and adds a ticket to the hand
    private void addTicket() {
    }

    // MODIFIES: hand
    // EFFECTS: prompts user to select a ticket and removes it from the hand
    private void removeTicket() {
    }

    // EFFECTS: prints all tickets currently in the hand
    private void viewHand() {
    }

    // EFFECTS: computes the optimal routes using FindPath and prints them with total points
    private void findOptimalPath() {
    }

}
