package model;

// The GameMap class represents the static, undirected, and weighted game board in Ticket to Ride North America,
// such that cities are vertices and routes are edges of fixed length (in number of trains), all routes are created
// at construction and cannot be modified after.

import java.util.*;

public class GameMap {

    private Map<String, List<Route>> adjacencyMap;
    
    // EFFECTS: constructs the complete board
    public GameMap() {
        adjacencyMap = new HashMap<>();
        initializeRoutes();
    }

    // MODIFIES: this
    // EFFECTS: adds all routes 
    @SuppressWarnings("methodlength") 
    private void initializeRoutes() {
        addRoute(new Route("Vancouver", "Seattle", 1));
        addRoute(new Route("Vancouver", "Calgary", 3));
        addRoute(new Route("Seattle", "Portland", 1));
        addRoute(new Route("Seattle", "Helena", 6));
        addRoute(new Route("Seattle", "Calgary", 4));
        addRoute(new Route("Calgary", "Winnipeg", 6));
        addRoute(new Route("Calgary", "Helena", 4));
        addRoute(new Route("Calgary", "Seattle", 4));
        addRoute(new Route("Calgary", "Vancouver", 3));
        addRoute(new Route("Winnipeg", "Sault S Marie", 6));
        addRoute(new Route("Winnipeg", "Duluth", 4));
        addRoute(new Route("Winnipeg", "Helena", 4));
        addRoute(new Route("Winnipeg", "Calgary", 6));
        addRoute(new Route("Sault S Marie", "Montreal", 5));
        addRoute(new Route("Sault S Marie", "Toronto", 2));
        addRoute(new Route("Sault S Marie", "Duluth", 3));
        addRoute(new Route("Sault S Marie", "Winnipeg", 6));
        addRoute(new Route("Montreal", "Boston", 2));
        addRoute(new Route("Montreal", "New York", 3));
        addRoute(new Route("Montreal", "Toronto", 3));
        addRoute(new Route("Montreal", "Sault S Marie", 5));
        addRoute(new Route("Boston", "Montreal", 2));
        addRoute(new Route("Boston", "New York", 2));
        addRoute(new Route("New York", "Boston", 2));
        addRoute(new Route("New York", "Montreal", 3));
        addRoute(new Route("New York", "Washington", 2));
        addRoute(new Route("New York", "Pittsburgh", 2));
        addRoute(new Route("Toronto", "Sault S Marie", 2));
        addRoute(new Route("Toronto", "Montreal", 3));
        addRoute(new Route("Toronto", "Pittsburgh", 2));
        addRoute(new Route("Toronto", "Chicago", 3));
        addRoute(new Route("Toronto", "Duluth", 6));
        addRoute(new Route("Duluth", "Winnipeg", 4));
        addRoute(new Route("Duluth", "Sault S Marie", 3));
        addRoute(new Route("Duluth", "Toronto", 6));
        addRoute(new Route("Duluth", "Chicago", 3));
        addRoute(new Route("Duluth", "Omaha", 2));
        addRoute(new Route("Duluth", "Helena", 6));
        addRoute(new Route("Helena", "Calgary", 4));
        addRoute(new Route("Helena", "Winnipeg", 4));      
        addRoute(new Route("Helena", "Duluth", 6));
        addRoute(new Route("Helena", "Omaha", 5));
        addRoute(new Route("Helena", "Denver", 4));
        addRoute(new Route("Helena", "Salt Lake City", 3));
        addRoute(new Route("Helena", "Seattle", 6));
        addRoute(new Route("Portland", "Seattle", 1));
        addRoute(new Route("Portland", "Salt Lake City", 6));
        addRoute(new Route("Portland", "San Francisco", 5));
        addRoute(new Route("Salt Lake City", "Portland", 6));
        addRoute(new Route("Salt Lake City", "Helena", 3));
        addRoute(new Route("Salt Lake City", "Denver", 3));
        addRoute(new Route("Salt Lake City", "Las Vegas", 3));
        addRoute(new Route("Salt Lake City", "San Francisco", 5));
        addRoute(new Route("San Francisco", "Portland", 5));
        addRoute(new Route("San Francisco", "Salt Lake City", 5));
        addRoute(new Route("San Francisco", "Los Angeles", 3));
        addRoute(new Route("Denver", "Salt Lake City", 3));
        addRoute(new Route("Denver", "Helena", 4));
        addRoute(new Route("Denver", "Omaha", 4));
        addRoute(new Route("Denver", "Kansas City", 4));
        addRoute(new Route("Denver", "Oklahoma City", 4));
        addRoute(new Route("Denver", "Santa Fe", 2));
        addRoute(new Route("Denver", "Phoenix", 5));
        addRoute(new Route("Omaha", "Helena", 5));
        addRoute(new Route("Omaha", "Duluth", 2));
        addRoute(new Route("Omaha", "Chicago", 4));
        addRoute(new Route("Omaha", "Kansas City", 1));
        addRoute(new Route("Omaha", "Denver", 4));
        addRoute(new Route("Chicago", "Duluth", 3));
        addRoute(new Route("Chicago", "Toronto", 4));
        addRoute(new Route("Chicago", "Pittsburgh", 3));
        addRoute(new Route("Chicago", "Saint Louis", 2));
        addRoute(new Route("Chicago", "Omaha", 4));
        addRoute(new Route("Pittsburgh", "Toronto", 2));
        addRoute(new Route("Pittsburgh", "New York", 2));
        addRoute(new Route("Pittsburgh", "Washington", 2));
        addRoute(new Route("Pittsburgh", "Raleigh", 2));
        addRoute(new Route("Pittsburgh", "Nashville", 4));
        addRoute(new Route("Pittsburgh", "Saint Louis", 4));
        addRoute(new Route("Pittsburgh", "Chicago", 3));
        addRoute(new Route("Washington", "New York", 2));
        addRoute(new Route("Washington", "Pittsburgh", 2));
        addRoute(new Route("Washington", "Raleigh", 2));
        addRoute(new Route("Raleigh", "Pittsburgh", 2));
        addRoute(new Route("Raleigh", "Washington", 2));
        addRoute(new Route("Raleigh", "Charleston", 2));
        addRoute(new Route("Raleigh", "Nashville", 2));
        addRoute(new Route("Raleigh", "Atlanta", 2));
        addRoute(new Route("Charleston", "Raleigh", 2));
        addRoute(new Route("Charleston", "Atlanta", 2));
        addRoute(new Route("Charleston", "Miami", 4));
        addRoute(new Route("Nashville", "Pittsburgh", 4));
        addRoute(new Route("Nashville", "Raleigh", 3));
        addRoute(new Route("Nashville", "Atlanta", 1));
        addRoute(new Route("Nashville", "Little Rock", 3));
        addRoute(new Route("Nashville", "Saint Louis", 2));
        addRoute(new Route("Atlanta", "Raleigh", 2));
        addRoute(new Route("Atlanta", "Charleston", 1));
        addRoute(new Route("Atlanta", "Miami", 5));
        addRoute(new Route("Atlanta", "New Orleans", 4));
        addRoute(new Route("Atlanta", "Nashville", 1));
        addRoute(new Route("Miami", "Charleston", 4));
        addRoute(new Route("Miami", "Atlanta", 5));
        addRoute(new Route("Miami", "New Orleans", 6));
        addRoute(new Route("Saint Louis", "Chicago", 2));
        addRoute(new Route("Saint Louis", "Pittsburgh", 5));
        addRoute(new Route("Saint Louis", "Nashville", 2));
        addRoute(new Route("Saint Louis", "Little Rock", 2));
        addRoute(new Route("Saint Louis", "Kansas City", 2));
        addRoute(new Route("Kansas City", "Omaha", 1));
        addRoute(new Route("Kansas City", "Saint Louis", 2));
        addRoute(new Route("Kansas City", "Oklahoma City", 2));
        addRoute(new Route("Kansas City", "Denver", 4));
        addRoute(new Route("Little Rock", "Saint Louis", 2));
        addRoute(new Route("Little Rock", "Nashville", 3));
        addRoute(new Route("Little Rock", "New Orleans", 3));
        addRoute(new Route("Little Rock", "Dallas", 2));
        addRoute(new Route("Little Rock", "Oklahoma City", 2));
        addRoute(new Route("New Orleans", "Little Rock", 3));
        addRoute(new Route("New Orleans", "Atlanta", 4));
        addRoute(new Route("New Orleans", "Miami", 6));
        addRoute(new Route("New Orleans", "Houston", 2));
        addRoute(new Route("Houston", "Dallas", 1));
        addRoute(new Route("Houston", "New Orleans", 2));
        addRoute(new Route("Houston", "El Paso", 6));
        addRoute(new Route("Dallas", "Oklahoma City", 2));
        addRoute(new Route("Dallas", "Little Rock", 2));
        addRoute(new Route("Dallas", "Houston", 1));
        addRoute(new Route("Dallas", "El Paso", 4));
        addRoute(new Route("Oklahoma City", "Kansas City", 2));
        addRoute(new Route("Oklahoma City","Little Rock", 2));
        addRoute(new Route("Oklahoma City", "Dallas", 2));
        addRoute(new Route("Oklahoma City", "El Paso", 5));
        addRoute(new Route("Oklahoma City", "Santa Fe", 3));
        addRoute(new Route("Oklahoma City", "Denver", 4));
        addRoute(new Route("Santa Fe", "Denver", 2));
        addRoute(new Route("Santa Fe", "Oklahoma City", 3));
        addRoute(new Route("Santa Fe", "El Paso", 2));
        addRoute(new Route("Santa Fe", "Phoenix", 3));
        addRoute(new Route("El Paso", "Santa Fe", 2));
        addRoute(new Route("El Paso", "Oklahoma City", 3));
        addRoute(new Route("El Paso", "Dallas", 4));
        addRoute(new Route("El Paso", "Houston", 6));
        addRoute(new Route("El Paso", "Los Angeles", 6));
        addRoute(new Route("El Paso", "Phoenix", 3));
        addRoute(new Route("Phoenix", "Denver", 5));
        addRoute(new Route("Phoenix", "Santa Fe", 3));
        addRoute(new Route("Phoenix", "El Paso", 3));
        addRoute(new Route("Phoenix", "Los Angeles", 3));
        addRoute(new Route("Las Vegas", "Salt Lake City", 3));
        addRoute(new Route("Las Vegas", "Los Angeles", 2));
        addRoute(new Route("Los Angeles", "San Francisco", 3));
        addRoute(new Route("Los Angeles", "Las Vegas", 2));
        addRoute(new Route("Los Angeles", "Phoenix", 3));
        addRoute(new Route("Los Angeles", "El Paso", 6));
    }

    // REQUIRES: route != null
    // MODIFIES: this
    // EFFECTS: adds the route to the adjacency map for both cities
    private void addRoute(Route route) {
        String start = route.getStartCity();
        String end = route.getEndCity();

        if (!adjacencyMap.containsKey(start)) {
        
            adjacencyMap.put(start, new ArrayList<>());
        }

        if (!adjacencyMap.containsKey(end)) {
        
            adjacencyMap.put(end, new ArrayList<>());
        }

        adjacencyMap.get(start).add(route);
        adjacencyMap.get(end).add(route);

    }

    // REQUIRES: route != null
    // EFFECTS: returns a list of routes incident to the input city
    public List<Route> getRoutesFrom(String city) {
        
        if (!adjacencyMap.containsKey(city)) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(adjacencyMap.get(city));
    }
}
