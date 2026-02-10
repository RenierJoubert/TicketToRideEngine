package model;

// The Ticket class represents a 'card' in the game ticket to ride, namely, a ticket
// represents a start city and an end city of arbitrary distance from each other, a +-point value
// associated with completion of the ticket, and a completion status (completed or not completed).
// Additionally, there is no direction in the gameMap so the start city and end city are interchangeable.

public class Ticket {

    private String start;
    private String end;
    private int points;
    private boolean status;


    // REQUIRES: startCity != null, endCity != null, and points > 0
    // EFFECTS: Instantiates a ticket that has not been completed yet with a start city, end city, and points.
    public Ticket(String start, String end, int points, boolean status) {
        this.start = start;
        this.end = end;
        this.points = points;
        this.status = false;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getPoints() {
        return points;
    }

    public boolean getStatus() {
        return status;
    }

    public void completeTicket() {
        status = true;
    }

    public void uncompleteTicket() {
        status = false;
    }

    // EFFECTS: returns +points if ticket status is completed, returns -points if ticket is not completed
    public int updateScore() {
        if (status) {
            return points;
        }
        return -points; 
    }
}
