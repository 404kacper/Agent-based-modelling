package gui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AppGui extends Application {

    Scene initialScene;

//    Gui must be ran with gradle run task

    //TODO nexSteps:
    // - validate that number of states == number of agents
    // - possibly extend the customization by giving user the opportunity to define more parameters
    // - eg. infectionDuration, resistanceDuration, fieldOfView, moveDistance etc.

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Initial scene
        CustomizationView customizationView = new CustomizationView();
        initialScene = new Scene(customizationView);
        stage.setScene(initialScene);
        stage.show();

        customizeStage(stage);
    }

    public void customizeStage(Stage stage) {
        stage.setTitle("SIR disease spread simulation");

        Image icon = new Image("https://image.flaticon.com/icons/png/512/1909/1909848.png");
        stage.getIcons().add(icon);
    }
}
