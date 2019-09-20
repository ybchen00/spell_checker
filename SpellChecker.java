// author: Yibing Chen
// This program is a SpellChecker Object takes in a txt file with incorrect spellings and return a list of suggestions
import java.io.*;
import java.util.*;

public class SpellChecker {

    private HashSet dic = new HashSet<>();
    
    /*
     *  Parse the dictionary file, storing the words in a HashSet instance. This dictionary: as reference for spellingcheck. 
     */
    public SpellChecker(String filename) throws FileNotFoundException {

        readFile(filename);
 

    }
    
    /*
     *  This method should return a list of misspelled words.
     */
    private void readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while(input.hasNextLine()){ 
            String line = input.nextLine().toLowerCase();
            dic.add(line);
        }
    }
    
    /*
     * Return all possible suggestions from EACH one of the techniques above.  (Since using Set, only unique suggestions)
     */
    public List<String> getIncorrectWords(String filename) {
        File inputFile;
        Scanner scan;
        List<String> incorrectWords = new ArrayList<String>();
        try{
            inputFile = new File(filename);
            scan = new Scanner(inputFile);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] words = line.split(" ");
                for(int i = 0; i < words.length; i++){
                    String temp;
                    temp = words[i].trim().toLowerCase();
                    if(temp == null || temp.isEmpty()) continue;

                    String wordNoPunc = "";
                    for(int j = 0; j < temp.length(); j++){
                        char tempChar = temp.charAt(j);
                        if(isPunc(tempChar)) continue;
                        else wordNoPunc += tempChar;
                    }
                if(!dic.contains(wordNoPunc)) incorrectWords.add(wordNoPunc);
                }
            }
        }
        catch(IOException e){throw new IllegalArgumentException();}
        return incorrectWords;        
        
    }

    
    private boolean isPunc(char x){
        if(Character.isDigit(x)||Character.isLetter(x)) return false;
        return true;
    }
    
	public HashSet<String> getSuggestions(String word){
        HashSet suggestions = new HashSet<>();
        if (dic.contains(word)) return suggestions;
        this.addChar(word, suggestions);
        this.removeChar(word, suggestions);
        this.swapChars(word, suggestions);
        return suggestions;
    }
    
    private void addChar(String word, HashSet suggestions){
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i = 0; i < 25; i++){ //choose letters to insert
            for(int j = 0; j <= word.length(); j++){ //choose position to insert
                String newWord = word.substring(0, j) + letters[i] + word.substring(j);
                if(dic.contains(newWord)) suggestions.add(newWord);
            }
        }
    }
    
    private void removeChar(String word, HashSet suggestions){
        for(int i = 0; i < word.length(); i++){
            String newWord = word.substring(0, i) + word.substring(i+1);
            if(dic.contains(newWord)) suggestions.add(newWord);
        }
    }
    
    private void swapChars(String word, HashSet suggestions){ //testing
        for(int i = 0; i < word.length() - 1; i++){
            String newWord = "";
            char temp = word.charAt(i);
            newWord = word.substring(0, i) + word.charAt(i+1) + temp + word.substring(i+2);
            if(dic.contains(newWord)) suggestions.add(newWord);
        }
    }
    
}
