package ui;

import model.Hand;
import model.Ticket;
import model.Route;
import model.GameMap;
import model.FindPath;
import model.Path;
import java.util.Scanner;
import persistence.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// The TicketToRideApp class represents a CLI UI for this program and allows the
// user to add a ticket to their hand with specifications of their choosing, remove a
// ticket from their hand, view all the tickets currently in their hand, and see the optimal 
// path(s) to take in order to maximize the number of points obtained.

// A portion of this code is modelled after code in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class TicketToRideApp {

    private final Scanner scanner;
    private Hand hand;
    private final GameMap map;
    private final FindPath pathFinder;

    private Writer writer;
    private Reader reader;
    private static final String SAVE_FILE = "./data/hand.json";

    // EFFECTS: Constructs the CLI and starts the main loop
    public TicketToRideApp() {
        scanner = new Scanner(System.in);
        hand = new Hand();
        map = new GameMap();
        pathFinder = new FindPath(map);
        writer = new Writer(SAVE_FILE);
        reader = new Reader(SAVE_FILE);
        mainLoop();
    }

    // MODIFIES: this
    // EFFECTS: prompts user for commands until exit
    private void mainLoop() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": addTicket();
                    break;
                case "2": removeTicket();
                    break;
                case "3": viewHand();
                    break;
                case "4": findOptimalPath();
                    break;
                case "5": loadHand();
                    break;
                case "6": saveHand();
                    break;
                case "0": running = false;
                    System.out.println("Closing TicketToRideApp");
                    break;
                default: System.out.println("Invalid key");
            }
        }
    }

    // EFFECTS: prints menu options to the user
    private void printMenu() {
        System.out.println(" _____ _      _        _     _            __ _     _      \n" 
                        + //
                        "/__   (_) ___| | _____| |_  | |_ ___     /__(_) __| | ___ \n" 
                        + //
                        "  / /\\/ |/ __| |/ / _ \\ __| | __/ _ \\   / \\// |/ _` |/ _ \\\n" 
                        + //
                        " / /  | | (__|   <  __/ |_  | || (_) | / _  \\ | (_| |  __/\n" 
                        + //
                        " \\/   |_|\\___|_|\\_\\___|\\__|  \\__\\___/  \\/ \\_/_|\\__,_|\\___|\n" 
                        + //
                        "                                                          ");
        System.out.println("1 - Add a Ticket");
        System.out.println("2 - Remove a Ticket");
        System.out.println("3 - View Hand");
        System.out.println("4 - Find Optimal Path");
        System.out.println("5 - Load Save File");
        System.out.println("6 - Save Your Hand");
        System.out.println("0 - Exit");
        System.out.print("Choose an option: \n");
    }

    // MODIFIES: hand
    // EFFECTS: prompts user for ticket details and adds a ticket to the hand
    private void addTicket() {
        System.out.print("Enter start city: ");
        String start = scanner.nextLine();
        System.out.print("Enter end city: ");
        String end = scanner.nextLine();
        System.out.print("Enter points for ticket: ");
        int points = Integer.parseInt(scanner.nextLine());

        Ticket ticket = new Ticket(start, end, points, false);
        hand.addTicket(ticket);
        System.out.println("Ticket added: " + start + " -> " + end + " (" + points + " points)");
    }

    // MODIFIES: hand
    // EFFECTS: prompts user to select a ticket and removes it from the hand
    private void removeTicket() {
        if (hand.getTickets().isEmpty()) {
            System.out.println("Your hand is empty.");
            return;
        }

        int i = 1;
        for (Ticket t : hand.getTickets()) {
            System.out.println(i + ": " + t.getStart() + " -> " + t.getEnd() + " (" + t.getPoints() + " points)");
            i++;
        }
        System.out.print("Enter the number of the ticket to remove: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice >= 1 && choice <= hand.getTickets().size()) {
            Ticket removed = (Ticket) hand.getTickets().toArray()[choice - 1];
            hand.removeTicket(removed);
            System.out.println("Removed ticket: " + removed.getStart() + " -> " + removed.getEnd());
        } else {
            System.out.println("Invalid key");
        }
    }

    // EFFECTS: prints all tickets currently in the hand
    private void viewHand() {
        if (hand.getTickets().isEmpty()) {
            System.out.println("Your hand is empty.");
            return;
        }
        System.out.println("Current hand:");
        for (Ticket t : hand.getTickets()) {
            System.out.println("- " + t.getStart() + " -> " + t.getEnd() + " (" + t.getPoints() + " points)");
        }
    }

    // EFFECTS: finds the optimal routes using FindPath and prints them with total points and trains used
    private void findOptimalPath() {
        if (hand.getTickets().isEmpty()) {
            System.out.println("No tickets in hand.");
            return;
        }

        Path path = pathFinder.computePath(hand);

        System.out.println("Optimal Routes:");
        int totalTrains = 0;
        for (Route r : path.getRoutes()) {
            System.out.println("- " + r.getStartCity() + " to " + r.getEndCity() + " (" + r.getLength() + " trains)");
            totalTrains += r.getLength();
        }

        System.out.println("Total trains used: " + totalTrains);
        System.out.println("Total points earned: " + path.getScore());

        System.out.println("Completed Tickets:");
        for (Ticket t : path.getCompletedTickets()) {
            System.out.println("- " + t.getStart() + " to " + t.getEnd() + " (" + t.getPoints() + " points)");
        }

        System.out.println("Incomplete Tickets:");
        for (Ticket t : path.getIncompleteTickets()) {
            System.out.println("- " + t.getStart() + " to " + t.getEnd() + " (" + t.getPoints() + " points)");
        }
    }

    // EFFECTS: Saves the current user hand to SAVE_FILE
    private void saveHand() {
        try {
            writer.open();
            writer.write(hand);
            writer.close();
            System.out.println("Hand Saved to " + SAVE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println(SAVE_FILE + "Not Found");
        }
    }

    // MODIFIES: hand
    // EFFECTS: loads the saved hand from file
    private void loadHand() {
        try {
            hand = reader.read();
            System.out.println("Hand loaded from file" + SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read file: " + SAVE_FILE);
        }
    }

}
