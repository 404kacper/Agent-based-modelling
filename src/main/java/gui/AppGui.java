package gui;

import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import gui.viewModel.MapViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AppGui extends Application {

//    Gui must be ran with gradle run task

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MapViewModel mapViewModel = new MapViewModel();

        MapCreator mapCreator = new MapCreator(10, 10);
        IMap map = mapCreator.createMap();
        MainView mainView = new MainView(mapViewModel, mapCreator, map);

        Scene scene = new Scene(mainView, 640, 480);

        stage.setScene(scene);
        stage.show();

        mapViewModel.setMapModel(map);

        customizeStage(stage);
    }

    public void customizeStage(Stage stage) {
        stage.setTitle("Demo app");

        Image icon = new Image("https://image.flaticon.com/icons/png/512/1909/1909848.png");
        stage.getIcons().add(icon);
    }
}
