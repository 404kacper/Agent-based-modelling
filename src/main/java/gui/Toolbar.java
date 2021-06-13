package gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {
    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;
        Button step = new Button("step");
        step.setOnAction(this::handleStep);
        Button stop = new Button("stop");
        stop.setOnAction(this::handleStop);
        Button start = new Button("start");
        start.setOnAction(this::handleStart);

        this.getItems().addAll(step, start, stop);
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.getSimulator().start();
    }

    private void handleStop(ActionEvent actionEvent) {
        this.mainView.getSimulator().stop();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("step pressed");
        this.mainView.getSimulation().runSimulationStep();
        this.mainView.draw();
    }

}
