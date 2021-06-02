package agentSim;

import agentSim.agent.IAgent;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.map.IMap;
import agentSim.map.creator.IMapCreator;
import agentSim.map.creator.MapCreator;

import java.util.List;
import java.util.Random;

public class Simulation {
    private IMap map;
    private Random rnd;
    private int maxIter;
    private List<IAgent> agentList;

//    Implement IAgentCreator once agents are added to the project structure
    public Simulation(IMapCreator mapCreator, IAgentCreator agentCreator, long seed, int maxIter){
    map = mapCreator.createMap();

    rnd=new Random(seed);
    agentList = agentCreator.createAgents(map, rnd);

//    Initialize map with all of the agents in list
    for (int i = 0; i< agentList.size();i++)
        while(!map.placeAgent(agentList.get(i), rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim())));

    this.maxIter=maxIter;
    }

    public void runSimulation() {
        int iterations = maxIter;

//        Print initial state of the map
        System.out.println("Iterations left: " + iterations);
        System.out.println(map.toString());

        while (--iterations>0) {
//            Reason for three loops is so that interactions between agents are mutual eg. agent1 can infect agent2 and vice-versa
//            Agents should move only after they were able to interact with each other
            for (IAgent agent : agentList) {
//                Recover agents that are both resistant and infected
                agent.recover();
            }
            for (IAgent agent : agentList) {
//                Execute infect agents only for infected agents
                if (agent.getHealth() == 1) {
//                    The infection duration can be different from the one in AgentCreator
                    agent.infect(1, 4);
                }
            }
            for (IAgent agent : agentList) {
                agent.move(1, agent);
            }
//            Print out map after each iteration
            System.out.println("\n");
            System.out.println("Iterations left: " + iterations);
            System.out.println(map.toString());
        }
    }

    public static void main(String[] args) {
        //FIXME bug for map size width: 4 height: 5 seed: 7 noAnimals: 3
        // - only 2 animals are printed out instead of 3 as declared in creator
        // - the bug persists probably for other seeds
        // - the issue is with non-square matrices
        // - most likely there is some logical issue in one of 2d array loops inside the app that prevents 3rd animal from being put onto the map
        // - therefore for now maps should only be square matrices
        // - keep in mind that above seed might not work anymore as app changes however the bug will persists in non square maps

        //TODO ideas:
        // - testing for:
        // - hashCodes of classes - making sure they stay unchanged during the course of simulation
        // - amount of agents - shouldn't change throughout the animation

        MapCreator currentMap = new MapCreator(5, 5);
//        Meaningless numbers just to get the simulation running

        IAgentCreator currentAgents = new AgentCreator(1,1,1,1,10,1);

        Simulation sim = new Simulation(currentMap, currentAgents,6, 10);
        sim.runSimulation();
//        Possibly a class that sums up everything that happened during these iterations? eg. amount of infections, healthy etc...
    }

}
