package gui;

import gui.controlers.SceneController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InputView extends VBox {
    public InputView() {
        Label label1 = new Label("Welcome to first scene");
        Button button1 = new Button("Go to simulation");
        SceneController sc = new SceneController();
        button1.setOnAction(sc::switchToSceneSimulationScene);
        this.getChildren().addAll(label1,button1);
    }
}
