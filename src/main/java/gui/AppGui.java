package gui;

import agentSim.Simulation;
import agentSim.agent.IAgent;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import gui.viewModel.MapViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;


public class AppGui extends Application {

//    Gui must be ran with gradle run task

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MapViewModel mapViewModel = new MapViewModel();

        MapCreator mapCreator = new MapCreator(10, 10);
        IAgentCreator currentAgents = new AgentCreator(24,3,3,27,0,3);
        Simulation simulation = new Simulation(mapCreator,currentAgents,0, 10);

        MainView mainView = new MainView(mapViewModel, simulation, simulation.getSimulationMap());

        Scene scene = new Scene(mainView, 640, 480);

        stage.setScene(scene);
        stage.show();

        mapViewModel.setMapModel(simulation.getSimulationMap());

        customizeStage(stage);
    }

    public void customizeStage(Stage stage) {
        stage.setTitle("Demo app");

        Image icon = new Image("https://image.flaticon.com/icons/png/512/1909/1909848.png");
        stage.getIcons().add(icon);
    }
}
