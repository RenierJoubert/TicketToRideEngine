<h1>My Personal Project: Ticket to Ride Optimized</h1>

<h4>What will this application do?</h4>

For my personal project I wish to program an application based on the board game *Ticket to Ride*, in that this application will take a collection of *routes* and map that list to the optimized 
path(s), that is the path(s) which yeilds the **maximum number of points**. To elaborate, in the game a player recieves an arbitrary number of cards each game, where each card represents a route from A -> B needing to be completed and a point value associated with that route; if completed they earn x points, if not completed x points are deducted from their total score. Note, for this application I will be working at a higher level of abstraction than the actual board game, however the basic premise of optimization will still serve functional in actual play despite certain oversights made.

To clarify, my class **X** in this application will be a *route*, which will represent the following: (All of which can be arbitrary.)
* A start city
* An end city
* A length (the number of trains required to complete the route), note the player is constrained to 45 trains
* The number of points associated with the route
 

My class **Y** in this application will be a *hand* which represents the player's hand, that is the routes that they wish to complete and will be a list of routes.

<h4>Who is this application intended for?</h4>

Furthermore, this application is intended for anyone interested in gaining a better understanding of optimization and strategy associated with *Ticket to Ride*, or those who simply want to improve their odds of winning.

<h4>Why does this project interest me?</h4>

Finally, this project has been of interest to me ever since I was introduced to the board game a few months, as despite the game's popularity very few sources delve into complexities of the graph theory under the game's hood so I thought it would make for a very interesting project.

