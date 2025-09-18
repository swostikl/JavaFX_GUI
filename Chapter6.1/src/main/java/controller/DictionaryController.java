package controller;


import model.Dictionary;
public class DictionaryController{
    private Dictionary dictionary;

    public DictionaryController(Dictionary dictionary){
        this.dictionary = dictionary;

     // preloadind some words
        dictionary.addWord("Java","a general-purpose computer programming language designed to produce programs that will run on any computer system.");
        dictionary.addWord("Python", "a high-level general-purpose programming language.");
        dictionary.addWord("HTML", "a standardized system for tagging text files to achieve font, colour, graphic, and hyperlink effects on World Wide Web pages.");
        dictionary.addWord("JavaScript", "an object-oriented computer programming language commonly used to create interactive effects within web browsers.");

    }
    // search word through the model
    public String searchWord(String word){
        return dictionary.getMeaning(word);
    }

    //get last searched word
    public String getLastSearchedWord(){
        return dictionary.getWord();
    }

    public void addWord(String word, String meaning){
        dictionary.addWord (word, meaning);

    }



}
