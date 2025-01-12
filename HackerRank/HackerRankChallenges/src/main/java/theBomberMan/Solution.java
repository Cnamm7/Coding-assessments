package theBomberMan;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class BomberManResult {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
        // Write your code here
        if (n == 1) {
            return grid;
        }

        List<String> filledArray = createFilledArray(grid);
        List<String> distinguishedArray = createDistinguishArray(grid);
        List<String> firstStageDenote = denoteThebombs(distinguishedArray);
        distinguishedArray = createDistinguishArray(firstStageDenote);
        List<String> secondStageDenote = denoteThebombs(distinguishedArray);
        List<String> finalAnswer = new ArrayList<>();

        if (n % 2 == 0) {
            return filledArray;
        } else {
            finalAnswer = isFirstStage(n) ? firstStageDenote : secondStageDenote;
        }
        return finalAnswer;
    }

    private static List<String> createFilledArray(List<String> grid) {
        List<String> filledList = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            String filledRow = "";
            for (int j = 0; j < grid.get(i).length(); j++) {
                filledRow += 'O';
            }
            filledList.add(filledRow);
        }
        return filledList;
    }

    private static List<String> createDistinguishArray(List<String> grid) {
        List<String> distinguishedList = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            String distinguishedRow = "";
            for (int j = 0; j < grid.get(i).length(); j++) {
                if (grid.get(i).charAt(j) == '.') {
                    distinguishedRow += 'O';
                } else {
                    distinguishedRow += 'X';
                }
            }
            distinguishedList.add(distinguishedRow);
        }
        return distinguishedList;
    }

    private static List<String> denoteThebombs(List<String> grid) {
        List<char[]> denotedGrid = new ArrayList<>();
        for (String s : grid) {
            denotedGrid.add(s.toCharArray());
        }
        for (int i = 0; i < denotedGrid.size(); i++) {
            for (int j = 0; j < denotedGrid.get(i).length; j++) {
                if (denotedGrid.get(i)[j] == 'X') {
                    int top = i - 1;
                    int bottom = i + 1;
                    int left = j - 1;
                    int right = j + 1;
                    if (top >= 0 &&  denotedGrid.get(top)[j] == 'O') {
                        denotedGrid.get(top)[j] = '.';
                    }
                    if (bottom < denotedGrid.size() && denotedGrid.get(bottom)[j] == 'O') {
                        denotedGrid.get(bottom)[j] = '.';
                    }
                    if (left >= 0 && denotedGrid.get(i)[left] == 'O') {
                        denotedGrid.get(i)[left] = '.';
                    }
                    if (right < denotedGrid.get(i).length && denotedGrid.get(i)[right] == 'O') {
                        denotedGrid.get(i)[right] = '.';
                    }
                    denotedGrid.get(i)[j] = '.';
                }
            }
        }
        List<String> finalDenotedAnswer = new ArrayList<>();
        for (char[] charArray: denotedGrid) {
            finalDenotedAnswer.add(new String(charArray));
        }
        return finalDenotedAnswer;
    }

    private static boolean isFirstStage(int n) {
        if (n == 3 || (n - 3) % 4 == 0) {
            return true;
        }
        return false;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = BomberManResult.bomberMan(n, grid);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
