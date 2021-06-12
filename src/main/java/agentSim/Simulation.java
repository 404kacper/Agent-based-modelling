package agentSim;

import agentSim.agent.IAgent;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import agentSim.map.creator.IMapCreator;
import agentSim.map.creator.MapCreator;
import gui.AppGui;

import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    private IMap map;
    private Random rnd;
    private int maxIter;
    private List<IAgent> agentList;

//    Implement IAgentCreator once agents are added to the project structure
    public Simulation(IMapCreator mapCreator, IAgentCreator agentCreator, long seed, int maxIter){
    map = mapCreator.createMap();

    rnd=new Random(seed);
//    Catch the exception defined in AgentCreator class
        try {
            agentList = agentCreator.createAgents(map, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

//    Initialize map with all of the agents in list
        for (IAgent agent : agentList)
            while (true) {
                if (map.placeAgent(agent, rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim()))) break;
            }

    this.maxIter=maxIter;
    }

    public void runSimulation() {
        int iterations = maxIter;

//        Print initial state of the map
        System.out.println("Iterations left: " + iterations);
        System.out.println(map.toString());

        while (--iterations>0) {
//            Recover agents and remove infected - dead agents
            Iterator <IAgent> listIterator = agentList.iterator();
            while (listIterator.hasNext()){
                IAgent agent = listIterator.next();
                double genProb = ThreadLocalRandom.current().nextDouble();
//                Initial iteration is omitted to allow agents to interact with each other
                if (iterations != maxIter-1) {
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
                    ((Medic) agent).vaccinate(1,2);
                }
            }
            for (IAgent agent : agentList) {
                agent.move();
            }
//            Print out map after each iteration
            System.out.println("\n");
            System.out.println("Iterations left: " + iterations);
            System.out.println(map.toString());
        }
    }

    public static void main(String[] args) {
        MapCreator currentMap = new MapCreator(4, 4);

        IAgentCreator currentAgents = new AgentCreator(1,1,1,1,1,1);

        Simulation sim = new Simulation(currentMap, currentAgents,54, 10);
        sim.runSimulation();

        AppGui newGui = new AppGui();
    }
}
