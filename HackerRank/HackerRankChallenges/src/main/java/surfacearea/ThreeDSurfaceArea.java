package surfacearea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ThreeDSurfaceArea {
    class Result {

        /*
         * Complete the 'surfaceArea' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts 2D_INTEGER_ARRAY A as parameter.
         */

        public static int surfaceArea(List<List<Integer>> A) {
            // Write your code here
            Stack<Cell> stack = new Stack<>();

            for (int i = 0; i < A.size(); i++) {
                for (int j = 0; j < A.get(i).size(); j++) {
                    int left = 0;
                    int right = 0;
                    int rearLeft = 0;
                    int rearRight = 0;

                    if ((i - 1) >= 0) {
                        if (A.get(i - 1).get(j) < A.get(i).get(j)) {
                            left = A.get(i).get(j) - A.get(i - 1).get(j);
                        }
                    } else {
                        left = A.get(i).get(j);
                    }

                    if ((i + 1) < A.size()) {
                        if (A.get(i + 1).get(j) < A.get(i).get(j)) {
                            right = A.get(i).get(j) - A.get(i + 1).get(j);
                        }
                    } else {
                        right = A.get(i).get(j);
                    }

                    if ((j + 1) < A.get(i).size()) {
                        if (A.get(i).get(j + 1) < A.get(i).get(j)) {
                            rearLeft = A.get(i).get(j) - A.get(i).get(j + 1);
                        }
                    } else {
                        rearLeft = A.get(i).get(j);
                    }

                    if ((j - 1) >= 0) {
                        if (A.get(i).get(j - 1) < A.get(i).get(j)) {
                            rearRight = A.get(i).get(j) - A.get(i).get(j - 1);
                        }
                    } else {
                        rearRight = A.get(i).get(j);
                    }
                    stack.push(new Cell(left, right, rearLeft, rearRight));
                }
            }
            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop().sum();
            }
            return result;
        }

        public static class Cell {
            int top = 1;
            int bottom = 1;
            int left;
            int right;
            int rearLeft;
            int rearRight;

            public Cell(int left, int right, int rearLeft, int rearRight) {
                this.left = left;
                this.right = right;
                this.rearLeft = rearLeft;
                this.rearRight = rearRight;
            }

            public int sum() {
                return top + bottom + left + right + rearLeft + rearRight;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> input = Arrays.asList(Arrays.asList(1,3,4),Arrays.asList(2,2,3),Arrays.asList(1,2,4));
        System.out.println(Result.surfaceArea(input));
    }
}
