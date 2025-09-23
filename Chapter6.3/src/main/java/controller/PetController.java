package controller;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import model.Pet;
import view.PetView;

public class PetController {
    private final Pet model;
    private final PetView view;

    public PetController(Pet model, PetView view, Canvas canvas) {
        this.model = model;
        this.view = view;

        // Mouse input
        canvas.setOnMouseMoved(e -> model.setTarget(e.getX(), e.getY()));
        canvas.setOnMouseExited(e -> model.clearTarget());

        // Background thread for updating pet
        new Thread(() -> {
            try {
                while (true) {
                    model.update();

                    // Safe GUI update
                    Platform.runLater(() -> view.render());

                    Thread.sleep(16); // ~60 FPS
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
