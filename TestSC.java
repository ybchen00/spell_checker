// author: Yibing Chen
// This program is a tester using the SpellChecker object.  It takes in "tester.txt" and prints in terminal suggestions for incorrect words.
// Can modify content in tester.txt for different test cases.
import java.util.*;
import java.io.*;

public class TestSC{
    
    public static void main(String[] args) throws FileNotFoundException {
    	// Import dictionary "words.txt"
        SpellChecker sc = new SpellChecker("words.txt");
        
        // Use method getIncorrectWords("filename") for a file with typos
        List<String> incorrectWords = sc.getIncorrectWords("tester.txt");
        
        System.out.println(incorrectWords);
        
        for(int i = 0; i < incorrectWords.size(); i++){
            System.out.println("Suggestions for " + incorrectWords.get(i) + " : " + sc.getSuggestions(incorrectWords.get(i)));
        }
        
    }
    
}