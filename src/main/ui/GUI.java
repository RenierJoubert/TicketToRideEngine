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
        mainPanel.add(saveHand(), "save");
        mainPanel.add(loadHand(), "load");

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
        save.addActionListener(e -> cardLayout.show(mainPanel, "save"));
        load.addActionListener(e -> cardLayout.show(mainPanel, "load"));

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

        ok.addActionListener(e -> handleAdd(start, end, points));
        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        return panel;
    }

    // EFFECTS: ticket adding helper method for addTicket
    private void handleAdd(JTextField start, JTextField end, JTextField points) {
        try {
            String s = start.getText();
            String e = end.getText();
            int p = Integer.parseInt(points.getText());

            hand.addTicket(new Ticket(s, e, p, false));
            JOptionPane.showMessageDialog(this, "ticket added to hand");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "invalid input");
        }
    }
    
    // EFFECTS: creates the panel for removing a ticket from the hand
    private JPanel removeTicket() {
        
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea text = new JTextArea();
        text.setEditable(false);

        JTextField input = new JTextField();

        JButton remove = new JButton("remove");
        JButton back = new JButton("back");

        JPanel bottom = new JPanel(new GridLayout(4, 1));
        bottom.add(new JLabel("enter ticket number to remove:"));
        bottom.add(input);
        bottom.add(remove);
        bottom.add(back);

        panel.add(new JScrollPane(text), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        remove.addActionListener(e -> handleRemove(input, text));
        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @ExcludeFromJacocoGeneratedReport
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateTextArea(text);
            }
        });

        return panel;
    }
    

    // EFFECTS: ticket removing helper method for removeTicket
    private void handleRemove(JTextField input, JTextArea text) {
        try {
            int index = Integer.parseInt(input.getText()) - 1;

            if (index >= 0 && index < hand.getTickets().size()) {
                Ticket t = hand.getTickets().get(index);
                hand.removeTicket(t);

                JOptionPane.showMessageDialog(this, "ticket removed");
                updateTextArea(text);
            } else {
                JOptionPane.showMessageDialog(this, "invalid number");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "invalid input");
        }
    }

    // EFFECTS: updates text area with current tickets in hand
    private void updateTextArea(JTextArea text) {
       
        text.setText("");

        int i = 1;

        if (hand.getTickets().isEmpty()) {
            text.append("your hand is empty");
        }
        
        for (Ticket t : hand.getTickets()) {
            text.append(i + ". " + t.getStart() + " -> "
                    + t.getEnd() + " (" + t.getPoints() + ")\n");
            i++;
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
            @ExcludeFromJacocoGeneratedReport
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateTextArea(text);
            }
        });

        return panel;
    }

    // EFFECTS: creates a panel for the player to see the optimal path for their hand
    private JPanel seePath() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea text = new JTextArea();
        text.setEditable(false);

        JButton back = new JButton("back");
        JPanel bottom = new JPanel();
        bottom.add(back);
        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @ExcludeFromJacocoGeneratedReport
            public void componentShown(java.awt.event.ComponentEvent e) {
                updatePathText(text);
            }
        });

        panel.add(new JScrollPane(text), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    // EFFECTS: seePath helper method that gets the used routes and calls to check ticket status
    private void updatePathText(JTextArea text) {
        text.setText("");
        if (hand.getTickets().isEmpty()) {
            text.append("your hand is empty.\n");
            return;
        }

        Path path = pathFinder.computePath(hand);

        updateRoutesSummary(text, path);
        updateTickets(text, path); 
    }

    // EFFECTS: seePath helper method that gets the routes chosen and trains used
    private void updateRoutesSummary(JTextArea text, Path path) {
        text.append("optimal routes:\n");
        int totalTrains = 0;
        for (Route r : path.getRoutes()) {
            text.append("- " + r.getStartCity() + " -> " + r.getEndCity() 
                    + " (" + r.getLength() + " trains)\n");
            totalTrains += r.getLength();
        }
        text.append("\ntotal trains used: " + totalTrains + "\n");
        text.append("total points earned: " + path.getScore() + "\n\n");
    }

    // EFFECTS: seePath helper method that gets complete and incomplete tickets
    private void updateTickets(JTextArea text, Path path) {
        text.append("completed Tickets:\n");
        for (Ticket t : path.getCompletedTickets()) {
            text.append("- " + t.getStart() + " -> " + t.getEnd() 
                    + " (" + t.getPoints() + " points)\n");
        }

        text.append("\nincomplete Tickets:\n");
        for (Ticket t : path.getIncompleteTickets()) {
            text.append("- " + t.getStart() + " -> " + t.getEnd() 
                    + " (" + t.getPoints() + " points)\n");
        }
    }

    // EFFECTS: creates a panel to save the current hand to file
    private JPanel saveHand() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("save your current hand?", SwingConstants.CENTER);
        JButton save = new JButton("save");
        JButton back = new JButton("back");
        JPanel buttons = new JPanel();
        buttons.add(save);
        buttons.add(back);

        panel.add(label, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        save.addActionListener(e -> {
            try {
                writer.open();
                writer.write(hand);
                writer.close();

                JOptionPane.showMessageDialog(this, "hand saved successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "failed to save");
            }
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        return panel;
    }

    // EFFECTS: creates a panel to load a saved hand from file
    private JPanel loadHand() {
        
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel();
        
        JLabel label = new JLabel("load saved hand?", SwingConstants.CENTER);

        JButton load = new JButton("load");
        JButton back = new JButton("back");
        
        buttons.add(load);
        buttons.add(back);

        panel.add(label, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        load.addActionListener(e -> {
            try {
                hand = reader.read();  
                JOptionPane.showMessageDialog(this, "hand loaded successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "failed to load");
            }
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        return panel;
    }    

}
