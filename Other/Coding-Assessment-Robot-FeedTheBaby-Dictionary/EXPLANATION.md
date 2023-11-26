# 1
Design a custom Word Dictionary that provides definitions about the words. Users should be able to lookup
specific words and find their corresponding definitions, add/remove words with definitions and search words
partially.
The dictionary should have a unique definition for the given word and these words are case insensitive. Please
implement the below WordDictionary class without using built-in java HashMap.

WordDictionary {

public WordDictionary() {
// initializes the WordDictionary
}

public void insertWord(String word, String definition) {
// Inserts the word and corresponding definition into the dictionary, if the word already exists, it

will override the word and definition
}

public String findDefinition(String word) {
// Returns the definition for the word
}

public List&lt;String&gt; partialSearch(String partialWord) {
// Returns the definitions for the words that are matched partially
}

public void remove(String word) {
// Removes the word with definition from the dictionary
}
}

# Answer of 1:
• In my initial step, I implemented the hash table, designing a hash function and choosing the structure of my hash table. I opted for an Array as the
container, a prime number for the array size in my hash function, and a LinkedList data structure to resolve collisions during hashing.
• I commented on each implementation step for clarity. The class constructor creates an array object, the insert word function uses the hash method to
find the index and places the word accordingly. The partial search function raised some questions about the approach. Since the question did not specify
if the partial word starts from the first character or the middle, I iterated through all keys and, if included, added it to the answer. For instance, if I was
sure it is starting from the beginning of the sentence and first character then maybe for improving the time complexity, I could use Trie data structure
for partial search. The removal process is straightforward: search, and if found, remove; otherwise, do nothing.
• The time complexity of the hash function is the number of characters in the key. For the insert method, in the worst-case scenario, it is equal to the
number of words in the dictionary. The same applies to getting definitions and partial search. Although the amortized time complexity can be considered
O (1) if we design scalable approach and resize our array due to the hash function implementation, at the end the worst case is O(n).
• The space complexity is equal to the number of words in the dictionary. The time spent on this solution was approximately 15 to 20 minutes.

# 2
You have been provided the “Robot” and “Main” classes below. Using these classes, you have been tasked
with writing an algorithm to guide a robot through a maze. An example of a possible maze configuration is
provided below. You are not permitted to alter the “Robot” class, and can only add code to the main()
method provide in the Main class.

Code Challenge ::: Maze Solving Robot

public class Robot {
//Please do not write any new code in this class
//A brief description is provided within each method
//describing its function

public boolean isPathClear() }
//returns true if no wall is in front of the robot
//returns false if a wall is in front of the robot
}

public void turnLeft() {
//rotates the robot left by 90 degrees (counterclockwise)
}

public void moveForward {
//moves the robot forward one square
//throws error if the robot hits a wall
}

public boolean hasExited() {
//return true if the robot has exited the maze
}
}

Implement the main method below with instructions to guide the robot through any maze using the Robot&#39;s
methods explained above. The program should end once the robot has successfully completed the maze. Try to
code your solution in as few lines as possible.

public class Main {
public static void main(String[] args) {
Robot rob = new Robot();
//Your code here

}
}

Below is an example of a possible maze, although your code should be able to function for differently configured
mazes as well.

# Answer of 2:
• The initial step involved figuring out how to record the robot's moves, handling situations in two possible ways, and navigating paths to check for dead
ends. I used a stack for LIFO to record moves, a queue to track possible ways, and the FIFO feature to go through available ways in order of precedence.
The implementation is in the main method, creating a node for the robot's position, recording available options, and navigating accordingly.
• The time complexity in the worst case is the number of places the robot needs to visit or O(n). For the node, as it records available options, it is O (1).
Inserting, removing and accessing the head of the stack and queue are O (1).
• The space complexity is the size of the stack, which at worst is all places on the board. The queue, storing at most 3 values, has a space complexity of
O (1). The time spent on the robot's approach was the longest, approximately 35 to 40 minutes.

# 3
The following program prints,
a) “Happy Face” text when you feed a child with “Ice Cream” and
b) “Angry Face” text when you feed a child with “Salad” and
c) “Normal Face” text when you feed a child with “Milk”
d) &quot;Error Face&quot; text if you do not feed child with either &quot;Ice Cream&quot;, &quot;Salad&quot; or &quot;Milk&quot;

public static void main(String[] args) {
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
System.out.println(&quot;Feed Child With Ice Cream, Salad or Milk To See Child&#39;s Face Reaction : &quot;);
String food = reader.readLine();
if (food.equalsIgnoreCase(&quot;Ice Cream&quot;)) {
System.out.println(&quot;Happy&quot; + &quot; &quot; + &quot;Face&quot;);
} else if (food.equalsIgnoreCase(&quot;Salad&quot;)) {
System.out.println(&quot;Angry&quot; + &quot; &quot; + &quot;Face&quot;);
} else if (food.equalsIgnoreCase(&quot;Milk&quot;)) {
System.out.println(&quot;Normal&quot; + &quot; &quot; + &quot;Face&quot;);
} else {
System.out.println(&quot;Error&quot; + &quot; &quot; + &quot;Face&quot;);
}
}
There are different ways you can refactor above code and remove/replace “if else if” condition and print the same
output. Write code snippets of minimum 3 possible ways you can think the above code can be refactored and explain
each approach.

# Answer of 3:
• The first approach involves a switch, naturally replacing if and else statements with cases and default. It could also be an enhanced switch.
• The second approach uses a stream of data. Options are stored inside a collection, and a stream filter is used to find the option. A ternary operator is
employed to return the default value if the collection after filtering is empty.
• The third approach utilizes a HashMap function to store options and retrieve related data or return the default answer.
• These are the simplest approaches in my opinion. The time spent on this question was relatively short, around 5 to 10 minutes.
