package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTicket {

    private Ticket t1;
    private Ticket t2;

    @BeforeEach
    void runBefore() {
        t1 = new Ticket("Vancouver", "Portland", 3, false);
    }

    @Test
    public void testTicketConstructor() {
        assertEquals("Vancouver", t1.getStartCity());
        assertEquals("Portland", t1.getEndCity());
        assertEquals(3, t1.getPoints());
        assertFalse(t1.getStatus());
    }

    @Test
    public void testCompleteTicket() {
        t1.completeTicket();
        boolean test = t1.getStatus();
        assertTrue(test);
    }

    @Test
    public void testUncompleteTicket() {
        t1.completeTicket();
        t1.uncompleteTicket();
        boolean test = t1.getStatus();
        assertFalse(test);
    }

    @Test
    public void testUpdateScore() {
        assertEquals(-3, t1.updateScore());
        t1.completeTicket();
        assertEquals(3, t1.updateScore());
    }






}
