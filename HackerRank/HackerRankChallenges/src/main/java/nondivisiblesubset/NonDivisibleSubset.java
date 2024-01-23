package nondivisiblesubset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NonDivisibleSubset {
    class Solution {

        /*
         * Complete the 'nonDivisibleSubset' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER k
         *  2. INTEGER_ARRAY s
         */

        public static int nonDivisibleSubset(int k, List<Integer> s) {
            // Write your code here
            Map<Integer, List<Integer>> remaindersMap = createMapOfRemaindersToValues(k, s);
            int max = Integer.MIN_VALUE;

            for (int key : remaindersMap.keySet()) {
                List<Integer> possiblity = new ArrayList<>();
                Set<Integer> added = new HashSet<>();
                addOneOrAllValues(isKeySupplementaryOrEqualToZero(key, k), key, possiblity, remaindersMap);
                added.add(key);
                for (int subKey : remaindersMap.keySet()) {
                    if (key != subKey && key + subKey != k && !isAdded(added, subKey, k)) {
                        handleAddingIncludedKeys(subKey, k, possiblity, remaindersMap, added);
                    }
                }
                max = Math.max(max, possiblity.size());
            }

            return max;
        }

        private static Map<Integer, List<Integer>> createMapOfRemaindersToValues(int k, List<Integer> s) {
            Map<Integer, List<Integer>> remaindersMap = new HashMap<>();
            for (int element : s) {
                if (!remaindersMap.containsKey(element % k)) {
                    List<Integer> members = new ArrayList<>();
                    members.add(element);
                    remaindersMap.put(element % k, members);
                } else {
                    remaindersMap.get(element % k).add(element);
                }
            }
            return remaindersMap;
        }

        private static boolean isAdded(Set<Integer> added, int key, int k) {
            return added.contains(key) || added.contains(k - key);
        }

        private static void addOneOrAllValues(boolean isKeySupplementaryOrEqualToZero, int key, List<Integer> possiblity, Map<Integer, List<Integer>> remaindersMap) {
            if (isKeySupplementaryOrEqualToZero) {
                possiblity.add(remaindersMap.get(key).get(0));
            } else {
                possiblity.addAll(remaindersMap.get(key));
            }
        }

        private static void handleAddingIncludedKeys(int subKey, int k, List<Integer> possiblity, Map<Integer, List<Integer>> remaindersMap, Set<Integer> added) {
            int supplementSubKey = k - subKey;

            if (remaindersMap.containsKey(supplementSubKey)) {
                int subKeySize = !(isKeySupplementaryOrEqualToZero(subKey, k)) ? remaindersMap.get(subKey).size() : 1;
                int supplementSubKeySize = !(isKeySupplementaryOrEqualToZero(supplementSubKey, k)) ? remaindersMap.get(supplementSubKey).size() : 1;

                if (subKeySize >= supplementSubKeySize) {
                    addOneOrAllValues(isKeySupplementaryOrEqualToZero(subKey, k), subKey, possiblity, remaindersMap);
                    added.add(subKey);
                } else {
                    possiblity.addAll(remaindersMap.get(supplementSubKey));
                    added.add(supplementSubKey);
                }

            } else {
                addOneOrAllValues(isKeySupplementaryOrEqualToZero(subKey, k), subKey, possiblity, remaindersMap);
                added.add(subKey);
            }
        }

        private static boolean isKeySupplementaryOrEqualToZero(int key, int k) {
            return key + key == k || key == 0;
        }
    }

    public static void main(String[] args) {
        List<Integer> s = Arrays.asList(19, 10, 12, 10, 24, 25, 22);
        int k = 4;
        System.out.println(NonDivisibleSubset.Solution.nonDivisibleSubset(4, s));
    }
}
