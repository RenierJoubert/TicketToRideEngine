package model;

// The FindPath class determines the optimal set of routes to build for a given hand
// that maximizes player score, while working under the constraint that the player
// has access to a maximum of 45 trains to build routes. The FindPath class utilizes Dijkstra's 
// algorithm to find the shortest path to complete a given ticket, and takes into account the reusability
// factor of routes by computing the marginal value density of tickets and iterating over the player's hand
// in decreasing order.

import java.util.*;

public class FindPath {

    private GameMap map;

    // REQUIRES: map != null
    // EFFECTS: constructs the path finder
    public FindPath(GameMap map) {
        
    }

    // REQUIRES: start && end != null
    // EFFECTS: returns the shortest path between start and end cities and notes already
    // selected routes as 0 cost.
    private List<Route> dijkstra(String start, String end, Set<Route> selectedRoutes) {
        return Collections.emptyList();
    }

    // REQUIRES: ticket != null
    // EFFECTS: returns the marginal value density of completing the given ticket
    private double valueDensity(Ticket ticket, Set<Route> selectedRoutes) {
        return 0;
    }

    // REQUIRES: hand != null
    // EFFECTS: Maps marginal value density to the player's hand and iterates over selecting
    // tickets in decreasing order until train budget is reached, and recomputes densities after 
    // each addition.
    public Set<Route> findRoutes(Hand hand) {
        return Collections.emptySet();
    }




}
