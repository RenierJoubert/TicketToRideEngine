package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHand {

    private Hand hand;
    private Ticket t1;
    private Ticket t2;

    @BeforeEach
    void runBefore() {
        hand = new Hand();
        t1 = new Ticket("Vancouver", "Portland", 3, false);
        t2 = new Ticket("Calgary", "Helena", 5, false);
    }

    @Test
    void testHandConstructor() {
        assertTrue(hand.isEmpty());
        assertEquals(0, hand.size());
        assertEquals(0, hand.getScore());
    }

    @Test
    void testAddOneTicket() {
        hand.addTicket(t1);
        assertEquals(1, hand.size());
        boolean test = hand.isEmpty();
        assertFalse(test);
    }

    @Test
    void testAddManyTickets() {
        hand.addTicket(t1);
        hand.addTicket(t2);
        assertEquals(2, hand.size());
    }

    @Test
    void testRemoveOneTicket() {
        hand.addTicket(t1);
        assertEquals(1, hand.size());
        boolean test = hand.isEmpty();
        assertFalse(test);

        hand.removeTicket(t1);
        assertEquals(0, hand.size());
        boolean test2 = hand.isEmpty();
        assertTrue(test2);
    }

    @Test
    void testRemoveManyTickets() {
        hand.addTicket(t1);
        hand.addTicket(t2);
        assertEquals(2, hand.size());

        hand.removeTicket(t1);
        hand.removeTicket(t2);
        assertEquals(0, hand.size());
        boolean test3 = hand.isEmpty();
        assertTrue(test3);
    }

    @Test
    void testGetScoreAllIncomplete() {
        hand.addTicket(t1);
        hand.addTicket(t2);

        assertEquals(-8, hand.getScore());
    }

    @Test
    void testGetScoreAllComplete() {
        hand.addTicket(t1);
        hand.addTicket(t2);

        t1.completeTicket();
        t2.completeTicket();

        assertEquals(8, hand.getScore());
    }

    @Test
    void testGetScoreMixedCompletion() {
        hand.addTicket(t1);
        hand.addTicket(t2);

        t1.completeTicket();

        assertEquals(-2, hand.getScore());
    }

}
