# Challenge Explanation:

Bomberman is a game played on a grid where bombs explode after 3 seconds, destroying adjacent cells.  Here's the simplified breakdown:

Bomberman's Actions:

Initial Setup: Bomberman places bombs in some cells.
Wait: One second passes.
Full Grid: Bomberman fills all empty cells with bombs.
Detonation: The initial bombs explode, clearing those cells and neighbors.
Repeat: Steps 3 and 4 continue forever.
The Challenge: Given the starting bomb locations, predict what the grid will look like after a specific number of seconds (n).

Key Concepts:

No Chain Reactions: Exploding bombs only destroy immediate neighbors, not other bombs.
Simultaneous Explosions: All bombs planted at the same time explode together.
Cyclical Pattern: The grid state will eventually repeat in a cycle.

source: hackerRank

challenge name: The Bomberman Game

# Solution Explanation:

After analyzing the problem, I realized we can calculate steps of the grid being filled, then denoted. There are 2 stages that have different faces in total and all other stages are the same, we just need to understand which stage should be returned.