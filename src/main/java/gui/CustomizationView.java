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

    Slider resistanceDurationSlider;
    Slider infectionDurationSlider;

    Slider vaccinationFovSlider;
    Slider infectionFovSlider;

    Label mapWidthLabel;
    Label mapHeightLabel;

    Label animalSpeedLabel;
    Label civilSpeedLabel;
    Label medicSpeedLabel;

    Label resistanceDurationLabel;
    Label infectionDurationLabel;

    Label vaccinationFovLabel;
    Label infectionFovLabel;

    public CustomizationView() {
        sceneButton = new Button("Go to simulation");

        // Map size
        mapWidthLabel = new Label("Set map width");
        mapWidthSlider = new Slider(0, 10, 10);

        mapHeightLabel = new Label("Set map height");
        mapHeightSlider = new Slider(0, 10, 10);

        sliderCustomizeToInt(mapWidthSlider);
        sliderCustomizeToInt(mapHeightSlider);


        //  Move speed
        int speedLimit = (int) Math.ceil(mapHeightSlider.getValue() / 3);

        animalSpeedLabel = new Label("Set animal move speed");
        animalSpeedSlider = new Slider(0, speedLimit, 1);

        civilSpeedLabel = new Label("Set civil move speed");
        civilSpeedSlider = new Slider(0, speedLimit, 1);

        medicSpeedLabel = new Label("Set medic move speed");
        medicSpeedSlider = new Slider(0, speedLimit, 1);

        sliderCustomizeToInt(animalSpeedSlider);
        sliderCustomizeToInt(civilSpeedSlider);
        sliderCustomizeToInt(medicSpeedSlider);

        // Interaction durations
        infectionDurationLabel = new Label("Set how long infections last");
        infectionDurationSlider = new Slider(0, 10, 1);

        resistanceDurationLabel = new Label("Set how long resistance lasts");
        resistanceDurationSlider = new Slider(0, 10, 1);

        sliderCustomizeToInt(infectionDurationSlider);
        sliderCustomizeToInt(resistanceDurationSlider);

        // Fields of view
        vaccinationFovLabel = new Label("Set how far medics vaccinate");
        vaccinationFovSlider = new Slider(0, 10, 1);

        infectionFovLabel = new Label("Set how far agents infect");
        infectionFovSlider = new Slider(0, 10, 1);

        sliderCustomizeToInt(vaccinationFovSlider);
        sliderCustomizeToInt(infectionFovSlider);

        SceneController sc = new SceneController();
        sceneButton.setOnAction(e -> {
            passData();
            sc.switchToInputScene(e);
        });

        this.getChildren().addAll(mapHeightLabel, mapWidthSlider, mapWidthLabel, mapHeightSlider, animalSpeedLabel, animalSpeedSlider, civilSpeedLabel, civilSpeedSlider, medicSpeedLabel, medicSpeedSlider, infectionDurationLabel, infectionDurationSlider, resistanceDurationLabel, resistanceDurationSlider, vaccinationFovLabel, vaccinationFovSlider, infectionFovLabel, infectionFovSlider, sceneButton);
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
        Data.infectionDuration = (int) infectionDurationSlider.getValue();
        Data.resistanceDuration = (int) resistanceDurationSlider.getValue();
        Data.vaccinationFov = (int) vaccinationFovSlider.getValue();
        Data.infectionFov = (int) infectionFovSlider.getValue();
    }
}
