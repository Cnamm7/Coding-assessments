package organizingContainersOfBalls;

import queensattackII.QueensAttackII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganizingContainersOfBalls {

    /*
     * Complete the 'organizingContainers' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts 2D_INTEGER_ARRAY container as parameter.
     */

    public static String organizingContainers(List<List<Integer>> container) {
        // Write your code here
        List<List<Long>> combined = calculateTypeNumbers(container);;
        List<Long> typeList = combined.get(0);
        List<Long> eachContainerSum =  combined.get(1);
        List<List<Boolean>> booleanSchema = new ArrayList<>();

        for (int i = 0; i < container.size(); i++) {
            List<Boolean> possibleBases = new ArrayList<>();
            for (int j = 0; j < container.size(); j++) {
                if (typeList.get(j).equals(eachContainerSum.get(i))) {
                    possibleBases.add(true);
                } else {
                    possibleBases.add(false);
                }
            }
            booleanSchema.add(possibleBases);
        }

        List<Boolean> possible = new ArrayList<>();
        for (int i = 0; i < booleanSchema.size(); i++) {
            boolean basePossiblity = false;
            for (int j = 0; j < booleanSchema.size(); j++) {
                basePossiblity = basePossiblity || booleanSchema.get(i).get(j);
            }
            possible.add(basePossiblity);
        }

        return !possible.contains(false) ? "Possible" : "Impossible";
    }

    private static List<List<Long>> calculateTypeNumbers(List<List<Integer>> container) {
        List<Long> typeList = new ArrayList<>();
        List<Long> eachContainerSum = new ArrayList<>();

        List<List<Long>> combined = new ArrayList<>();
        for (int i = 0; i < container.size(); i++) {
            long sum = 0;
            for (int j = 0; j < container.size(); j++) {
                if (i == 0) {
                    typeList.add((long) container.get(i).get(j));
                } else {
                    typeList.set(j, typeList.get(j) + (long) container.get(i).get(j));
                }
                sum += (long) container.get(i).get(j);
            }
            eachContainerSum.add(sum);
        }

        combined.add(typeList);
        combined.add(eachContainerSum);
        return combined;
    }

    public static void main(String[] args) {
        List<Integer> container1 = Arrays.asList(0, 2, 1);
        List<Integer> container2 = Arrays.asList(1, 1, 1);
        List<Integer> container3 = Arrays.asList(2, 0, 0);

        List<List<Integer>> containers = Arrays.asList(container1, container2, container3);

        System.out.printf(OrganizingContainersOfBalls.organizingContainers(containers));
    }
}
