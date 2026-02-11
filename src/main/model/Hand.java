package model;

// The Hand class represents a list of tickets that the player wishes to complete,
// the players hand stores a colleciton of tickets and can evaluate the score of the player
// based on the completion status of each ticket.

import java.util.List;

public class Hand {

    private List<Ticket> tickets;

    // EFFECTS: constructs an empty hand with no tickets
    public Hand() {

    }

    // REQUIRES: ticket != null
    // MODIFIES: this
    // EFFECTS: adds ticket to hand
    public void addTicket(Ticket ticket) {

    }

    // REQUIRES: ticket != null
    // MODIEFIES: this
    // EFFECTS: removes ticket from hand
    public void removeTicket(Ticket ticket) {

    }

    // EFFECTS: returns number of tickets in hand
    public int size() {
        return 0;
    }

    // EFFECTS: returns the total score of the hand
    public int getScore() {
        return 0;
    }

    // EFFECTS: returns true is hand contains no tickets
    public boolean isEmpty() {
        return false;
    }

}
