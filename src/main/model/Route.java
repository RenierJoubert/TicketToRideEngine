package model;

// The Route class represents a direct adjacent connection (edge) between two vertices,
// a start city and an end city on the Ticked to Ride board. However there is no direction
// in the overlying graph so order is unimpportant. The Route class also represents a length 
// (the distance) between the start and end city measured in number of trains.

public class Route {
    
    private final String startCity;
    private final String endCity;
    private final int length;

    // REQUIRES: startCity != null, endCity != null, and length > 0
    // EFFECTS: Instantiates a route with a start city, end city, and length
    public Route(String startCity, String endCity, int length) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.length = length;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public int getLength() {
        return length;
    }

}
