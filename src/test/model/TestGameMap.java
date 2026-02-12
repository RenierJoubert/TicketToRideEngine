package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestGameMap {

    private GameMap map;

    @BeforeEach
    void runBefore() {
        map = new GameMap();
    }

    @Test
    void testMapConstructor() {
        List<Route> routes = map.getRoutesFrom("Vancouver");
        assertFalse(routes.isEmpty());
    }

    @Test
    void testUnknownCity() {
        List<Route> routes = map.getRoutesFrom("DrRacketVille");
        assertTrue(routes.isEmpty());
    }

    @Test
    void testFindConnections() {
        List<Route> routes = map.getRoutesFrom("Vancouver");

        boolean foundSeattle = false;
        boolean foundCalgary = false;

        for (Route r : routes) {
            if (r.getStartCity().equals("Vancouver") &&
            r.getEndCity().equals("Seattle") &&
            r.getLength() == 1) {
            foundSeattle = true;
            }

            if (r.getStartCity().equals("Vancouver") &&
               r.getEndCity().equals("Calgary") &&
               r.getLength() == 3) {
               foundCalgary = true;
               }
            
        }

        assertTrue(foundSeattle);
        assertTrue(foundCalgary);

        
    }

}
