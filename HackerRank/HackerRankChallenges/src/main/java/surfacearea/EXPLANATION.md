# Challenge Explanation:

Madison is a little girl who is fond of toys. Her friend Mason works in a toy manufacturing factory . Mason has a 2D board  of size W * H with  rows W and H columns. calculate the price of surfaces of each board, if the cells dimensions are 1 * 1 * H, which H is given in the array for each cell.

source: hackerRank

challenge name: Organizing Containers Of Balls

# Solution Explanation:

After analyzing the problem, I realized we can calculate the total surface area by iterating over each cell in the grid and accounting for the top and bottom surfaces, which are always exposed. For each cell, we then check its neighbors to determine the exposed side surfaces. Cells at the corners have two exposed sides, edge cells have one exposed side, and middle cells have their side surfaces compared with neighboring cells to calculate the exposed area. Summing these values for all cells gives the total surface area.