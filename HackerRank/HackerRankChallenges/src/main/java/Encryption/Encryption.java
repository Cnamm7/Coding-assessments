package Encryption;

public class Encryption {
    class Result {

        /*
         * Complete the 'encryption' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING s as parameter.
         */

        public static String encryption(String s) {
            // Write your code here
            s = s.replace(" ", "");
            int length = s.length();

            int rows = (int) Math.floor(Math.sqrt(length));
            int columns = (int) Math.ceil(Math.sqrt(length));
            if (rows * columns < length) {
                rows = columns;
            }

            char[][] grid = new char[rows][columns];

            int start = 0;
            int end = columns;
            int rowCounter = 0;
            while (start < length) {
                int columnCounter = 0;
                String sub = s.substring(start, end);
                start = end;
                end += columns;

                if (end > length) {
                    end = length;
                }

                for (char subChar : sub.toCharArray()) {
                    grid[rowCounter][columnCounter] = subChar;
                    columnCounter++;
                }
                rowCounter++;
            }

            String output = "";

            int encodedWord = 0;
            while (encodedWord < columns) {
                for (char[] rowsOfGRid : grid) {
                    if (rowsOfGRid[encodedWord] == 0) {
                        continue;
                    }
                    output += rowsOfGRid[encodedWord];
                }

                output += " ";
                encodedWord++;
            }
            output = output.trim();
            return output;
        }
    }

    public static void main(String[] args) {
        String s = "feedthedog";
        System.out.println(Result.encryption(s));
    }
}
