package gui;

import agentSim.Simulation;
import agentSim.agent.IAgent;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;


public class MainView extends VBox {
    private InfoBar infoBar;
    private Canvas canvas;

    private Affine affine;
    private IMap simulationMap;

    public Simulation getSimulation() {
        return simulation;
    }

    private Simulation simulation;

    private Simulator simulator;


    public MainView() {
        MapCreator currentMap = new MapCreator(10, 10);

        IAgentCreator currentAgents = new AgentCreator(10,1,1,11,0,1);

        Simulation sim = new Simulation(currentMap, currentAgents,54, 100);

        simulation = sim;

        simulationMap = sim.getSimulationMap();

        this.simulator = new Simulator(this, this.simulation);

        Toolbar toolbar = new Toolbar(this);

        this.infoBar = new InfoBar();
        this.infoBar.setCursorPosition(0,0);

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.canvas = new Canvas(400,400);

        this.canvas.setOnMouseMoved(this::handleMoved);

        this.getChildren().addAll(toolbar, this.canvas, spacer, infoBar);

        this.affine = new Affine();
        this.affine.appendScale(400/10f, 400/10f);
    }

    private void handleMoved(MouseEvent mouseEvent) {
        Point2D simCoord = this.getSimulationCoordinates(mouseEvent);

        this.infoBar.setCursorPosition((int)simCoord.getX(), (int)simCoord.getY());
    }

    private javafx.geometry.Point2D getSimulationCoordinates(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            return simCoord;
        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException("Non invertable transform");
        }

    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        for (int x = 0; x < this.simulationMap.getXDim(); x++) {
            for (int y = 0; y < this.simulationMap.getYDim(); y++) {
                IAgent currAgent = simulationMap.getAgent(x,y);
                g.setFill(Color.BLACK);
                if (currAgent!=null) {
                    int currHealth = currAgent.getHealth();
                    switch (currHealth) {
                        case 0:
                            g.setFill(Color.GREEN);
                            break;
                        case 1:
                            g.setFill(Color.RED);
                            break;
                        case 2:
                            g.setFill(Color.BLUE);
                            break;
                    }
//                    Arrays retrieve data according to rows and columns
                    g.fillRect(y,x, 1,1);
                }
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

    public Simulator getSimulator() {
        return simulator;
    }
}
