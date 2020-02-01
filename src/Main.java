import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;


public class Main {

    public static void main(String[] args) {
        Scanner patternScanner = new Scanner(System.in);
        String patternString = patternScanner.nextLine();

        Scanner dirScanner = new Scanner(System.in);
        String dirString = dirScanner.nextLine();

        String fileName = "C:\\Users\\Jarred\\Documents\\SCHOOL\\SYSC 2100\\Assignment 02";

        List<Character> textFile = Collections.emptyList();

        try {
            Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }


    public static int findBrute(List<Character> text, List<Character> pattern) {
        int n = text.size();
        int m = pattern.size();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i <= n - m; i++) { //start at every letter in the text, minus the lenght of our search word.
            int k = 0; //variable to track indexing through pattern
            while (k < m && text.get(i + k) == pattern.get(k)) { //while the considered string matches pattern
                k++;
            }
            if (k == m) { //if we get all the way through pattern and our considered string still matches.
                long endTime = System.currentTimeMillis();
                return i; //substring of text (text[i ... i+m-1]) is a match
            }
        }
        return -1; //search retuned no matches, return -1.
    }



}