# Challenge Explanation:


Given a word, create a new word by swapping some or all of its characters. This new word must meet two criteria:
It must be greater than the original word
It must be the smallest word that meets the first condition

source: hackerRank

challenge name: Bigger Is Greater

# Solution Explanation:


After carefully examining the question, the approach involves iterating through the array from the end to identify potential candidates for swapping. Swapping these candidates strategically allows us to construct the smallest larger string. We store these possibilities and select the candidate index with the largest index value. After swapping the characters, we iterate again to make the string smaller, starting from the swapped index. This systematic approach ensures the creation of the desired smallest larger string.