package gui;

import agentSim.Simulation;
import gui.viewModel.MapViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private MapViewModel mapViewModel;
    private Simulation simulation;

    public Simulator(MapViewModel mapView, Simulation simulation) {
        this.mapViewModel = mapView;
        this.simulation = simulation;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> this.doStep()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void doStep() {
        this.simulation.runSimulationStep();
        this.mapViewModel.setMapModel(this.simulation.getSimulationMap());
    }

    public void start() {
    this.timeline.play();
    }


    public void stop() {
    this.timeline.stop();
    }
}
