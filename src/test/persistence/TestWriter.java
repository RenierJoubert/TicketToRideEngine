package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// A portion of this code is modelled after code in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport

public class TestWriter {
    
    @Test
    void testWriteToInvalidFile() {
        try {
            Writer writer = new Writer("./data/\0null.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEmptyHand() {
        try {
            Hand hand = new Hand();
            Writer writer = new Writer("./data/testEmptyHand.json");
            writer.open();
            writer.write(hand);
            writer.close();

            Reader reader = new Reader("./data/testEmptyHand.json");
            hand = reader.read();
            assertEquals(0, hand.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testNaiveHand() {
        try {
            Hand hand = new Hand();
            hand.addTicket(new Ticket("Vancouver", "Seattle",  2, false));
            Writer writer = new Writer("./data/testNaiveHand.json");
            writer.open();
            writer.write(hand);
            writer.close();

            Reader reader = new Reader("./data/testNaiveHand.json");
            hand = reader.read();
            List<Ticket> tickets = hand.getTickets();
            assertEquals(1, hand.size());
            Ticket ticket = tickets.get(0);
            assertEquals("Vancouver", ticket.getStart());
            assertEquals("Seattle", ticket.getEnd());
            assertEquals(2, ticket.getPoints());
            assertFalse(ticket.getStatus());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
