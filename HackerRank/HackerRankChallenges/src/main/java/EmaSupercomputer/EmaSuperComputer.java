package EmaSupercomputer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class EmaSuperComputer {

    /*
     * Complete the 'twoPluses' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY grid as parameter.
     */



    public static int twoPluses(List<String> grid) {
        // Write your code here
        List<Plus> validPluses = buildTheValidPluses(grid);
        if (validPluses.size() == 0) {
            return 1;
        }
        return calculateMaxProduct(validPluses);
    }

    public static class Plus {
        public Point center;
        public int length;
        int maxRow;
        int minRow;
        int maxColumn;
        int minColumn;
        Set<Point> points;

        public Plus(Point center, int length) {
            this.center = center;
            this.length = length;
            this.maxRow = center.x + (length);
            this.minRow = center.x - (length);
            this.maxColumn = center.y + (length);
            this.minColumn = center.y - (length);
            this.points = getPlusPoints();
        }

        public int calculateArea() {
            return (2 * length) + (2 * length) + 1;
        }

        @Override
        public boolean equals(Object obj) {
            return this.center.equals(((Plus) obj).center) && this.length == ((Plus) obj).length ;
        }

        @Override
        public final int hashCode() {
            int result = 17;
            if (center != null) {
                result = 31 * result + center.hashCode();
            }
            if (length != 0) {
                result = 31 * result + Integer.hashCode(length);
            }
            return result;
        }

        public boolean hasOverlap(Plus other) {
            Set<Point> pointsForThisPlus = this.points;
            Set<Point> pointsForOtherPlus = other.points;

            for (Point point : pointsForThisPlus) {
                if (pointsForOtherPlus.contains(point)) {
                    return true;
                }
            }
            return false;
        }

        public Set<Point> getPlusPoints() {
            Set<Point> points = new HashSet<>();
            points.add(center);
            for (int i = 1; i <= length; i++) {
                points.add(new Point(center.x - i, center.y));
                points.add(new Point(center.x + i, center.y));
                points.add(new Point(center.x, center.y + i));
                points.add(new Point(center.x, center.y - i));
            }
            return points;
        }

        public void print() {
            System.out.print("center: ");
            center.print();

            System.out.print("length: ");
            System.out.println(length);

            System.out.println();
        }
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        }

        @Override
        public final int hashCode() {
            int result = 17;
            if (x > -1) {
                result = 31 * result + Integer.hashCode(x);
            }
            if (y > -1) {
                result = 31 * result + Integer.hashCode(y);
            }
            return result;
        }

        public void print() {
            System.out.println("(" + x + " ," + y + ")");
        }
    }


    public static List<Plus> buildTheValidPluses(List<String> grid) {
        List<Plus> validPluses = new ArrayList<>();
        Set<Point> bTracker = buildBTracker(grid);
        validPluses = buildGTracker(bTracker, grid);
        return validPluses;
    }

    public static Set<Point> buildBTracker(List<String> grid) {
        Set<Point> bTracker = new HashSet<>();
        int minRow = 0;
        int maxRow = grid.size();
        int minColumn = 0;
        int maxColumn = grid.get(0).length();
        for (int i = minRow; i < maxRow; i++) {
            for (int j = minColumn; j < maxColumn; j++) {
                if (grid.get(i).charAt(j) == 'B') {
                    bTracker.add(new Point(i, j));
                }
            }
        }
        return bTracker;
    }

    public static List<Plus> buildGTracker(Set<Point> bTracker, List<String> grid) {
        List<Plus> validPlus = new ArrayList<>();
        int minRow = 0;
        int maxRow = grid.size();
        int minColumn = 0;
        int maxColumn = grid.get(0).length();
        for (int i = minRow; i < maxRow; i++) {
            // no valid big plus will be built on the first and last row
            if (i == 0 || i == grid.size() - 1) continue;
            for (int j = minColumn; j < maxColumn; j++) {
                // no valid big plus will be built on the first and last column
                if (j == 0 || j == grid.get(i).length() - 1) continue;
                if (grid.get(i).charAt(j) == 'G') {
                    int length = 0;
                    int counter = 0;
                    while (true) {
                        counter++;
                        int top = i - counter;
                        int bottom = i + counter;
                        int left = j - counter;
                        int right = j + counter;
                        if (bTracker.contains(new Point(top, j))
                                || bTracker.contains(new Point(bottom, j))
                                || bTracker.contains(new Point(i, left))
                                || bTracker.contains(new Point(i, right))) {
                            break;
                        }
                        if (top < minRow
                                || bottom >= maxRow
                                || left < minColumn
                                || right >= maxColumn) {
                            break;
                        }
                        length++;
                    }
                    if (length > 0) {
                        validPlus.add(new Plus(new Point(i, j), length));
                    }
                }
            }
        }
        return validPlus;
    }

    public static int calculateMaxProduct(List<Plus> validPluses) {
        int max = 0;

        for (int i = 0; i < validPluses.size(); i++) {
            Plus firstPlus = validPluses.get(i);
            max = Math.max(firstPlus.calculateArea(), max);
            for (int j = i + 1; j < validPluses.size(); j++) {
                Plus secondPlus = validPluses.get(j);
                if (!firstPlus.hasOverlap(secondPlus)) {
                    max = Math.max(firstPlus.calculateArea() * secondPlus.calculateArea(), max);
                } else {
                    max = decreaseEachPlusesToFindNonOverlap(firstPlus, secondPlus, max);
                }
            }
        }

        return max;
    }

    public static int decreaseEachPlusesToFindNonOverlap(Plus first, Plus second, int max) {
        Plus shrinkedFirst = new Plus(first.center, first.length);
        Plus shrinkedSecond = new Plus(second.center, second.length);
        while (shrinkedFirst.length > 0) {
            shrinkedFirst = shrinkThePlus(shrinkedFirst);
            if (!shrinkedFirst.hasOverlap(second)) {
                max = Math.max(shrinkedFirst.calculateArea() * second.calculateArea(), max);
                break;
            }
        }
        while (shrinkedSecond.length > 0) {
            shrinkedSecond = shrinkThePlus(shrinkedSecond);
            if (!shrinkedSecond.hasOverlap(first)) {
                max = Math.max(shrinkedSecond.calculateArea() * first.calculateArea(), max);
                break;
            }
        }
        shrinkedFirst = new Plus(first.center, first.length);
        shrinkedSecond = new Plus(second.center, second.length);
        while (shrinkedSecond.length > 0 && shrinkedFirst.length > 0) {
            shrinkedFirst = shrinkThePlus(shrinkedFirst);
            shrinkedSecond = shrinkThePlus(shrinkedSecond);
            if (!shrinkedFirst.hasOverlap(shrinkedSecond)) {
                max = Math.max(shrinkedFirst.calculateArea() * shrinkedSecond.calculateArea(), max);
            }
        }
        return max;
    }

    public static Plus shrinkThePlus(Plus toShrink) {
        return new Plus(toShrink.center, toShrink.length - 1);
    }


    public static void main(String[] args) {
        List<String> grid = Arrays.asList(  "GGGGGGGGGGGG",
                                            "GBGGBBBBBBBG",
                                            "GBGGBBBBBBBG",
                                            "GGGGGGGGGGGG",
                                            "GGGGGGGGGGGG",
                                            "GGGGGGGGGGGG",
                                            "GGGGGGGGGGGG",
                                            "GBGGBBBBBBBG",
                                            "GBGGBBBBBBBG",
                                            "GBGGBBBBBBBG",
                                            "GGGGGGGGGGGG",
                                            "GBGGBBBBBBBG");

        System.out.println(twoPluses(grid));
    }
}


