package gui.controlers;

import agentSim.Simulation;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.customizer.SimulationCustomizer;
import agentSim.map.creator.MapCreator;
import gui.CustomizationView;
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

    public void switchToInputScene(ActionEvent event) {
        InputView inputView = new InputView();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(inputView, 300, 350);
        stage.setX(850);
        stage.setY(200);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSliderScene(ActionEvent event) {
        CustomizationView customizationView = new CustomizationView();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(customizationView, 400, 500);
        stage.setScene(scene);
        stage.setX(850);
        stage.setY(200);
        stage.show();
    }

    public void switchToSimulationScene(ActionEvent event) {
        Integer maxIter = Integer.parseInt(Data.maxIterInputText);
        Integer animalCount = Integer.parseInt(Data.animalInputText);
        Integer civilCount = Integer.parseInt(Data.civilInputText);
        Integer medicCount = Integer.parseInt(Data.medicInputText);
        Integer illCount = Integer.parseInt(Data.illInputText);
        Integer immuneCount = Integer.parseInt(Data.immuneInputText);
        Integer healthyCount = Integer.parseInt(Data.healthyInputText);

        int mapWidth = Data.mapWidth;
        int mapHeight = Data.mapHeight;
        int civilSpeed = Data.civilSpeed;
        int animalSpeed = Data.animalSpeed;
        int medicSpeed = Data.medicSpeed;
        int infectionDuration = Data.infectionDuration;
        int resistanceDuration = Data.resistanceDuration;
        int vaccinationFov = Data.vaccinationFov;
        int infectionFov = Data.infectionFov;

        MapViewModel mapViewModel = new MapViewModel();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        MapCreator mapCreator = new MapCreator(mapWidth, mapHeight);

        SimulationCustomizer customizer = new SimulationCustomizer();

        customizer.setSpeedValues(civilSpeed, animalSpeed, medicSpeed);
        customizer.setInteractionValues(infectionDuration, resistanceDuration);
        customizer.setVaccinateFov(vaccinationFov);
        customizer.setInfectionFov(infectionFov);

        IAgentCreator currentAgents = new AgentCreator(healthyCount, illCount, immuneCount, civilCount, animalCount, medicCount, customizer);

        Simulation simulation = new Simulation(mapCreator, currentAgents, customizer, 0, maxIter);

        MainView mainView = new MainView(mapViewModel, simulation);
        Scene scene = new Scene(mainView);
        scene.getStylesheets().add(this.getClass().getResource("/styling/chartStyling.css").toExternalForm());
//        Set initial state of simulation map
        mapViewModel.setMapModel(simulation.getSimulationMap());

        stage.setX(500);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
}

