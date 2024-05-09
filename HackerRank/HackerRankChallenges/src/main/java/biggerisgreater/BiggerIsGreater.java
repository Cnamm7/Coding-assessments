package biggerisgreater;

import java.util.HashMap;
import java.util.Map;

public class BiggerIsGreater {
    class Result {

        /*
         * Complete the 'biggerIsGreater' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING w as parameter.
         */

        public static String biggerIsGreater(String w) {
            char[] charArray = w.toCharArray();
            int largestIndexChanged = Integer.MIN_VALUE;
            Map<Integer, Integer> possibleCandidates = new HashMap<>();
            for (int i = charArray.length - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (charArray[i] > charArray[j]) {
                        if (possibleCandidates.containsKey(j)) {
                            if (charArray[possibleCandidates.get(j)] > charArray[i]) {
                                possibleCandidates.put(j,i);
                            }
                        } else {
                            possibleCandidates.put(j,i);
                        }
                        largestIndexChanged = Math.max(largestIndexChanged, j);
                        break;
                    }
                }
            }

            if (possibleCandidates.isEmpty()) { return "no answer";}

            swap(charArray, possibleCandidates.get(largestIndexChanged), largestIndexChanged);

            for (int i = largestIndexChanged + 1; i < charArray.length; i++) {
                for (int j = i + 1; j < charArray.length; j++) {
                    if (charArray[i] > charArray[j]) {
                        swap(charArray, i, j);
                    }
                }
            }

            return new String(charArray);
        }

        private static void swap(char[] charArray, int firstIndex, int SecondIndex) {
            char temp = charArray[firstIndex];
            charArray[firstIndex] = charArray[SecondIndex];
            charArray[SecondIndex] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(BiggerIsGreater.Result.biggerIsGreater("ab"));
        System.out.println(BiggerIsGreater.Result.biggerIsGreater("bb"));
        System.out.println(BiggerIsGreater.Result.biggerIsGreater("hefg"));
        System.out.println(BiggerIsGreater.Result.biggerIsGreater("dkhc"));
    }
}
