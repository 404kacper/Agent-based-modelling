package gui;

import gui.controlers.Data;
import gui.controlers.SceneController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class CustomizationView extends VBox {

    Button sceneButton;

    Slider mapWidthSlider;
    Slider mapHeightSlider;

    Slider animalSpeedSlider;
    Slider civilSpeedSlider;
    Slider medicSpeedSlider;

    Label mapWidthLabel;
    Label mapHeightLabel;

    Label animalSpeedLabel;
    Label civilSpeedLabel;
    Label medicSpeedLabel;

    public CustomizationView() {
        sceneButton = new Button("Go to simulation");

        mapWidthLabel = new Label("Set map width");
        mapWidthSlider = new Slider(0, 10, 10);

        mapHeightLabel = new Label("Set map height");
        mapHeightSlider = new Slider(0, 10, 10);

        int speedLimit = (int) Math.ceil(mapHeightSlider.getValue() / 3);

        animalSpeedLabel = new Label("Set animal move speed");
        animalSpeedSlider = new Slider(0, speedLimit, 1);

        civilSpeedLabel = new Label("Set civil move speed");
        civilSpeedSlider = new Slider(0, speedLimit, 1);

        medicSpeedLabel = new Label("Set civil move speed");
        medicSpeedSlider = new Slider(0, speedLimit, 1);

        sliderCustomizeToInt(mapWidthSlider);
        sliderCustomizeToInt(mapHeightSlider);

        sliderCustomizeToInt(animalSpeedSlider);
        sliderCustomizeToInt(civilSpeedSlider);
        sliderCustomizeToInt(medicSpeedSlider);

        SceneController sc = new SceneController();
        sceneButton.setOnAction(e -> {
            passData();
            sc.switchToSimulationScene(e);
        });

        this.getChildren().addAll(mapHeightLabel, mapWidthSlider, mapWidthLabel, mapHeightSlider, animalSpeedLabel, animalSpeedSlider, civilSpeedLabel, civilSpeedSlider, medicSpeedLabel, medicSpeedSlider, sceneButton);
    }

    private void sliderCustomizeToInt(Slider slider) {
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
    }

    private void passData() {
        Data.mapWidth = (int) mapWidthSlider.getValue();
        Data.mapHeight = (int) mapHeightSlider.getValue();
        Data.animalSpeed = (int) animalSpeedSlider.getValue();
        Data.civilSpeed = (int) civilSpeedSlider.getValue();
        Data.medicSpeed = (int) medicSpeedSlider.getValue();
    }
}
