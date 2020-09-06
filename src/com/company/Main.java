package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static final int maxWordLength = 30; // words greater then this value will be truncated
    private static int max = 0; // maximum count of different letters in the word
    private static HashSet<String> maxLettersCountWords = new HashSet<>(); // array with all words of the length equal to max

    private static void processNewWord(String word) {
        // count the number of different letters
        HashSet<Character> letters = new HashSet<>();
        for (Character c:word.toLowerCase().toCharArray()) {
            letters.add(c);
        }

        // update the answer
        if (letters.size() > max) {
            max = letters.size();
            maxLettersCountWords.clear();
        }
        if(letters.size() == max) {
            maxLettersCountWords.add(word.toLowerCase());
        }
    }

    public static void main(String[] args) {
        try {
            // read path to the data from stdin
            Scanner in = new Scanner(System.in);
            String filePath = in.nextLine();

            // reading data
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);

            StringBuilder lastWord = new StringBuilder();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (Character c:line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        if (lastWord.length() < maxWordLength) {
                            lastWord.append(c);
                        }
                    } else {
                        if (lastWord.length() != 0) {
                            processNewWord(lastWord.toString());
                        }
                        lastWord = new StringBuilder();
                    }
                }
                if (lastWord.length() != 0) {
                    processNewWord(lastWord.toString());
                }
                lastWord = new StringBuilder();
            }

            // output the results
            System.out.printf("Maximum count of different letters in the word: %d\n", max);
            for (String word:maxLettersCountWords) {
                System.out.println(word);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
    }
}
