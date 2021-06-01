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

    for (int i = 0; i< agentList.size();i++)
        while(!map.placeAgent(agentList.get(i), rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim())));
    this.maxIter=maxIter;
    }

    public void runSimulation() {
        int iterations = maxIter;

        System.out.println("Iterations left: " + iterations);
        System.out.println(map.toString());

        while (--iterations>0) {
//            Reason for two loops is so that interactions between agents are mutual eg. agent1 can infect agent2 and vice-versa
            for (IAgent agent : agentList) {
//                Infect agents only for infected agents
                if (agent.getHealth() == 1) {
                    agent.infect(1, 2);
                }
            }
            for (IAgent agent : agentList) {
//                Move agents
                agent.move();
            }
            System.out.println("\n");
            System.out.println("Iterations left: " + iterations);
//            Print out map after each iteration
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

        MapCreator currentMap = new MapCreator(10, 10);
//        Meaningless numbers just to get the simulation running

        IAgentCreator currentAgents = new AgentCreator(1,1,1,1,20,1);

        Simulation sim = new Simulation(currentMap, currentAgents,1, 8);
        sim.runSimulation();
//        Possibly a class that sums up everything that happened during these iterations? eg. amount of infections, healthy etc...
    }

}
