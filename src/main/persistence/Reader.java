package persistence;

import model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;

// The Reader class reads the player's hand and saved path data from the stored JSON file

public class Reader {
    private String saveLocation;

    // EFFECTS: constructs a reader to read from the saved file
    public Reader(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    // EFFECTS: reads the saved Hand from file and returns it
    public Hand read() throws IOException {
        String jsonData = fileToString(saveLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHand(jsonObject);
    }


    // EFFECTS: returns the saved file as a string
    private String fileToString(String saveFile) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(saveLocation), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }

    // EFFECTS: returns parsed Hand from JSON 
    private Hand parseHand(JSONObject jsonObject) {
        Hand hand = new Hand();
        getTickets(hand, jsonObject);
        return hand;
    }

    // MODIFIES: Hand
    // EFFECTS: Parses tickets from the JSON data and adds them to the user's hand
    private void getTickets(Hand hand, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Hand");
        for (Object obj : jsonArray) {
            JSONObject nextTicket = (JSONObject) obj;
            addTicket(hand, nextTicket);
        }
    }

    private void addTicket(Hand hand, JSONObject jsonObject) {
        String start = jsonObject.getString("start");
        String end = jsonObject.getString("end");
        int points = jsonObject.getInt("points");
        boolean status = jsonObject.getBoolean("status");
        Ticket ticket = new Ticket(start, end, points, status);
        hand.addTicket(ticket);
    }



}
