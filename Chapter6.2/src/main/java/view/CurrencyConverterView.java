package view;


import controller.CurrencyConverterController;
import model.Currency;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CurrencyConverterView extends Application {
    private CurrencyConverterController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new CurrencyConverterController();

        // Labels
        Label titleLabel = new Label("Currency Converter");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label fromLabel = new Label("From Currency:");
        Label toLabel = new Label("To Currency:");
        Label amountLabel = new Label("Amount:");
        Label resultLabel = new Label("Converted Amount:");

        // Input fields
        TextField amountField = new TextField();
        TextField resultField = new TextField();
        resultField.setEditable(false);

        // ChoiceBoxes
        ComboBox<Currency> fromCurrencyBox = new ComboBox<>();
        ComboBox<Currency> toCurrencyBox = new ComboBox<>();
        fromCurrencyBox.getItems().addAll(controller.getCurrencies());
        toCurrencyBox.getItems().addAll(controller.getCurrencies());

        fromCurrencyBox.getSelectionModel().selectFirst();
        toCurrencyBox.getSelectionModel().select(1);

        // Button
        Button convertButton = new Button("Convert");

        convertButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                Currency from = fromCurrencyBox.getValue();
                Currency to = toCurrencyBox.getValue();

                double result = controller.convert(amount, from, to);
                resultField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Amount");
                alert.setContentText("Please enter a valid numeric amount.");
                alert.showAndWait();
            }
        });

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(titleLabel, 0, 0, 2, 1);

        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);

        grid.add(fromLabel, 0, 2);
        grid.add(fromCurrencyBox, 1, 2);

        grid.add(toLabel, 0, 3);
        grid.add(toCurrencyBox, 1, 3);

        grid.add(convertButton, 0, 4);

        grid.add(resultLabel, 0, 5);
        grid.add(resultField, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
