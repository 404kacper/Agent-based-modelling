package gui;

import agentSim.map.IMap;
import gui.viewModel.MapViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {
    private MainView mainView;
    private MapViewModel mapViewModel;

    private Simulator simulator;

    public Toolbar(MainView mainView, MapViewModel mapViewModel) {
        this.mainView = mainView;
        this.mapViewModel = mapViewModel;
        this.simulator = new Simulator(this.mapViewModel, mainView.getSim());
        Button step = new Button("step");
        step.setOnAction(this::handleStep);
        Button stop = new Button("stop");
        stop.setOnAction(this::handleStop);
        Button start = new Button("start");
        start.setOnAction(this::handleStart);

        this.getItems().addAll(step, start, stop);
    }

    private void handleStart(ActionEvent actionEvent) {
        this.simulator.start();
    }

    private void handleStop(ActionEvent actionEvent) {
        this.simulator.stop();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("step pressed");
        this.mainView.getSim().runSimulationStep();
        this.simulator.doStep();
    }

}
