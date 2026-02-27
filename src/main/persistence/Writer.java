package persistence;

import model.*;
import org.json.JSONObject;
import java.io.*;

// The Writer class writes the current TicketToRide game state to a JSON file and saves it 

public class Writer {

    private String fileName;
    private PrintWriter writer;

    // EFFECTS: constructs a writer to write the game state to file fileName
    public Writer(String fileName) {
        this.fileName = fileName;
    }

    // MODIFIES: this
    // EFFECTS: writes user's hand to JSON file 
    public void write(Hand hand) {
        JSONObject json = hand.toJson();
        save(json.toString());
    }

    // MODIFIES: this
    // EFFECTS: Initializes writer at file location
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileName));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves JSON to file
    public void save(String json) {
        writer.print(json);
    }

}
