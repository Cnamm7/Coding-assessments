package feedBaby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

    /*
    There are different ways you can refactor code and remove/replace “if else if” condition and print the same
    output. Write code snippets of minimum 3 possible ways you can think the above code can be refactored and explain
    each approach.
    */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed child with Ice Cream, Salad or Milk to see child's face reaction: ");
        String food = reader.readLine();
        if (food.equalsIgnoreCase("Ice Cream")) {
            System.out.println("Happy" + " " + "Face");
        } else if ( food.equalsIgnoreCase("Salad")){
            System.out.println("Angry" + " " + "Face");
        } else if (food.equalsIgnoreCase("Milk")){
            System.out.println("Normal" + " " + "Face");
        } else{
            System.out.println("Error" + " " + "Face");
        }
    }
}

/**
 * using switch instead of if and else, and have a default case for printing the cases that are not included
 * it can be converted to enhanced switch statement as well for better readability
 */

class MainForSwitch {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed child with Ice Cream, Salad or Milk to see child's face reaction: ");
        String food = reader.readLine();
        switch (food.toLowerCase()) {
            case "ice cream":
                System.out.println("Happy" + " " + "Face");
                break;
            case "salad":
                System.out.println("Angry" + " " + "Face");
                break;
            case "milk":
                System.out.println("Normal" + " " + "Face");
                break;
            default:
                System.out.println("Error" + " " + "Face");
        }
    }
}

/**
 * stream of data by storing answers we are looking for, then iterate throw the list and filter it
 * based on user input, and use ternary operator in the end for checking if the list is empty or it contains
 * value
 */

class MainStream {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed child with Ice Cream, Salad or Milk to see child's face reaction: ");
        String inputFood = reader.readLine();
        List<Node> foods = new ArrayList<>();
        Node iceCream = new Node("ice cream", "Happy Face");
        Node salad = new Node("salad", "Angry Face");
        Node milk = new Node("milk", "Normal Face");
        foods.add(iceCream);
        foods.add(salad);
        foods.add(milk);
        foods = foods.stream().filter(element -> element.food.equalsIgnoreCase(inputFood)).collect(Collectors.toList());
        System.out.println(foods.size() == 0 ? "Error Face" : foods.get(0).face);
    }
    static class Node {
        String food;
        String face;
        Node (String food, String face) {
            this.food = food;
            this.face = face;
        }
    }
}

/**
 * use HashMap to store the data we are looking for and then retrieving the answer based on user input
 * if it contains the answer, it will return the value of the key, if it is not then it returns default value
 */

class MainHashMap {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Feed child with Ice Cream, Salad or Milk to see child's face reaction: ");
        String food = reader.readLine();
        Map<String, String> foodsMap = new HashMap<>();
        foodsMap.put("ice cream", "Happy Face");
        foodsMap.put("salad", "Angry Face");
        foodsMap.put("milk", "Normal Face");
        System.out.println(foodsMap.getOrDefault(food.toLowerCase(), "Error Face"));
    }
}



