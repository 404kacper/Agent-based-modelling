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
    agentList = agentCreator.createAgents(map);

    for (int i = 0; i<agentList.size();i++)
        while(!map.placeAgent(agentList.get(i), rnd.nextInt(map.getSize()), rnd.nextInt(map.getSize())));
    this.maxIter = maxIter;
    }

    public void runSimulation() {
        int iterations = maxIter;
        System.out.println(map.toString());
        while (--iterations>0) {
//            Agent list is empty so nothing prints out for now - the map is full of empty cells (#) / agents classes are to be implemented next
            for (IAgent agent : agentList) {
//                Move agents
                agent.move();
//                Print out map after each move
                System.out.println(map.toString());
            }
        }
    }

    public static void main(String[] args) {
        MapCreator currentMap = new MapCreator(5, 5);
//        Meaningless numbers just to get the simulation running
        IAgentCreator currentAgents = new AgentCreator(1,1,1,1,1,1,1);
//        Seed could be current time if needed
        Simulation sim = new Simulation(currentMap, currentAgents,1, 10);
        sim.runSimulation();
//        Possibly a class that sums up everything that happened during these iterations? eg. amount of infections, healthy etc...
    }

}
