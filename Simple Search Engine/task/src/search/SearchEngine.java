package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SearchEngine {
    private static final List<String> SEARCH_OPTIONS = Arrays.asList("ALL", "ANY", "NONE");
    private final Scanner scanner;
    private List<String> peopleDirectory;
    private Map<String, List<Integer>> reverseIndex;

    SearchEngine(String[] args) {
        scanner = new Scanner(System.in);
        //command line arguments
        if (args.length > 0) {
            if (args.length == 1) {
                System.out.println("Missing parameters. Default operation");
            } // if filename is provided
            else {
                // if the first argument == "--data"
                if (args[0].equals("--data")) {
                    String fileName = args[1];
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                        //add strings to main repository of names/contact info
                        //counter to indicate which current line
                        //build reverseIndex by passing the currentLine and index
                        String input;
                        int currentLine = 0;
                        peopleDirectory = new ArrayList<>();
                        reverseIndex = new HashMap<>();
                        while ((input = bufferedReader.readLine()) != null) {
                            peopleDirectory.add(input);
                            reverseIndexBuilder(input, currentLine++);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File Not Found:");
                    } catch (IOException e) {
                        System.out.println("IO Exception");
                    }
                } else {
                    System.out.println("Invalid parameters. Default operation");
                }
            }
        }
    }

    public void start() {
        //if there is no data initialized
        if (peopleDirectory == null) {
            int numberOfLines = numberOfLinesInput();
            reverseIndex = new HashMap<>();
            peopleDirectory = readFromConsole(numberOfLines);
        }
        userSelect();
    }

    private int numberOfLinesInput() {
        while (true) {
            try {
                System.out.println("Enter the number of people:");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    private List<String> readFromConsole(int numberOfLines) {
        List<String> lineOfText = new ArrayList<>();
        String currentLine;

        System.out.println("Enter all people:");
        for (int i = 0; i < numberOfLines; i++) {
            currentLine = scanner.nextLine();
            lineOfText.add(currentLine);
            reverseIndexBuilder(currentLine, i);
        }

        //buffer spacing after inputs
        System.out.println();
        return lineOfText;
    }

    private void menu() {
        System.out.println("=== Menu ===\n" +
                           "1. Find a person\n" +
                           "2. Print all people\n" +
                           "0. Exit");
    }

    private void userSelect() {
        String input = null;
        while (!"0".equals(input)) {
            menu();
            input = scanner.nextLine().trim();
            System.out.println();
            switch (input) {
                //search person
                case "1":
                    personToFind();
                    break;
                //print list
                case "2":
                    printAllPeople();
                    break;
                //quit the program
                case "0":
                    break;
                default:
                    System.out.println("Incorrect option! Try Again.\n");
            }
        }
        System.out.println("Bye!");
    }

    private void personToFind() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategyChoice = scanner.nextLine().trim().toUpperCase();
        if (SEARCH_OPTIONS.contains(strategyChoice)) {
            SearchContext searchContext = new SearchContext(strategyChoice);
            String[] results = searchContext.search(peopleDirectory, reverseIndex);
            printResults(results);
        } else {
            System.out.println("Invalid selection");
        }
    }

    private void printResults(String[] searchResults) {
        if (searchResults.length == 0) {
            System.out.println("No matching people found.");
        } else {
            for (String contact : searchResults) {
                System.out.println(contact);
            }
        }
        System.out.println();
    }

    private void printAllPeople() {
        System.out.println("=== List of people ===");
        for (String person : peopleDirectory) {
            System.out.println(person);
        }
        System.out.println();
    }

    private void reverseIndexBuilder(String personInfo, int index) {
        String[] listOfWords = personInfo.split(" ");
        for (String word : listOfWords) {
            //if reverseIndex doesn't have the word, make a new entry with the index
            //else just add the index to arraylist of indexes
            if (!reverseIndex.containsKey(word)) {
                reverseIndex.put(word, new ArrayList<>(List.of(index)));
            } else {
                reverseIndex.get(word).add(index);
            }
        }
    }
}
