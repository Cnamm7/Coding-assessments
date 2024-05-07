# Challenge Explanation:


An English text is to be encrypted using the following encryption scheme. Initially, the spaces are removed from the text. The characters are then arranged in a grid of size rows by columns, where the product of rows and columns should not be less than the length of the text. Additionally, both the number of rows and columns are equal to the square root of the original text length. Each column of the grid represents a new word, thus encrypting the original text.
source: hackerRank

challenge name: Organizing Containers Of Balls

# Solution Explanation:


Upon analyzing the question, it appears to be a straightforward task involving the creation of a grid using a double character array. Subsequently, each word can be constructed by concatenating each character from the selected column. Finally, the encrypted text can be generated by assembling the words accordingly.