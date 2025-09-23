package view;

import controller.PetController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Pet;

public class PetView extends Application {
    private Pet model;
    private Canvas canvas;
    private GraphicsContext gc;
    private Image petImage;

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(600, 400);
        gc = canvas.getGraphicsContext2D();

        model = new Pet(300, 200);
        petImage = new Image("cat.png");

        // Pass view reference to controller
        new PetController(model, this, canvas);

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Virtual Pet Cursor Chaser");
        stage.show();
    }

    public void render() {
        gc.setFill(Color.DIMGREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(petImage,
                model.getX() - model.getSize() / 2,
                model.getY() - model.getSize() / 2,
                model.getSize(),
                model.getSize());
    }
}
