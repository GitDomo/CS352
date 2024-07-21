import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class WordCount {

    public static void main(String[] args) {
        String fileName = "enterTXTfilename.txt here"; // File name for input text file
        List<String> words = new ArrayList<>(); // List to store words from the file

        // Read the file and store words in the list
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove commas, periods, question marks, and exclamation points
                line = line.replaceAll("[,?.!]", "");
                // Split line into words based on whitespace
                String[] wordArray = line.split("\\s+");
                for (String word : wordArray) {
                    if (!word.isEmpty()) {
                        // Convert words to lowercase and add to list
                        words.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Use TreeMap to count word occurrences
        long startTime = System.nanoTime(); // Start timer
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (String word : words) {
            // Increment word count in TreeMap
            treeMap.put(word, treeMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("Top 5 words using TreeMap:");
        printTop5Words(treeMap); // Print top 5 words longer than 6 characters
        long endTime = System.nanoTime(); // End timer
        System.out.println("Time taken using TreeMap: " + (endTime - startTime) / 1_000_000 + " ms");

        // Use HashMap to count word occurrences
        startTime = System.nanoTime(); // Start timer
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            // Increment word count in HashMap
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("\nTop 5 words using HashMap:");
        printTop5Words(hashMap); // Print top 5 words longer than 6 characters
        endTime = System.nanoTime(); // End timer
        System.out.println("Time taken using HashMap: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    // Method to print top 5 words longer than 6 characters
    private static void printTop5Words(Map<String, Integer> map) {
        // Create a list from elements of the map
        List<Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        // Sort the list based on values in descending order
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Print top 5 words longer than 6 characters
        int count = 0;
        for (Entry<String, Integer> entry : list) {
            if (entry.getKey().length() > 6) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                count++;
                if (count == 5) {
                    break;
                }
            }
        }
    }
}
