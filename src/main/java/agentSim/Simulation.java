package agentSim;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.creator.IAgentCreator;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.counter.AgentCounter;
import agentSim.customizer.SimulationCustomizer;
import agentSim.map.IMap;
import agentSim.map.creator.IMapCreator;

import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    private IMap map;
    private Random rnd;
    private int maxIter;
    private int currentIteration;
    private AgentCounter agentCounter;
    private SimulationCustomizer simulationCustomizer;
    private List<IAgent> agentList;

    //    Implement IAgentCreator once agents are added to the project structure
    public Simulation(IMapCreator mapCreator, IAgentCreator agentCreator, SimulationCustomizer simCust, long seed, int maxIter) {
        map = mapCreator.createMap();
        rnd = new Random(seed);
        simulationCustomizer = simCust;
        initializeSimulation(agentCreator, map, rnd);
        agentCounter = new AgentCounter();
        this.currentIteration = 0;
        this.maxIter = maxIter;
    }

    public void initializeSimulation(IAgentCreator agentCreator, IMap map, Random rnd) {
        //    Catch the exception defined in AgentCreator class
        try {
            agentList = agentCreator.createAgents(map, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //    Initialize map with all of the agents in list
        for (IAgent agent : agentList) {
            while (true) {
                if (map.placeAgent(agent, rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim()))) break;
            }
            setAgentsMoveDistance(agent, simulationCustomizer.getCivilSpeed(), simulationCustomizer.getMedicSpeed(), simulationCustomizer.getAnimalSpeed());
        }
    }

    public void runSimulationStep() {
        if (currentIteration < this.maxIter) {
            agentCounter.restCount();
            recoverAgents(currentIteration);
            interact();
            moveAgents();
            countAgents();
            ++currentIteration;
        }
    }

    public void countAgents() {
        for (IAgent agent : agentList) {
            agentCounter.count(agent);
        }
    }

    public void recoverAgents(int iterations) {
        //            Recover agents and remove infected - dead agents
        Iterator<IAgent> listIterator = agentList.iterator();
        while (listIterator.hasNext()) {
            IAgent agent = listIterator.next();
            double genProb = ThreadLocalRandom.current().nextDouble();
//                Initial iteration is omitted to allow agents to interact with each other
            if (iterations != 0) {
                if (genProb <= agent.getDeathProb()) {
                    if (agent.recover()) {
                        map.removeAgent(agent);
                        listIterator.remove();
                    }
                } else {
                    agent.recover();
                }
            }
        }
    }

    public void interact() {
        for (IAgent agent : agentList) {
//                Execute infect only for infected agents
            if (agent.getHealth() == 1) {
//                    Catch the exception defined in medic class
                try {
                    agent.infect();
                    Collections.sort(agentList, new SortByHealth());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
//                Execute vaccinate only for medics
            if (agent instanceof Medic) {
                ((Medic) agent).vaccinate();
            }
        }
    }

    public void moveAgents() {
        for (IAgent agent : agentList) {
            agent.move();
        }
    }

    public void setAgentsMoveDistance(IAgent agent, int civilSpeed, int medicSpeed, int animalSpeed) {
        if (agent instanceof Civil) {
            ((Civil) agent).setCivilSpeed(civilSpeed);
        }
        if (agent instanceof Medic) {
            ((Medic) agent).setMedicSpeed(medicSpeed);
        }
        if (agent instanceof Animal) {
            ((Animal) agent).setAnimalSpeed(animalSpeed);
        }
    }

    public IMap getSimulationMap() {
        return map;
    }

    public AgentCounter getAgentCounter() {
        return agentCounter;
    }

    public int getCurrentIteration() {
        return currentIteration;
    }
}
