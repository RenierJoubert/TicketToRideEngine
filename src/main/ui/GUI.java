package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// The GUI class represents a graphical user interface for interacting with 
// the ticket to ride app.

public class GUI extends JFrame {
    
    private static final String SAVE_FILE = "./data/hand.json";

    private Hand hand;
    private GameMap map;
    private FindPath pathFinder;
    private Writer writer;
    private Reader reader;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // EFFECTS: Constructs + initializes the GUI
    public GUI() {
        
        hand = new Hand();
        map = new GameMap();
        pathFinder = new FindPath(map);
        writer = new Writer(SAVE_FILE);
        reader = new Reader(SAVE_FILE);

    }

    // EFFECTS: creates the main menu panel displaying all options
    private JPanel mainMenu() {
        JPanel panel = new JPanel();
        return panel;
    }

    // EFFECTS: creates the panel for adding a ticket to the hand
    private JPanel addTicket() {
        JPanel panel = new JPanel();
        return panel;
    }
    
    // EFFECTS: createst the panel for removing a ticket from the hand
    private JPanel removeTicket() {
        JPanel panel = new JPanel();
        return panel;
    }
    
    // EFFECTS: creates a panel for the player to view their hand
    private JPanel viewHand() {
        JPanel panel = new JPanel();
        return panel;
    }

    // EFFECTS: creates a panel for the player to see the optimal path for their hand
    private JPanel seePath() {
        JPanel panel = new JPanel();
        return panel;
    }

    // EFFECTS: creates a panel to save the current hand to file
    private JPanel saveHand() {
        JPanel panel = new JPanel();
        return panel;
    }

    // EFFECTS: creates a panel to load a saved hand from file
    private JPanel loadHand() {
        JPanel panel = new JPanel();
        return panel;
    }
    
    

    

}
