package gui;

import agentSim.Simulation;
import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;


public class MainView extends VBox {
    private Button stepButton;
    private Canvas canvas;

    private Affine affine;

    private MapCreator mapCreator;
    private IMap simulationMap;

    public MainView() {
        this.stepButton = new Button("step");
        this.stepButton.setOnAction(actionEvent -> {
//            To be implemented tomorrow
//            Increments simulation iteration by one
//            Simulation.step();

        });
        this.canvas = new Canvas(400,400);

        this.getChildren().addAll(this.stepButton, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400/10f, 400/10f);
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        MapCreator currentMap = new MapCreator(10, 10);

        simulationMap = currentMap.createMap();

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.simulationMap.getXDim(); x++) {
            for (int y = 0; y < this.simulationMap.getYDim(); y++) {
//                g.fillRect(x,y, 1,1);
            }
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05f);
        for (int x = 0; x <= this.simulationMap.getXDim(); x++) {
            g.strokeLine(x, 0,x,10);
        }

        for (int y = 0; y <= this.simulationMap.getYDim(); y++) {
            g.strokeLine(0,y, 10,y);
        }
    }
}
