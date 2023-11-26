package robot;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
You have been provided the “Robot” and “Main” classes below. Using these classes, you have been tasked
with writing an algorithm to guide a robot through a maze. An example of a possible maze configuration is
provided below. You are not permitted to alter the “Robot” class, and can only add code to the main()
method provide in the Main class.
 */

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        // stack of steps to be able to have track of steps and come back to the points where we have another paths
        Stack<Node> steps = new Stack<>();
        // boolean solvable in case we do not have steps to follow are empty and puzzle did not solve
        boolean solvable = true;
        // create a loop that will continue to solve until robot exited
        while (!robot.hasExited()) {
            // create a current node that robot stands in it
            Node current = new Node();
            // a loop with 4 steps to check which directions are open to robot, except the back path
            for (int i = 0; i < 4; i++) {
                if (robot.isPathClear() && i != 2) {
                    current.availablePaths.add(i);
                }
                robot.turnLeft();
            }
            // check if robot encounter dead end or there is a way for it to approach
            if (current.availablePaths.peek() != null) {
                // it removes the available path from queue to go in that direction
                current.turned = current.availablePaths.remove();
                for (int i = 0; i < current.turned; i++) {
                    robot.turnLeft();
                }
                // it adds the current node to steps stack for having a record of steps we took
                steps.push(current);
            } else {
                // check if there is no path for robot and it already reached the end of steps he has taken
                // then break. it is the edge case in order to see if the puzzle is solvable
                if (steps.empty()) {
                    solvable = false;
                    break;
                }
                // a loop to check the last step has another way to go, if not then pop and remove that step
                while (steps.peek().availablePaths.peek() == null) {
                    Node temp = steps.pop();
                    // if the robot turned in that step, we turn back to the original direction in that step
                    if (temp.turned > 0) {
                        // check if it turned 1 time, then for being in original direction it should turn left
                        // 3 times counterclockwise, and if it turned 3 times it should turn 1 time counterclockwise
                        int j = temp.turned == 1 ? 3 : 1;
                        for (int i = 0; i < j; i++) {
                            robot.turnLeft();
                        }
                    }
                }
                // we reached a step that has another direction, we do not pop it from step stack, but we first turn
                // the robot in the direction of available path, and then remove the available path from queue of node
                // to mark the visited path
                int turn = steps.peek().turned - steps.peek().availablePaths.remove();
                for (int i = 0; i < turn; i++) {
                    robot.turnLeft();
                }
            }
            // at this point robot has a way open in front of him to follow
            robot.moveForward();
        }
        System.out.println(solvable ? "solved" : "not solvable");
    }

    /**
     * Node data structure that has two attributes
     *
     * availablePaths queue is for storing available path in 3 direction of robots except the back of robot, and
     * it uses the queue data structure for FIFO to go to first path on left
     *
     * turned integer is to store the direction that robot took to go in a node in case it turned in that node, it is
     * useful when we want to step back, and we know which direction we turned in this point before
     */
     static class Node {
        Queue<Integer> availablePaths;
        int turned;
        Node() {
            availablePaths = new LinkedList<>();
        }
     }


}

class Robot {
    //Please do not write any new code in this class
    //A brief description is provided within each method
    //describing its function
    public boolean isPathClear() {
        //returns true if no wall is in front of the robot
        //returns false if a wall is in front of the robot
        return false;
    }
    public void turnLeft() {
        //rotates the robot left by 90 degrees (counterclockwise)
    }

    public void moveForward() {
        //moves the robot forward one square
        //throws error if the robot hits a wall
    }

    public boolean hasExited() {
        //return true if the robot has exited the maze
        return false;
    }
}
