package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRoute {

    private Route r1;
    
    @BeforeEach
    void runBefore() {
        r1 = new Route("Vancouver", "Seattle", 1);
    }

    @Test
    void testRouteConstructor() {
        assertEquals("Vancouver", r1.getStartCity());
        assertEquals("Seattle", r1.getEndCity());
        assertEquals(1, r1.getLength());
    }
}


