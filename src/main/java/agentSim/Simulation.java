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
//        Double type value between min - max both included --> Math.floor(Math.random() * (max - min + 1)) + min
        agentList = agentCreator.createAgents(map, (int)Math.floor(Math.random() * (2 - 1 + 1)) + 1);

    for (int i = 0; i< agentList.size();i++)
        //FIXME Current issue is with map.getSize();
        // - Make it so getSize() function returns x and y dimensions
        // - Either a getDimX() and getDimY() function or getSize() returning array and then extract values from the array
        // - Then uncomment below line, see if app compiles
        // - If it does continue working on AgentCreator so that it fills the map with Animal class objects (only animal objects for now)
        // - And make sure they're printed accordingly to their healthCondition, printing is done with toString function
//        while(!map.placeAgent(agentList.get(i), rnd.nextInt(map.getSize()), rnd.nextInt(map.getSize())));
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
