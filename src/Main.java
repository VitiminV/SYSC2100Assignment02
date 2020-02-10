import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter the name of the file to be loaded: ");
        Scanner dirScanner = new Scanner(System.in);
        String dirString = dirScanner.nextLine();

        System.out.println("Please enter the the string you wish to search count: ");
        Scanner patternScanner = new Scanner(System.in);
        String patternString = patternScanner.nextLine();
        List<Character> patternChars = new ArrayList<Character>();
        for (char c : patternString.toCharArray()) {
            patternChars.add(c);
        }

        String fileName = "C:\\Users\\Jarred\\Documents\\SCHOOL\\SYSC 2100\\SYSC2100Assignment02\\" + dirString;

        List<Character> TextFile = readTextfile(fileName);
        findBruteArrayList(TextFile, patternChars);
        LinkedList<Character> LinkedTextFile = new LinkedList<>(TextFile);
        LinkedList<Character> LinkedPatternChars = new LinkedList<>(patternChars);
        findBruteLinkedList(LinkedTextFile, LinkedPatternChars);
    }

    private static List<Character> readTextfile(String fileName) throws IOException{
        List<Character> returnList = new ArrayList<Character>();
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = null;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    returnList.add(c);
                }
            }
        }
        return returnList;
    }

    public static int findBruteArrayList(List<Character> text, List<Character> pattern) {
        int n = text.size();
        int m = pattern.size();
        int result = 0;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i <= n - m; i++) { //start at every letter in the text, minus the length of our search word.
            int k = 0; //variable to track indexing through pattern.
            while (k < m && text.get(i + k) == pattern.get(k)) { //while the considered string matches pattern.
                k++;
            }
            if (k == m) { //if we get all the way through pattern and our considered string still matches.

                ++result; //substring of text (text[i ... i+m-1]) is a match, so increment the result counter.
            }
        }
        long endTime = System.currentTimeMillis();
        long TimeTaken = endTime - startTime;
        System.out.println("Using ArrayLists: " + result + " matches, derived in " + TimeTaken + " milliseconds.");
        return result;
    }

    public static int findBruteLinkedList(LinkedList<Character> text, LinkedList<Character> pattern) {
        int n = text.size();
        int m = pattern.size();
        int result = 0;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i <= n - m; i++) { //start at every letter in the text, minus the length of our search word.
            int k = 0; //variable to track indexing through pattern.
            while (k < m && text.get(i + k) == pattern.get(k)) { //while the considered string matches pattern.
                k++;
            }
            if (k == m) { //if we get all the way through pattern and our considered string still matches.

                ++result; //substring of text (text[i ... i+m-1]) is a match, so increment the result counter.
            }
        }
        long endTime = System.currentTimeMillis();
        long TimeTaken = endTime - startTime;
        System.out.println("Using ArrayLists: " + result + " matches, derived in " + TimeTaken + " milliseconds.");
        return result;
    }
}