package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {
    private static String cursorPosFormat = "Cursor: (%d,%d)";
    private static String counterFormat = "Animal count: %d Civil counter: %d Medic count: %d Ill count: %d Immune count: %d Healthy count: %d";


    private Label cursor;
    private Label counter;

    public InfoBar() {
        this.cursor = new Label();
        this.counter = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(counter, spacer, this.cursor);

    }

    public void setCursorPosition(int x, int y) {
        this.cursor.setText(String.format(cursorPosFormat, x, y));
    }
    public void setCounterFormat(int animalCo, int civilCo, int medicCo, int illCo, int immCo, int healCo) {
        this.counter.setText(String.format(counterFormat, animalCo, civilCo, medicCo, illCo, immCo, healCo));
    }

}
