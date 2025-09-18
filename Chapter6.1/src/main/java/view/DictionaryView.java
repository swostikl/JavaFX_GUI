package view;

import controller.DictionaryController;
import javafx.scene.control.TextField;
import model.Dictionary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class DictionaryView extends Application  {

    @Override
    public void start(Stage stage){
        //model

        Dictionary model = new Dictionary();
        DictionaryController controller = new DictionaryController(model);

        // post adding words via controller
        controller.addWord("CSS", "A stylesheet language for describing HTML presentation.");
        controller.addWord("IDE", "Integrated Development Environment; software application for writing, testing, and debugging code.");
        controller.addWord("Debugger", "A tool that allows you to run code step by step to find errors.");
        controller.addWord("Compiler", "A program that translates source code into machine code.");
        controller.addWord("Interpreter", "A program that executes source code line by line.");
        controller.addWord("Syntax Highlighting", "Feature that displays code in different colors for easier reading.");
        controller.addWord("Version Control", "System that manages changes to source code over time, e.g., Git.");
        controller.addWord("Refactoring", "Improving code structure without changing its behavior.");
        controller.addWord("Build Tool", "Software that automates compiling and packaging code, e.g., Maven or Gradle.");
        controller.addWord("Breakpoint", "A marker in the code where the debugger will pause execution.");
        controller.addWord("Code Snippet", "A small block of reusable code that can be inserted into a program.");



        // UI components
        Label text = new Label("Type Word to search : ");
        TextField input = new TextField();  // for typing word
        Button searchButton = new Button("Search");
        Button clearButton = new Button ("Clear ");
        Label result = new Label();  //display meaning




        //  Search Button action
        searchButton.setOnAction(e -> {
            String word = input.getText().trim();
            String meaning = controller.searchWord(word);
            if (meaning.equals("Please enter the word") || meaning.equals("Word do not exist in dictionary")){
                result.setText(meaning);
            }else {
                String lastSearchedWord = controller.getLastSearchedWord();
                result.setText( lastSearchedWord + " is " + meaning);
            }

        });

        // clear button action
        clearButton.setOnAction(e-> {
            input.clear();
            result.setText("");
        });

        //layout
        FlowPane componentGroup = new FlowPane();
        componentGroup.setHgap(30);
        componentGroup.setVgap(30);
        componentGroup.getChildren().addAll(text, input, searchButton,clearButton, result);



        Scene scene = new Scene(componentGroup, 600, 200);
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();


    }

}
