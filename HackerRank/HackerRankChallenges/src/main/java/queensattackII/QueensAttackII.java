package queensattackII;

import nondivisiblesubset.NonDivisibleSubset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QueensAttackII {
    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {

        Map<Direction, Boolean> openDirections = calculateOpenDirectionsMap();
        Map<Integer, Set<Integer>> obstaclesMap = calculateObstaclesMap(k, obstacles);

        int circleMove = 1;
        int totalAttackMoves = 0;
        while ((r_q + circleMove <= n)
                || (r_q - circleMove >= 1)
                || (c_q + circleMove <= n)
                || (c_q - circleMove >= 1)) {

            totalAttackMoves = setOpenDirectionsAndUpdateTotalAttackMoves(r_q, c_q, n, circleMove, totalAttackMoves, openDirections, obstaclesMap);
            circleMove++;
        }
        return totalAttackMoves;
    }

    private enum Direction {
        LEFT, TOP, RIGHT, DOWN, LEFT_TOP, TOP_RIGHT, RIGHT_DOWN, DOWN_LEFT
    }

    private static Map<Direction, Boolean> calculateOpenDirectionsMap() {
        Map<Direction, Boolean> openDirections = new HashMap<>();
        for (int i = 0; i < Direction.values().length; i++) {
            openDirections.put(Direction.values()[i], true);
        }
        return openDirections;
    }

    private static Map<Integer, Set<Integer>> calculateObstaclesMap(int k, List<List<Integer>> obstacles) {
        Map<Integer, Set<Integer>> obstaclesMap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            int xAskey = obstacles.get(i).get(0);
            int yAsValue = obstacles.get(i).get(1);
            if (obstaclesMap.containsKey(xAskey)) {
                obstaclesMap.get(xAskey).add(yAsValue);
            } else {
                Set<Integer> yValues = new HashSet<>();
                yValues.add(yAsValue);
                obstaclesMap.put(xAskey, yValues);
            }
        }
        return obstaclesMap;
    }

    private static int setOpenDirectionsAndUpdateTotalAttackMoves(int r_q, int c_q, int n, int circleMove, int totalAttackMoves, Map<Direction, Boolean> openDirections, Map<Integer, Set<Integer>> obstaclesMap) {
        int leftX = r_q - circleMove;
        int x = r_q;
        int rightX = r_q + circleMove;

        int bottomY = c_q - circleMove;
        int y = c_q;
        int topY = c_q + circleMove;

        for (Direction direction : openDirections.keySet()) {
            if (openDirections.get(direction)) {
                switch (direction) {
                    case LEFT:
                        if ((leftX < 1)
                                || (obstaclesMap.containsKey(leftX) && obstaclesMap.get(leftX).contains(y))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case TOP:
                        if ((topY > n)
                                || (obstaclesMap.containsKey(x) && obstaclesMap.get(x).contains(topY))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case RIGHT:
                        if ((rightX > n)
                                || (obstaclesMap.containsKey(rightX) && obstaclesMap.get(rightX).contains(y))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case DOWN:
                        if ((bottomY < 1)
                                || (obstaclesMap.containsKey(x) && obstaclesMap.get(x).contains(bottomY))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case LEFT_TOP:
                        if ((leftX < 1 || topY > n)
                                || (obstaclesMap.containsKey(leftX) && obstaclesMap.get(leftX).contains(topY))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case TOP_RIGHT:
                        if ((topY > n || rightX > n)
                                || (obstaclesMap.containsKey(rightX) && obstaclesMap.get(rightX).contains(topY))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case RIGHT_DOWN:
                        if ((rightX > n || bottomY < 1)
                                || (obstaclesMap.containsKey(rightX) && obstaclesMap.get(rightX).contains(bottomY))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    case DOWN_LEFT:
                        if ((bottomY < 1 || leftX < 1)
                                || (obstaclesMap.containsKey(leftX) && obstaclesMap.get(leftX).contains(bottomY))) {
                            openDirections.put(direction, false);
                        }
                        break;
                    default:
                        break;
                }
            }
            if (openDirections.get(direction)) {
                totalAttackMoves++;
            }
        }

        return totalAttackMoves;
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        int r_q = 4;
        int c_q = 3;
        List<Integer> firstObstacle = Arrays.asList(5, 5);
        List<Integer> secondObstacle = Arrays.asList(4, 2);
        List<Integer> thirdObstacle = Arrays.asList(2, 3);

        List<List<Integer>> obstacles = new ArrayList<>();
        obstacles.add(firstObstacle);
        obstacles.add(secondObstacle);
        obstacles.add(thirdObstacle);

        System.out.println(QueensAttackII.queensAttack(n, k, r_q, c_q, obstacles));
    }
}
