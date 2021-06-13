package gui;

import gui.controlers.Data;
import gui.controlers.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InputView extends VBox {
    Button sceneButton;
    TextField maxIterInput;

    public InputView() {
        Label label1 = new Label("Enter number of iterations");

        sceneButton = new Button("Go to simulation");
        SceneController sc = new SceneController();
        sceneButton.setOnAction(e -> {
            passData();
            sc.switchToSceneSimulationScene(e);
        });

        // Form
        maxIterInput = new TextField();
        validateNumericalInput(maxIterInput);
        disableEmptyInput(maxIterInput);
        sceneButton.setDisable(true);


        this.getChildren().addAll(label1, sceneButton, maxIterInput);
    }

    public void validateNumericalInput(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void disableEmptyInput(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String s1) {
                if(s1.equals(""))
                    sceneButton.setDisable(true);
                else
                    sceneButton.setDisable(false);
            }
        });
    }

    public void passData() {
        Data.inputText = maxIterInput.getText();
    }

}
