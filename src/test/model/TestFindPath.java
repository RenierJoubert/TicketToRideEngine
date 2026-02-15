package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TestFindPath {

    private GameMap map;
    private FindPath finder;
    private Hand hand;

    @BeforeEach
    void runBefore() {
        
        map = new GameMap();
        finder = new FindPath(map);
        hand = new Hand();
    }

    @Test
    void testEmptyHand() {
        
        Set<Route> result = finder.findRoutes(hand);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNaiveTicket() {
        
        Ticket t = new Ticket("Vancouver", "Seattle", 2, false);
        hand.addTicket(t);
        Set<Route> result = finder.findRoutes(hand);

        boolean foundCorrectRoute = false;

        for (Route r : result) {
            if (r.getStartCity().equals("Vancouver")
                    && r.getEndCity().equals("Seattle")
                    && r.getLength() == 1) {
                foundCorrectRoute = true;
            }
            
        }

        assertTrue(foundCorrectRoute);

    }

    @Test
    void testOneTicket() {
        
        Ticket t = new Ticket("Vancouver", "Portland", 4, false);
        hand.addTicket(t);
        Set<Route> result = finder.findRoutes(hand);
        assertEquals(2, result.size());

        boolean foundVanSeattle = false;
        boolean foundSeattlePortland = false;

        for (Route r : result) {

            if (r.getStartCity().equals("Vancouver")
                    && r.getEndCity().equals("Seattle")
                    && r.getLength() == 1) {
                foundVanSeattle = true;
            }

            if (r.getStartCity().equals("Seattle")
                    && r.getEndCity().equals("Portland")
                    && r.getLength() == 1) {
                foundSeattlePortland = true;
            }
        }

        assertTrue(foundVanSeattle);
        assertTrue(foundSeattlePortland);

    }

    @Test
    void testReuseRoutes() {
        
        Ticket t1 = new Ticket("Vancouver", "Seattle", 2, false);
        Ticket t2 = new Ticket("Seattle", "Portland", 2, false);

        hand.addTicket(t1);
        hand.addTicket(t2);

        Set<Route> result = finder.findRoutes(hand);

        assertFalse(result.isEmpty());

        assertEquals(result.size(), result.stream().distinct().count());
    }

    @Test
    void testTrainConstraint() {
        
        Ticket t1 = new Ticket("Vancouver", "Miami", 21, false);
        Ticket t2 = new Ticket("Los Angeles", "Montreal", 20, false);
        Ticket t3 = new Ticket("Winnipeg", "El Paso", 12, false);

        hand.addTicket(t1);
        hand.addTicket(t2);
        hand.addTicket(t3);

        Set<Route> result = finder.findRoutes(hand);

        int totalLength = 0;
        for (Route r : result) {
            totalLength += r.getLength();
        }

        assertTrue(totalLength <= 45);
    }

    @Test
    void testUnreachableTicket() {
        
        Ticket t = new Ticket("Vancouver", "Paris", 5, false);
        hand.addTicket(t);

        Set<Route> result = finder.findRoutes(hand);

        assertTrue(result.isEmpty());

    }

    @Test
    void testAllTicketsUnreachable() {
        
        Ticket t1 = new Ticket("Paris", "Pretoria", 5, false);
        Ticket t2 = new Ticket("Hong Kong", "London", 5, false);

        hand.addTicket(t1);
        hand.addTicket(t2);

        Set<Route> result = finder.findRoutes(hand);

        assertTrue(result.isEmpty());
    }
    
    @Test
    void testZeroAdditionalCostDensity() {
        
        Ticket t1 = new Ticket("Vancouver", "Seattle", 2, false);
        Ticket t2 = new Ticket("Vancouver", "Seattle", 2, false);

        hand.addTicket(t1);
        hand.addTicket(t2);

        Set<Route> result = finder.findRoutes(hand);

        assertEquals(1, result.size());
    
    }
}
