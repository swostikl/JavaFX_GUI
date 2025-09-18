package model;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> words; // it stores the words and meaning of the word
    private String lastSearchedWord; // searched word

    public Dictionary(){
        words = new HashMap<>();
        lastSearchedWord = null;
    }

    // methods to add word and their meaning

    public void addWord(String word, String meaning){
        words.put (word, meaning); // put the word and their meaning in words

    }

    // getters gor last search word
    public String getWord(){
        return lastSearchedWord;
    }

    // Method to search the word and their meaning
     public String getMeaning(String word){
        lastSearchedWord = word; // update the word which has been searched
        if(word == null || word.trim().isEmpty()){
            return ("Please enter the word");
        }
        if(words.containsKey(word)){
            return words.get(word);
        }

        else {
            return ("Word do not exist in dictionary");
        }

     }
}
