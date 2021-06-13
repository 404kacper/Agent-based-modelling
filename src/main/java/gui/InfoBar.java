package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {
    private static String cursorPosFormat = "Cursor: (%d,%d)";

    private Label cursor;

    public InfoBar() {
        this.cursor = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(spacer, this.cursor);

    }

    public void setCursorPosition(int x, int y) {
        this.cursor.setText(String.format(cursorPosFormat, x, y));
    }

}
