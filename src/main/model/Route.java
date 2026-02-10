package model;

// The Route class represents a direct adjacent connection (edge) between two vertices,
// a start city and an end city on the Ticked to Ride board. However there is no direction
// in the overlying graph so order is unimpportant. The Route class also represents a length 
// (the distance) between the start and end city measured in number of trains.
// 
// Invariants: 
// - startCity is != null
// - endCity is != null
// - length > 0


import java.util.Objects;

public class Route {
    
    private final String startCity;
    private final String endCity;
    private final int length;

}
