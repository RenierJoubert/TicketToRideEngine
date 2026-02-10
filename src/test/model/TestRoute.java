package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRoute {
    
    @BeforeEach
    void runBefore() {
        Route r1 = new Route("Vancouver", "Seattle", 1);
        Route r2 = new Route("Los Angeles", "Las Vegas", 2);
    }

    @Test
    void testRouteConstructor() {
        assertEquals("Vancouver", r1.getStartCity());
        assertEquals("Seattle", r1.getEndCity());
        assertEquals(1, r1.getLength());
    }
}


