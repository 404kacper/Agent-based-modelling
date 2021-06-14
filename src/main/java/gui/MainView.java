package gui;

import agentSim.Simulation;
import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import gui.controlers.Data;
import gui.viewModel.MapViewModel;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;


public class MainView extends VBox {
    private InfoBar infoBar;
    private Canvas canvas;

    private Affine affine;

    private Simulation sim;

    private MapViewModel mapViewModel;

    private LineChart<Number, Number> populationChart;
    private LineChart<Number, Number> stateChart;

    private XYChart.Series<Number, Number> animalSeries;
    private XYChart.Series<Number, Number> civilSeries;
    private XYChart.Series<Number, Number> medicSeries;

    private XYChart.Series<Number, Number> healthySeries;
    private XYChart.Series<Number, Number> illSeries;
    private XYChart.Series<Number, Number> immuneSeries;


    public MainView(MapViewModel mapViewModel , Simulation sim) {
        this.mapViewModel = mapViewModel;
        this.sim = sim;

        this.mapViewModel.listenToMap(this::onMapChanged);

        HBox chartBox = new HBox(3);

        HBox canvasBox = new HBox(100);

        HBox toolBarBox = new HBox(1);


        this.canvas = new Canvas(400,400);

        this.canvas.setOnMouseMoved(this::handleMoved);

        Toolbar toolbar = new Toolbar(sim, mapViewModel);

        constructPopulationChart();
        constructStateChart();

        this.infoBar = new InfoBar();
        this.infoBar.setCursorPosition(0,0);
        // Initial values to display in agents counter
        this.infoBar.setCounterFormat(0,0, 0, 0, 0, 0);

        this.setSpacing(50);
        chartBox.getChildren().addAll(populationChart, stateChart);
        canvasBox.setAlignment(Pos.CENTER);
        canvasBox.getChildren().addAll(canvas);
        toolBarBox.setAlignment(Pos.CENTER);
        toolBarBox.getChildren().addAll(toolbar);

        this.getChildren().addAll(toolBarBox, canvasBox ,chartBox, infoBar);

        this.affine = new Affine();
        this.affine.appendScale(400/ (double)Data.mapWidth, 400/(double)Data.mapHeight);
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
        animalSeries.getNode().setStyle("-fx-stroke: blue");
        this.civilSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), civilCount));
        this.medicSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), medicCount));

        this.healthySeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), healthyCount));
        this.illSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), illCount));
        this.immuneSeries.getData().add(new XYChart.Data<>(sim.getCurrentIteration(), immuneCount));
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
            g.strokeLine(x, 0,x,Data.mapWidth);
        }

        for (int y = 0; y <= map.getYDim(); y++) {
            g.strokeLine(0,y, Data.mapHeight,y);
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
                    if (currAgent instanceof Animal) {
                        g.fillOval(y,x,1,1);
                    } else if (currAgent instanceof Civil) {
                        g.fillArc(y, x, 1, 1, 45, 240, ArcType.OPEN);
                    }  else if (currAgent instanceof Medic) {
                        g.fillRoundRect(y,x,1,1,0.5,0.5);
                    }
                }
            }
        }

    }

    private void constructPopulationChart() {
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


    private void constructStateChart() {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Iteration");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("State count");
        yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        stateChart = new LineChart<>(xAxis, yAxis);
        stateChart.setTitle("State populations");
        stateChart.setAnimated(false); // disable animations

        //defining a series to display data
        healthySeries = new XYChart.Series<>();
        healthySeries.setName("Healthy count");

        illSeries = new XYChart.Series<>();
        illSeries.setName("Ill count");

        immuneSeries = new XYChart.Series<>();
        immuneSeries.setName("Immune count");

        // add series to chart
        stateChart.getData().add(healthySeries);
        stateChart.getData().add(illSeries);
        stateChart.getData().add(immuneSeries);
    }
}
