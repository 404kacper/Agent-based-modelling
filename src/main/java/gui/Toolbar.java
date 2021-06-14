package gui;

import agentSim.Simulation;
import agentSim.map.IMap;
import gui.controlers.SceneController;
import gui.viewModel.MapViewModel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Toolbar extends ToolBar {
    private MapViewModel mapViewModel;

    private Simulator simulator;

    public Toolbar(Simulation sim, MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
        this.simulator = new Simulator(this.mapViewModel, sim);

        HBox itemSpacing = new HBox(20);
        StackPane left = new StackPane();
        left.setPrefSize(400, 10);
        StackPane right = new StackPane();
        right.setPrefSize(400, 10);

        Button step = new Button("step");
        step.setOnAction(this::handleStep);
        Button stop = new Button("stop");
        stop.setOnAction(this::handleStop);
        Button start = new Button("start");
        start.setOnAction(this::handleStart);

        Button inputSwitch = new Button("go to input");
        SceneController sc = new SceneController();
        inputSwitch.setOnAction(event ->  {
            sc.switchToScene1(event);
            handleStop(event);
        });

        itemSpacing.getChildren().addAll(left, step, start, stop, inputSwitch, right);
        this.getItems().addAll(itemSpacing);
    }

    private void handleStart(ActionEvent actionEvent) {
        this.simulator.start();
    }

    private void handleStop(ActionEvent actionEvent) {
        this.simulator.stop();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("step pressed");
        this.simulator.doStep();
    }

}
