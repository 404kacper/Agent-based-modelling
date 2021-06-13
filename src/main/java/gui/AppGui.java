package gui;

import agentSim.Simulation;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.map.creator.MapCreator;
import gui.viewModel.MapViewModel;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class AppGui extends Application {

    Scene sceneInput, sceneSimulation;

//    Gui must be ran with gradle run task

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        InputView inputView = new InputView();
        sceneInput = new Scene(inputView);
        stage.setScene(sceneInput);
        stage.show();

        customizeStage(stage);
    }

    public void customizeStage(Stage stage) {
        stage.setTitle("SIR disease spread simulation");

        Image icon = new Image("https://image.flaticon.com/icons/png/512/1909/1909848.png");
        stage.getIcons().add(icon);
    }
}
