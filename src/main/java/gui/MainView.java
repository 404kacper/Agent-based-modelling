package gui;

import agentSim.Simulation;
import agentSim.agent.IAgent;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import gui.viewModel.MapViewModel;
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

    private Simulation simulation;

    private MapViewModel mapViewModel;


    public MainView(MapViewModel mapViewModel , MapCreator initialMap, IMap simMap) {
        this.mapViewModel = mapViewModel;
        this.simulationMap = simMap;

        this.mapViewModel.listenToMap(this::onMapChanged);

        IAgentCreator currentAgents = new AgentCreator(24,3,3,27,0,3);

        Simulation sim = new Simulation(initialMap, currentAgents,2, 100);

        simulation = sim;

        simulationMap = sim.getSimulationMap();

        Toolbar toolbar = new Toolbar(this, this.mapViewModel);

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

    private void onMapChanged(IMap iMap) {
        draw();
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
            throw new RuntimeException("Non invertible transform");
        }

    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        g.setStroke(Color.GRAY);
        drawSimulation(this.simulationMap);
        g.setLineWidth(0.05f);
        for (int x = 0; x <= this.simulationMap.getXDim(); x++) {
            g.strokeLine(x, 0,x,10);
        }

        for (int y = 0; y <= this.simulationMap.getYDim(); y++) {
            g.strokeLine(0,y, 10,y);
        }
    }

    public void drawSimulation(IMap mapToBeDrawn) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setStroke(Color.GRAY);

        for (int x = 0; x < mapToBeDrawn.getXDim(); x++) {
            for (int y = 0; y < mapToBeDrawn.getYDim(); y++) {
                IAgent currAgent = mapToBeDrawn.getAgent(x,y);
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

    }

    public Simulation getSimulation() {
        return simulation;
    }
}
