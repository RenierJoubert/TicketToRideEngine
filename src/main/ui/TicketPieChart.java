package ui;

import javax.swing.*;
import java.awt.*;

public class TicketPieChart extends JPanel {
    private int completed;
    private int incomplete;

    public TicketPieChart(int completed, int incomplete) {
        setPreferredSize(new Dimension(300, 300));
        this.completed = completed;
        this.incomplete = incomplete;
    }

    public void setData(int completed, int incomplete) {
        this.completed = completed;
        this.incomplete = incomplete;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int total = completed + incomplete;
        if (total == 0) {
            g.drawString("your hand is empty", 20, 20);
            return;
        }

        int completedAngle = (int) Math.round(360.0 * completed / total);

        
        g.setColor(Color.GREEN);
        g.fillArc(50, 50, 200, 200, 0, completedAngle);

        
        g.setColor(Color.RED);
        g.fillArc(50, 50, 200, 200, completedAngle, 360 - completedAngle);

        
        g.setColor(Color.BLACK);
        g.drawString("completed: " + completed, 50, 270);
        g.drawString("incomplete: " + incomplete, 50, 290);
    }
}