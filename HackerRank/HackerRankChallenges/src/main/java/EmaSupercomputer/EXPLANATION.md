# Challenge Explanation:


Given a grid of size n * m , each cell in the grid is either good or bad.
A valid plus is defined here as the crossing of two segments (horizontal and vertical) of equal lengths. These lengths must be odd, and the middle cell of its horizontal segment must cross the middle cell of its vertical segment.
Find the two largest valid pluses that can be drawn on  cells in the grid, and return an integer denoting the maximum product of their areas.
Note: The two pluses cannot overlap, and the product of their areas should be maximal.

challenge name: Ema's Supercomputer

# Solution Explanation:


After examining the question, the approach involves finding the valid pluses, then check if they have override and find the two that can considered the biggest