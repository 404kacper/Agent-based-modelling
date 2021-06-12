package gui;

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
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 640, 480);

        stage.setTitle("Demo app");

        Image icon = new Image("https://image.flaticon.com/icons/png/512/1909/1909848.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
        mainView.draw();
    }
}
