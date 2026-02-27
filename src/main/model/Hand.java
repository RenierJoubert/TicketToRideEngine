package model;

// The Hand class represents a list of tickets that the player wishes to complete,
// the players hand stores a colleciton of tickets and can evaluate the score of the player
// based on the completion status of each ticket.

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.*;

public class Hand {

    private List<Ticket> tickets;

    // EFFECTS: constructs an empty hand with no tickets
    public Hand() {
        tickets = new ArrayList<>();
    }

    // REQUIRES: ticket != null
    // MODIFIES: this
    // EFFECTS: adds ticket to hand
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // REQUIRES: ticket != null
    // MODIEFIES: this
    // EFFECTS: removes ticket from hand
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    // EFFECTS: returns number of tickets in hand
    public int size() {
        return tickets.size();
    }

    // EFFECTS: returns the total score of the hand
    public int getScore() {
        int score = 0;
        for (Ticket t : tickets) {
            score += t.updateScore();
        }
        return score;
    }

    // EFFECTS: returns true is hand contains no tickets
    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    // EFFECTS: returns the tickets in the players hand
    public List<Ticket> getTickets() {
        return tickets;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Hand", handToJson());
        return json;
    }

    public JSONArray handToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ticket t : tickets) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}
