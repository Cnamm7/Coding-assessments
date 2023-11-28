import java.util.*;

class Main {
    /*
      for this function, I used Wikipedia reference to Graham's scan
      and solve the problem, step by step explanation is provided
      as follows
    */
    public static String MathChallenge(String[] strArr) {
        // code goes here
        // changing the input value to a list of integer coordinates
        List<CoordinatePair> pairs = new ArrayList<>();
        for (String str : strArr) {
            String[] parts = str.replaceAll("[()]", "").split(",");
            pairs.add(new CoordinatePair(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        // finding the point with lowest y-coordinate and x-coordinate
        CoordinatePair lowestPoint = findTheLowestCoordinatePair(pairs);
        // remove the lowest from the list
        pairs.remove(lowestPoint);
        // sort the list based on the comparator to give us the ability to traverse in a
        // counterclockwise way based on the lowest point
        pairs.sort(new AngleComparator(lowestPoint));
        // adding the first two point to the list and start comparing 3 points at a time
        Stack<CoordinatePair> stack = new Stack<>();
        stack.push(lowestPoint);
        stack.push(pairs.get(0));

        for (int i = 1; i < pairs.size(); i++) {
            // here we are checking if it is clockwise and remove it from stack, based on the
            // Graham's scan
            while (stack.size() >= 2 && isClockWise(stack.get(stack.size() - 2), stack.peek(), pairs.get(i))) {
                stack.pop();
            }
            stack.push(pairs.get(i));
        }

        return Integer.toString(stack.size());
    }

    /**
     * Coordinate points pair class, containing x and y
     *
     */
    private static class CoordinatePair{
        int x;
        int y;

        public CoordinatePair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CoordinatePair that)) return false;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * the method for finding the lowest coordinate point, by first comparing the y and then
     * if y was equal, compare based on x
     * @return the lowest point in the list
     * @param pairs List of pairs
     */
    private static CoordinatePair findTheLowestCoordinatePair(List<CoordinatePair> pairs) {
        CoordinatePair lowest = pairs.get(0);

        for (int i = 1; i < pairs.size(); i++) {
            if (pairs.get(i).y < lowest.y || (pairs.get(i).y == lowest.y && pairs.get(i).x < lowest.x)) {
                lowest = pairs.get(i);
            }
        }

        return lowest;
    }

    /**
     * class of comparator, which used based on Graham's scan to sort the list based on
     * the lowest coordinate
     *
     */
    private static class AngleComparator implements Comparator<CoordinatePair>{
        private final CoordinatePair lowestPoint;

        public AngleComparator(CoordinatePair lowestPoint) {
            this.lowestPoint = lowestPoint;
        }

        /**
         * compare methode, check which point's y is closer to lowest point, if both are equal,
         * then check which point has nearest x to lowest point's x, this order of values give
         * us the ability to order the items to be able to travers counterclockwise
         * @return 1 if p1 is greater and -1 if p2 is greater
         * @param p1 first coordinate point
         * @param p2 second coordinate point
         */
        @Override
        public int compare(CoordinatePair p1, CoordinatePair p2) {
            if ((p1.y - lowestPoint.y) > (p2.y - lowestPoint.y)) {
                return 1;
            } else if ((p1.y - lowestPoint.y) < (p2.y - lowestPoint.y)) {
                return -1;
            } else {
                return Integer.compare(p2.x - lowestPoint.x, p1.x - lowestPoint.x);
            }
        }
    }

    /**
     * this function check whether the three points are clockwise or not, by computing the
     * z-coordinate of the cross product of the two vectors ab and ac
     * @return true if it is clockwise, otherwise false
     * @param a first point
     * @param b second point
     * @param c third point
     */
    private static boolean isClockWise(CoordinatePair a, CoordinatePair b, CoordinatePair c) {
        int result = ((b.x - a.x) * (c.y - a.y)) - ((b.y - a.y) * (c.x - a.x));
        return result <= 0;
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        String[] input = {"(2,2)", "(3,1)", "(5,2)", "(2,3)", "(2,6)", "(0,1)"};
        System.out.print(MathChallenge(input));
    }

}