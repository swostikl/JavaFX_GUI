package application;

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

public class CurrencyApp extends Application {

    private final CurrencyController controller = new CurrencyController();
    private final CurrencyDao currencyDao = new CurrencyDao();

    private ComboBox<Currency> fromCurrencyBox;
    private ComboBox<Currency> toCurrencyBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Currency Converter (JPA)");

        // GUI elements
        TextField amountField = new TextField();
        Label resultLabel = new Label("Converted Amount: ");
        Button convertButton = new Button("Convert");
        Button addCurrencyButton = new Button("Add Currency");

        fromCurrencyBox = new ComboBox<>();
        toCurrencyBox = new ComboBox<>();
        loadCurrencies();

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Amount:"), 0, 0);
        grid.add(amountField, 1, 0);
        grid.add(new Label("From:"), 0, 1);
        grid.add(fromCurrencyBox, 1, 1);
        grid.add(new Label("To:"), 0, 2);
        grid.add(toCurrencyBox, 1, 2);
        grid.add(convertButton, 1, 3);
        grid.add(resultLabel, 1, 4);
        grid.add(addCurrencyButton, 1, 5);

        // Optional: make Add Currency button span 2 columns
        GridPane.setColumnSpan(addCurrencyButton, 2);

        // Button actions
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
                resultLabel.setText(String.format("Converted Amount: %.2f", result));
            } catch (NumberFormatException e) {
                showError("Amount must be a valid number.");
            } catch (Exception e) {
                showError(e.getMessage());
            }
        });

        addCurrencyButton.setOnAction(event -> openAddCurrencyWindow());

        Scene scene = new Scene(grid, 420, 360); // slightly bigger window
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openAddCurrencyWindow() {
        Stage newStage = new Stage();
        newStage.setTitle("Add New Currency");

        TextField abbrField = new TextField();
        TextField nameField = new TextField();
        TextField rateField = new TextField();
        Button saveButton = new Button("Save");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

        pane.add(new Label("Abbreviation:"), 0, 0);
        pane.add(abbrField, 1, 0);
        pane.add(new Label("Name:"), 0, 1);
        pane.add(nameField, 1, 1);
        pane.add(new Label("Rate to USD:"), 0, 2);
        pane.add(rateField, 1, 2);
        pane.add(saveButton, 1, 3);

        saveButton.setOnAction(e -> {
            try {
                String abbr = abbrField.getText().trim();
                String name = nameField.getText().trim();
                double rate;

                if (abbr.isEmpty() || name.isEmpty() || rateField.getText().trim().isEmpty()) {
                    showError("All fields are required.");
                    return;
                }

                try {
                    rate = Double.parseDouble(rateField.getText());
                } catch (NumberFormatException ex) {
                    showError("Rate must be a valid number.");
                    return;
                }

                currencyDao.addCurrency(new Currency(abbr, name, rate));
                newStage.close();
            } catch (Exception ex) {
                showError("Error adding currency: " + ex.getMessage());
            }
        });

        newStage.setScene(new Scene(pane, 300, 220));
        newStage.showAndWait(); // wait for window to close before continuing

        // Refresh ComboBoxes after adding a new currency
        loadCurrencies();
    }

    private void loadCurrencies() {
        try {
            List<Currency> currencies = currencyDao.getAllCurrencies();
            fromCurrencyBox.getItems().setAll(currencies);
            toCurrencyBox.getItems().setAll(currencies);

            // Optional: select first currency by default
            if (!currencies.isEmpty()) {
                fromCurrencyBox.getSelectionModel().select(0);
                toCurrencyBox.getSelectionModel().select(0);
            }
        } catch (Exception e) {
            showError("Failed to load currencies: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
