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
        
    }

    // MODIFIES: this
    // EFFECTS: writes user's hand to JSON file 
    public void write(Hand hand) {

    }

    // MODIFIES: this
    // EFFECTS: Initializes writer at file location
    public void open() {

    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {

    }

    // MODIFIES: this
    // EFFECTS: saves JSON to file
    public void save() {
        
    }

}
