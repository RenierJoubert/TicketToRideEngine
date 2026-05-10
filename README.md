# Ticket to Ride Optimized

A greedy pathfinding optimization engine for the board game *Ticket to Ride*, implemented in Java. This project applies graph theory and combinatorial optimization to compute maximum-value route selections under resource constraints.

## Overview

Ticket to Ride Engine solves the constrained optimization problem inherent in the board game *Ticket to Ride*: given a set of destination tickets and a 45-train budget, determine which tickets to complete in order to maximize total score.

### Problem Statement

In *Ticket to Ride*, a player receives a hand of tickets, each representing:
- A source and destination city
- A point value (earned if completed, deducted if incomplete)
- A distance in train units (the resource constraint)

The engine computes the optimal subset of tickets to complete given the fixed train budget of 45 units, maximizing overall score while accommodating disconnected route networks.

### Algorithm

The pathfinding strategy employs a **greedy heuristic** combined with **Dijkstra's algorithm**:

1. **Value Density Sorting**: Tickets are prioritized by their points-per-distance ratio, ensuring high-ROI routes are considered first
2. **Shortest Path Computation**: Dijkstra's algorithm determines the minimum train cost for each candidate ticket
3. **Greedy Selection**: Tickets are iteratively selected if they fit within the remaining train budget
4. **Path Aggregation**: Selected routes are aggregated into a unified path (not necessarily connected)

**Time Complexity**: O(V² log V + E) per iteration, where V is the number of cities and E is the number of routes

**Space Complexity**: O(V + E) for graph representation

## Features

- **Ticket Management**: Add, remove, and view tickets in your current hand
- **Optimal Path Computation**: Calculate maximum-value route selection under resource constraints
- **Persistence**: Save and load game states to/from disk
- **Audit Trail**: Comprehensive logging of all operations with timestamps
- **Performance Metrics**: Completion analysis with visual breakdown (pie chart) of completed vs. incomplete tickets