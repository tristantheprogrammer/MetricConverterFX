import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverterGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        TextField inputField = new TextField();
        Label resultLabel = new Label();

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> {
            String userInput = inputField.getText();
            double result = convert(userInput);
            resultLabel.setText("Result: " + result);
        });

        grid.add(inputField, 0, 0, 2, 1);
        grid.add(convertButton, 0, 1);
        grid.add(resultLabel, 1, 1);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private double convert(String userInput) {
        try {
            String[] parts = userInput.split("=");
            if (parts.length != 2) {
                return -1;
            }

            String sourceUnit = parts[0].trim();
            String targetUnit = parts[1].trim();

            double value = Double.parseDouble(sourceUnit.split(" ")[0]);
            String source = sourceUnit.split(" ")[1];
            String target = targetUnit.split(" ")[0];

            return convert(value, source, target);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private double convert(double value, String source, String target) {
        if (source.equalsIgnoreCase("km") && target.equalsIgnoreCase("mile")) {
            return value * 0.621371;
        } else if (source.equalsIgnoreCase("kg") && target.equalsIgnoreCase("lb")) {
            return value * 2.20462;
        } else if (source.equalsIgnoreCase("g") && target.equalsIgnoreCase("oz")) {
            return value * 0.035274;
        } else if (source.equalsIgnoreCase("mm") && target.equalsIgnoreCase("inch")) {
            return value * 0.0393701;
        } else {
            return -1;
        }
    }
}
