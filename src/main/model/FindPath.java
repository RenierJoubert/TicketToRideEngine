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
    private static final int TRAIN_LIMIT = 45;

    // REQUIRES: map != null
    // EFFECTS: constructs the path finder
    public FindPath(GameMap map) {
        this.map = map;
    }

    // Dijkstra's algo

    // REQUIRES: start && end != null
    // EFFECTS: returns the shortest path between start and end cities and notes already selected routes as 0 cost.
    public List<Route> dijkstra(String start,
                                 String end,
                                 Set<Route> selectedRoutes) {

        Map<String, Integer> dist = new HashMap<>();
        Map<String, Route> prevRoute = new HashMap<>();
        Set<String> visited = new HashSet<>();

        initializeDijkstra(start, dist);

        PriorityQueue<String> pq =
                new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(start);

        runDijkstraLoop(end, selectedRoutes, dist, prevRoute, visited, pq);

        if (!dist.containsKey(end)) {
            return Collections.emptyList();
        }

        return reconstructPath(start, end, prevRoute);
    }

    // REQUIRES: start != null, dist != null
    // MODIFIES: dist
    // EFFECTS: initializes the distance map so that start has distance 0
    private void initializeDijkstra(String start,
                                    Map<String, Integer> dist) {
        dist.put(start, 0);
    }

    // REQUIRES: all parameters != null
    // MODIFIES: dist, prevRoute, visited, pq
    // EFFECTS: runs the main dijkstra loop until pq is empty or reaches end
    private void runDijkstraLoop(String end,
                                 Set<Route> selectedRoutes,
                                 Map<String, Integer> dist,
                                 Map<String, Route> prevRoute,
                                 Set<String> visited,
                                 PriorityQueue<String> pq) {

        while (!pq.isEmpty()) {
            String current = pq.poll();

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            if (current.equals(end)) {
                return;
            }

            relaxEdges(current, selectedRoutes, dist, prevRoute, pq);
        }
    }

    // REQUIRES: all parameters != null, current is in dist
    // MODIFIES: dist, prevRoute, pq
    // EFFECTS: relaxes outgoing edges from current city and updates shortest known distances
    private void relaxEdges(String current,
                            Set<Route> selectedRoutes,
                            Map<String, Integer> dist,
                            Map<String, Route> prevRoute,
                            PriorityQueue<String> pq) {

        for (Route r : map.getRoutesFrom(current)) {
            String neighbour = r.getEndCity();
            int weight = selectedRoutes.contains(r) ? 0 : r.getLength();
            int newDist = dist.get(current) + weight;

            if (!dist.containsKey(neighbour)
                    || newDist < dist.get(neighbour)) {
                dist.put(neighbour, newDist);
                prevRoute.put(neighbour, r);
                pq.add(neighbour);
            }
        }
    }

    // REQUIRES: start != null, end != null, prevRoute != null
    // EFFECTS: reconstructs and returns the path from start to end
    //          using prevRoute. Returns empty list if reconstruction fails.
    private List<Route> reconstructPath(String start,
                                        String end,
                                        Map<String, Route> prevRoute) {

        List<Route> path = new ArrayList<>();
        String current = end;

        while (!current.equals(start)) {
            Route r = prevRoute.get(current);
            if (r == null) {
                return Collections.emptyList();
            }
            path.add(r);
            current = r.getStartCity();
        }

        Collections.reverse(path);
        return path;
    }

    // Value density

    // REQUIRES: ticket != null, selectedRoutes != null
    // EFFECTS: computes marginal value density for completing ticket.
    // - Returns -1 if no path exists.
    // - Returns +infinity if ticket requires no additional trains.
    private double valueDensity(Ticket ticket,
                                Set<Route> selectedRoutes) {

        List<Route> path = dijkstra(ticket.getStart(),
                ticket.getEnd(),
                selectedRoutes);

        if (path.isEmpty()) {
            return -1;
        }

        int additionalCost = computeAdditionalCost(path, selectedRoutes);

        if (additionalCost == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (double) ticket.getPoints() / additionalCost;
    }

    // REQUIRES: path != null, selectedRoutes != null
    // EFFECTS: returns total train length of routes in path that are not already selected
    private int computeAdditionalCost(List<Route> path,
                                      Set<Route> selectedRoutes) {

        int cost = 0;

        for (Route r : path) {
            if (!selectedRoutes.contains(r)) {
                cost += r.getLength();
            }
        }

        return cost;
    }

    // Selecting routes

    // REQUIRES: hand != null
    // EFFECTS: returns a set of routes that maximizes score using <= 45 trains
    public Set<Route> findRoutes(Hand hand) {
        
        Set<Route> selectedRoutes = new HashSet<>();
        Set<Ticket> remaining = new HashSet<>(hand.getTickets());
        
        int usedTrains = 0;
        while (!remaining.isEmpty()) {

            Ticket bestTicket = selectBestTicket(remaining, selectedRoutes);

            if (bestTicket == null) {
                break;
            }

            List<Route> bestPath = dijkstra(
                    bestTicket.getStart(),
                    bestTicket.getEnd(),
                    selectedRoutes
            );

            int additionalCost = computeAdditionalCost(bestPath, selectedRoutes);

            if (!canAfford(usedTrains, additionalCost)) {
                remaining.remove(bestTicket);
                continue;
            }

            usedTrains += addPathToSelection(bestPath, selectedRoutes);
            remaining.remove(bestTicket);
        }

        return selectedRoutes;
    }

    // REQUIRES: remaining != null, selectedRoutes != null
    // EFFECTS: returns the ticket with highest marginal value density.
    //          Returns null if no ticket has non-negative density.
    private Ticket selectBestTicket(Set<Ticket> remaining,
                                    Set<Route> selectedRoutes) {

        Ticket bestTicket = null;
        double bestDensity = -1;

        for (Ticket t : remaining) {
            double density = valueDensity(t, selectedRoutes);
            if (density > bestDensity) {
                bestDensity = density;
                bestTicket = t;
            }
        }

        if (bestTicket == null || bestDensity < 0) {
            return null;
        }

        return bestTicket;
    }

    // REQUIRES: usedTrains >= 0, additionalCost >= 0
    // EFFECTS: returns true if +additionalCost <= TRAIN_LIMIT
    private boolean canAfford(int usedTrains,
                              int additionalCost) {
        return usedTrains + additionalCost <= TRAIN_LIMIT;
    }

    // REQUIRES: path != null, selectedRoutes != null
    // MODIFIES: selectedRoutes
    // EFFECTS: adds all routes in path that are not already selected.
    //          Returns total number of trains added.
    private int addPathToSelection(List<Route> path,
                                   Set<Route> selectedRoutes) {

        int added = 0;

        for (Route r : path) {
            if (!selectedRoutes.contains(r)) {
                selectedRoutes.add(r);
                added += r.getLength();
            }
        }

        return added;
    }
}