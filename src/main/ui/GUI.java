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
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(mainMenu(), "menu");
        mainPanel.add(addTicket(), "add");
        mainPanel.add(removeTicket(), "remove");
        mainPanel.add(viewHand(), "view");
        mainPanel.add(seePath(), "path");

        add(mainPanel);
        setVisible(true);
    }

    // EFFECTS: creates the main menu panel displaying all options
    private JPanel mainMenu() {
        
        JPanel panel = new JPanel();

        JButton add = new JButton("add a ticket");
        JButton remove = new JButton("remove a ticket");
        JButton view = new JButton("view your hand");
        JButton path = new JButton("find the optimal path");
        JButton save = new JButton("save");
        JButton load = new JButton("load");

        add.addActionListener(e -> cardLayout.show(mainPanel, "add"));
        remove.addActionListener(e -> cardLayout.show(mainPanel, "remove"));
        view.addActionListener(e -> cardLayout.show(mainPanel, "view"));
        path.addActionListener(e -> cardLayout.show(mainPanel, "path"));
        save.addActionListener(e -> saveHand());
        load.addActionListener(e -> loadHand());

        panel.add(add);
        panel.add(remove);
        panel.add(view);
        panel.add(path);
        panel.add(save);
        panel.add(load);

        return panel;
    }

    // EFFECTS: creates the panel for adding a ticket to the hand
    private JPanel addTicket() {
        
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JTextField start = new JTextField();
        JTextField end = new JTextField();
        JTextField points = new JTextField();

        JButton ok = new JButton("add");
        JButton back = new JButton("back");

        panel.add(new JLabel("start city:"));
        panel.add(start);
        panel.add(new JLabel("end city:"));
        panel.add(end);
        panel.add(new JLabel("points:"));
        panel.add(points);

        panel.add(ok);
        panel.add(back);

        ok.addActionListener(e -> {
            try {
                String startCity = start.getText();
                String endCity = end.getText();
                int numPoints = Integer.parseInt(points.getText());

                hand.addTicket(new Ticket(startCity, endCity, numPoints, false));

                JOptionPane.showMessageDialog(this, "ticket added to hand");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "invalid input");
            }
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        return panel;
    }
    
    // EFFECTS: createst the panel for removing a ticket from the hand
    private JPanel removeTicket() {
        
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea text = new JTextArea();
        text.setEditable(false);

        JTextField input = new JTextField();

        JButton remove = new JButton("remove");
        JButton back = new JButton("back");

        JPanel bottom = new JPanel(new GridLayout(3, 1));
        bottom.add(new JLabel("enter ticket number to remove:"));
        bottom.add(input);
        bottom.add(remove);
        bottom.add(back);

        panel.add(new JScrollPane(text), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        remove.addActionListener(e -> {
            try {
                int index = Integer.parseInt(input.getText()) - 1;

                if (index >= 0 && index < hand.getTickets().size()) {
                    Ticket t = hand.getTickets().get(index);
                    hand.removeTicket(t);

                    JOptionPane.showMessageDialog(this, "ticket removed");
                    updateTextArea(text); // refresh immediately
                } else {
                    JOptionPane.showMessageDialog(this, "invalid number");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "invalid input");
            }
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateTextArea(text);
            }
        });

        return panel; 
    }

    private void updateTextArea(JTextArea text) {
        text.setText("");

        int i = 1;
        for (Ticket t : hand.getTickets()) {
            text.append(i + ". " + t.getStart() + " -> "
                    + t.getEnd() + " (" + t.getPoints() + ")\n");
            i++;
        }

        if (hand.getTickets().isEmpty()) {
            text.append("your hand is empty");
            }
    }
    
    // EFFECTS: creates a panel for the player to view their hand
    private JPanel viewHand() {
        
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea text = new JTextArea();
        text.setEditable(false);

        JButton back = new JButton("back");

        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.add(new JScrollPane(text), BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateTextArea(text);
            }
        });

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
