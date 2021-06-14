package gui;

import agentSim.Simulation;
import agentSim.agent.IAgent;
import agentSim.map.IMap;
import gui.viewModel.MapViewModel;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

    private Simulation sim;

    private MapViewModel mapViewModel;

    private LineChart<Number, Number> populationChart;

    private XYChart.Series<Number, Number> animalSeries;
    private XYChart.Series<Number, Number> civilSeries;
    private XYChart.Series<Number, Number> medicSeries;


    public MainView(MapViewModel mapViewModel , Simulation sim) {
        this.mapViewModel = mapViewModel;
        this.sim = sim;

        this.mapViewModel.listenToMap(this::onMapChanged);

        this.canvas = new Canvas(400,400);

        this.canvas.setOnMouseMoved(this::handleMoved);

        Toolbar toolbar = new Toolbar(sim, mapViewModel);

        constructNumericalChart();

        this.infoBar = new InfoBar();
        this.infoBar.setCursorPosition(0,0);
        this.infoBar.setCounterFormat(0,0, 0, 0, 0, 0);

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(toolbar, populationChart, this.canvas, spacer, infoBar);

        this.affine = new Affine();
        this.affine.appendScale(400/10f, 400/10f);
    }

    private void constructNumericalChart() {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Iteration");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Agent count");
        yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        populationChart = new LineChart<>(xAxis, yAxis);
        populationChart.setTitle("Agent populations");
        populationChart.setAnimated(false); // disable animations

        //defining a series to display data
        animalSeries = new XYChart.Series<>();
        animalSeries.setName("Animal count");

        civilSeries = new XYChart.Series<>();
        civilSeries.setName("Civil count");

        medicSeries = new XYChart.Series<>();
        medicSeries.setName("Medic count");

        // add series to chart
        populationChart.getData().add(animalSeries);
        populationChart.getData().add(civilSeries);
        populationChart.getData().add(medicSeries);
    }

    private void onMapChanged(IMap map) {
        draw(map);
        int animalCount = sim.getAgentCounter().getAnimalNo();
        int civilCount =  sim.getAgentCounter().getCivilNo();
        int medicCount = sim.getAgentCounter().getMedicNo();
        int illCount = sim.getAgentCounter().getIllNo();
        int immuneCount = sim.getAgentCounter().getImmNo();
        int healthyCount = sim.getAgentCounter().getHealthyNo();
        this.infoBar.setCounterFormat(animalCount,civilCount, medicCount, illCount, immuneCount, healthyCount);
        this.animalSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), animalCount));
        this.civilSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), civilCount));
        this.medicSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), medicCount));
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

    public void draw(IMap map) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        g.setStroke(Color.GRAY);
        this.drawSimulation(map);
        g.setLineWidth(0.05f);
        for (int x = 0; x <= map.getXDim(); x++) {
            g.strokeLine(x, 0,x,10);
        }

        for (int y = 0; y <= map.getYDim(); y++) {
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


    public Simulation getSim() {
        return sim;
    }
}
