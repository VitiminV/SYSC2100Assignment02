import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CountSubstrings {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter the name of the file to be loaded: ");
        Scanner dirScanner = new Scanner(System.in);
        String dirString = dirScanner.nextLine();

        System.out.println("Please enter the the string you wish to search count: ");
        Scanner patternScanner = new Scanner(System.in);
        String patternString = patternScanner.nextLine();
        ArrayList<Character> patternChars = new ArrayList<Character>();
        for (char c : patternString.toCharArray()) {
            patternChars.add(c);
        }

        String fileName = "C:\\Users\\Jarred\\Documents\\SCHOOL\\SYSC 2100\\SYSC2100Assignment02\\" + dirString;

        ArrayList<Character> TextFile = readTextfile(fileName);

        findBruteArrayList(TextFile, patternChars);

        LinkedList<Character> LinkedTextFile = new LinkedList<>(TextFile);
        LinkedList<Character> LinkedPatternChars = new LinkedList<>(patternChars);
        findBruteLinkedList(LinkedTextFile, LinkedPatternChars);
    }

    private static ArrayList<Character> readTextfile(String fileName) throws IOException{
        ArrayList<Character> returnList = new ArrayList<Character>(); //empty list this function will fill and return.
        Path path = Paths.get(fileName); //the path given by the argument.
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){ //try reading the file at the location.
            String line = null; //the most recent line returned by the buffered reader.
            while ((line = reader.readLine()) != null) { //are we at the end of the text file?
                for (char c : line.toCharArray()) { //for every character in the current line...
                    returnList.add(c); //...put that character into a the list we want to fill.
                }
            }
        }
        return returnList;
    }

    public static int findBruteArrayList(ArrayList<Character> text, ArrayList<Character> pattern) {
        int n = text.size() - 1;
        int m = pattern.size() - 1;
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
        ListIterator<Character> textItr = text.listIterator(0);
        ListIterator<Character> patternItr = pattern.listIterator(0);
        int result = 0;
        long startTime = System.currentTimeMillis();
        while(textItr.hasNext()) { //are we at the end of the text?
            if(patternItr.hasNext()) { //are we at the end of our patten?
                if(textItr.next() == patternItr.next()){ //does the next character of the text match the next character of the pattern?
                    continue; //if it does, cool. no need to reset patternItr, so we let them both advance, and check the next character.
                }
                else { //characters don't match. reset patternItr.
                    patternItr = pattern.listIterator(0);
                }
            }
            else { //we're at the end of our patternItr, therefore we have a match. increment result, and reset patternItr.
                result++;
                patternItr = pattern.listIterator(0);
            }
        }
        long endTime = System.currentTimeMillis();
        long TimeTaken = endTime - startTime;
        System.out.println("Using LinkedLists: " + result + " matches, derived in " + TimeTaken + " milliseconds.");
        return result;
    }
}