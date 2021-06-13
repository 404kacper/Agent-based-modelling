package gui;

import gui.controlers.Data;
import gui.controlers.SceneController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InputView extends VBox {
    Button sceneButton;

    Label labelIter;
    Label labelCivil;
    Label labelAnimal;
    Label medicLabel;
    Label labelIll;
    Label labelImmune;
    Label labelHealthy;

    TextField animalInput;
    TextField civilInput;
    TextField medicInput;
    TextField illInput;
    TextField immuneInput;
    TextField healthyInput;
    TextField maxIterInput;

    public InputView() {
        createInputFields();

        sceneButton = new Button("Go to simulation");
        validateNumericalInput(maxIterInput);
        validateNumericalInput(civilInput);
        validateNumericalInput(animalInput);
        validateNumericalInput(medicInput);
        validateNumericalInput(illInput);
        validateNumericalInput(healthyInput);
        validateNumericalInput(immuneInput);

        disableSimulationButton();
        SceneController sc = new SceneController();
        sceneButton.setOnAction(e -> {
            passData();
            sc.switchToSceneSimulationScene(e);
        });


        this.getChildren().addAll(labelIter, maxIterInput, labelCivil, civilInput, labelAnimal, animalInput, medicLabel, medicInput, labelHealthy, healthyInput, labelIll, illInput, labelImmune, immuneInput, sceneButton);
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

    public void createInputFields() {
        // Form

        // Iterations
        labelIter = new Label("Enter number of iterations");

        maxIterInput = new TextField();

        // Civil count

        labelCivil = new Label("Enter number of civilians");

        civilInput = new TextField();

        // Animal count

        labelAnimal = new Label("Enter number of animals");

        animalInput = new TextField();

        // Medic count

        medicLabel = new Label("Enter number of medics");

        medicInput = new TextField();

        // Ill count

        labelIll = new Label("Enter number of ill agents");

        illInput = new TextField();

        // Immune count

        labelImmune = new Label("Enter number of immune agents");

        immuneInput = new TextField();

        // Healthy count

        labelHealthy = new Label("Enter number of healthy agents");

        healthyInput = new TextField();
    }

    public void disableSimulationButton() {
        sceneButton.disableProperty().bind(
                Bindings.isEmpty(maxIterInput.textProperty())
                        .or(Bindings.isEmpty(civilInput.textProperty()))
                        .or(Bindings.isEmpty(animalInput.textProperty()))
                        .or(Bindings.isEmpty(medicInput.textProperty()))
                        .or(Bindings.isEmpty(illInput.textProperty()))
                        .or(Bindings.isEmpty(immuneInput.textProperty()))
                        .or(Bindings.isEmpty(healthyInput.textProperty()))
        );
    }

    public void passData() {
        Data.maxIterInputText = maxIterInput.getText();
        Data.animalInputText = animalInput.getText();
        Data.civilInputText = civilInput.getText();
        Data.medicInputText = medicInput.getText();
        Data.illInputText = illInput.getText();
        Data.immuneInputText = immuneInput.getText();
        Data.healthyInputText = healthyInput.getText();
    }

}
