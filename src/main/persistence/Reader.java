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

    }

    // EFFECTS: reads the saved Hand from file and returns it
    public Hand read() throws IOException {
        return null;
    }


    // EFFECTS: returns the saved file as a string
    private String fileToString(String saveFile) throws IOException {
        return "";
    }

    // EFFECTS: returns parsed Hand from JSON 
    private Hand parseHand(JSONObject json) {
        return null;
    }

    // MODIFIES: Hand
    // EFFECTS: Parses tickets from the JSON data and adds them to the user's hand
    private void getTickets(Hand hand, JSONObject json) {

    }



}
