package gui.controlers;

import agentSim.Simulation;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.map.creator.MapCreator;
import gui.InputView;
import gui.MainView;
import gui.viewModel.MapViewModel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;

    public void switchToScene1(ActionEvent event) {
        InputView inputView = new InputView();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(inputView, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneSimulationScene(ActionEvent event) {
        System.out.println(Data.inputText);
        MapViewModel mapViewModel = new MapViewModel();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        MapCreator mapCreator = new MapCreator(10, 10);
        IAgentCreator currentAgents = new AgentCreator(24,3,3,27,0,3);
        Simulation simulation = new Simulation(mapCreator,currentAgents,0, Integer.parseInt(Data.inputText));
        MainView mainView = new MainView(mapViewModel, simulation);
        Scene scene = new Scene(mainView, 640, 480);
//        Set initial state of simulation map
        mapViewModel.setMapModel(simulation.getSimulationMap());

        stage.setScene(scene);
        stage.show();
    }
}

