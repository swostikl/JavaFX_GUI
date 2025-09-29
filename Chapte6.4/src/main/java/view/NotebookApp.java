package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotebookApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/notebook_view.fxml"));
        Scene scene = new Scene(loader.load());

        // Set up the scene and stage
        primaryStage.setTitle("Simple Notebook");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
