# Simple_Search_Engine
HyperSkill Project #15

Project Directory:
Simple_Search_Engine/Simple Search Engine/task/src/search/


https://hyperskill.org/projects/66


This project was a simple search excercise in where the program creates a small directory filled with contact information. Internally, the program implements a reverse index composed of a Map<String, ArrayList<Integer>> to keep track of potential searches. The search program then has two functions, either a simple search based off a given strategy, or to print out all the names in the given list or directory. The program can accept a command line input to be populated with a directory filled with names and contact information or be initialized and populated. Since there were a few different strategies of finding a given term (ALL, ANY, NONE) a strategy design pattern was implemented. The ALL strategy makes a SET where it finds only the given inputs in the reverse index and returns the lines where all ther terms matched. The ANY strategy looks for every given occurence of any given word in the reverse index and returns results based off that. The NONE strategy populates the whole results query with all the given directory and filters out all the occurences of the given parameters.
