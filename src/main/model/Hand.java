package model;

// The Hand class represents a list of tickets that the player wishes to complete,
// the players hand stores a colleciton of tickets and can evaluate the score of the player
// based on the completion status of each ticket.

// A portion of this code is modelled after code in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Hand {

    private List<Ticket> tickets;

    // EFFECTS: constructs an empty hand with no tickets
    public Hand() {
        tickets = new ArrayList<>();
    }

    // REQUIRES: ticket != null
    // MODIFIES: this
    // EFFECTS: adds ticket to hand and logs it
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        EventLog.getInstance().logEvent(
            new Event("added ticket: " + ticket.getStart() + " -> " + ticket.getEnd()
                + " (" + ticket.getPoints() + " pts)")
        );
    }

    // REQUIRES: ticket != null
    // MODIEFIES: this
    // EFFECTS: removes ticket from hand and logs it
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        EventLog.getInstance().logEvent(
            new Event("removed ticket: " + ticket.getStart() + " -> " + ticket.getEnd())
        );
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

    // EFFECTS: Returns the user's hand as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Hand", handToJson());
        return json;
    }

    // EFFECTS: returns the tickets in the user's hand as a JSONArray
    public JSONArray handToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ticket t : tickets) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}
