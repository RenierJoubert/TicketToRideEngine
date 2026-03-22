package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// The GUI class represents a graphical user interface for interacting with 
// the ticket to ride app.

@ExcludeFromJacocoGeneratedReport
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

        setTitle("Ticket To Ride");
        setSize(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(mainMenu(), "menu");
        mainPanel.add(addTicket(), "add");
        mainPanel.add(viewHand(), "hand");
        mainPanel.add(seePath(), "path");

        add(mainPanel);
        setVisible(true);
    }

    // EFFECTS: creates the main menu panel displaying all options
    private JPanel mainMenu() {
        JPanel panel = new JPanel();

        JButton add = new JButton("add a ticket");
        JButton view = new JButton("view your hand");
        JButton path = new JButton("find the optimal path");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");

        add.addActionListener(e -> cardLayout.show(mainPanel, "add"));
        view.addActionListener(e -> cardLayout.show(mainPanel, "view"));
        path.addActionListener(e -> cardLayout.show(mainPanel, "path"));
        save.addActionListener(e -> saveHand());
        load.addActionListener(e -> loadHand());

        panel.add(add);
        panel.add(view);
        panel.add(path);
        panel.add(save);
        panel.add(load);

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
