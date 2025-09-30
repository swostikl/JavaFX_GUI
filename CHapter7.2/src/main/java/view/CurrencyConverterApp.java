package view;

import controller.CurrencyController;
import dao.CurrencyDao;
import entity.Currency;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class CurrencyConverterApp extends Application {

    private final CurrencyController controller = new CurrencyController();
    private final CurrencyDao currencyDao = new CurrencyDao();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Currency Converter");

        // UI elements
        TextField amountField = new TextField();
        Label resultLabel = new Label("Result: ");
        Button convertButton = new Button("Convert");

        // ChoiceBoxes
        ComboBox<Currency> fromCurrencyBox = new ComboBox<>();
        ComboBox<Currency> toCurrencyBox = new ComboBox<>();

        // Load currencies from database
        try {
            List<Currency> currencies = currencyDao.getAllCurrencies();
            fromCurrencyBox.getItems().addAll(currencies);
            toCurrencyBox.getItems().addAll(currencies);
        } catch (RuntimeException e) {
            showError("Failed to load currencies: " + e.getMessage());
        }

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Amount:"), 0, 0);
        grid.add(amountField, 1, 0);

        grid.add(new Label("From Currency:"), 0, 1);
        grid.add(fromCurrencyBox, 1, 1);

        grid.add(new Label("To Currency:"), 0, 2);
        grid.add(toCurrencyBox, 1, 2);

        grid.add(convertButton, 1, 3);
        grid.add(resultLabel, 1, 4);

        // Button action
        convertButton.setOnAction(event -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                Currency from = fromCurrencyBox.getValue();
                Currency to = toCurrencyBox.getValue();

                if (from == null || to == null) {
                    showError("Please select both currencies.");
                    return;
                }

                double result = controller.convert(from.getAbbreviation(), to.getAbbreviation(), amount);
                if (result >= 0) {
                    resultLabel.setText(String.format("Result: %.2f", result));
                } else {
                    showError("Conversion failed. Check DB connection or input.");
                }
            } catch (NumberFormatException e) {
                showError("Invalid number. Please enter a valid amount.");
            } catch (Exception e) {
                showError("Error: " + e.getMessage());
            }
        });

        // Scene
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

}
