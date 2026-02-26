package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport

public class TestReader {

    @Test
    void testReaderFileNull() {
        
        Reader reader = new Reader("./data/null");
        
        try {
            Hand hand = reader.read();
            fail("IOexception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHand() {

        Reader reader = new Reader("./data/testReaderEmptyHand.json");

        try {
            Hand hand = reader.read();
            assertEquals(hand.size(), 0);
        } catch (IOException e) {
            fail("File read failure");
        }
    }

    @Test
    void testReadingNaiveHand() {

        Reader reader = new Reader("./data/testReaderNaiveHand.json");

        try {
            Hand hand = reader.read();
            List<Ticket> tickets = hand.getTickets();
            assertEquals(1, hand.size());
            Ticket ticket = tickets.get(1);
            assertEquals("Vancouver", ticket.getStart());
            assertEquals("Seattle", ticket.getEnd());
            assertEquals(2, ticket.getPoints());
            assertFalse(ticket.getStatus());
        } catch (IOException e) {
            fail("File read failure");
        }
    }
    
}
