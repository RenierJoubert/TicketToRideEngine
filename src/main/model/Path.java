package model;

// The Path class is an object that represents the path(s), tickets completed/not completed, and
// the score found after using FindPath on the player's input Hand.

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Path {

    private final Set<Route> routes;
    private final Set<Ticket> completedTickets;
    private final Set<Ticket> incompleteTickets;
    private final int score;

    // REQUIRES: parameters != null
    // EFFECTS: constructs the path and results from FindPath
    public Path(Set<Route> routes,
            Set<Ticket> completedTickets,
            Set<Ticket> incompleteTickets,
            int score) {

        this.routes = Collections.unmodifiableSet(new HashSet<>(routes));
        this.completedTickets = Collections.unmodifiableSet(new HashSet<>(completedTickets));
        this.incompleteTickets = Collections.unmodifiableSet(new HashSet<>(incompleteTickets));
        this.score = score;
    
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public Set<Ticket> getCompletedTickets() {
        return completedTickets;
    }

    public Set<Ticket> getIncompleteTickets() {
        return incompleteTickets;
    }

    public int getScore() {
        return score;
    }

}
