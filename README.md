<h1>My Personal Project: Ticket to Ride Optimized</h1>

<h4>What will this application do?</h4>

For my personal project I wish to program an application based on the board game *Ticket to Ride*, more specifically this application will take a list of *tickets* referred to as the user's *hand* and the *gameMap*, where a *ticket* is a subclass of a *route* from the *gameMap* and map that list to the optimized *path*, that is the *path* which yeilds the **maximum number of points** under the constraint such that the player only has access to 45 trains, which is the case in the actual game. Furthermore, this path need not be connected, namely, the list of routes in the path does not need to form 1 connected route and routes in the path can be separate. To elaborate, in the game a player recieves an arbitrary number of cards each game, where each card represents a *ticket* that in essence is a route from city A -> city B needing to be completed and a point value associated with that route, as well as a distance between the two cities measured in the number of trains between them, which is important because the player only has access to 45 trains. Then, if that ticket is completed the player earns x points, if not completed, x points are deducted from their total score. So, for this application I will be working at a higher level of abstraction than the actual board game as I will not be touching on several other intricacies of the game such as train colour, however the basic premise of optimization will still serve functional in actual play despite certain oversights made.

To clarify, my class **X** in this application will be a *ticket*, which will represent the following: (All of which can be arbitrary.)
* A start city
* An end city
* A length (the number of trains required to complete the route), note the player is constrained to 45 trains
* The number of points associated with the route
 

My class **Y** in this application will be a *hand* which represents the player's hand, that is the *tickets* that they wish to complete and will be a list of *tickets.

<h4>Who is this application intended for?</h4>

Furthermore, this application is intended for anyone interested in gaining a better understanding of optimization and strategy associated with *Ticket to Ride*, or those who simply want to improve their odds of winning.

<h4>Why does this project interest me?</h4>

Finally, this project has been of interest to me ever since I was introduced to the board game a few months ago, as despite the game's popularity very few sources delve into complexities of the graph theory under the game's hood so I thought it would make for a very interesting project.

<h4>User Stories</h4>

* As a user I want to be able to add a ticket to my hand.
* As a user I want to be able to view my hand.
* As a user I want to be able to a route to my hand and specify the start city, end city, length, and number of points.
* As a user I want to be able to see the optimal path(s) to take in order to maximize the number of points obtained.

