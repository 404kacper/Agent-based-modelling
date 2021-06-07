package agentSim;

import agentSim.agent.IAgent;
import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import agentSim.map.creator.IMapCreator;
import agentSim.map.creator.MapCreator;

import java.util.*;

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
//            Reason for three loops is so that interactions between agents are mutual eg. agent1 can infect agent2 and vice-versa
//            Agents should move only after they were able to interact with each other
            for (IAgent agent : agentList) {
//                Recover agents that are both resistant and infected
                agent.recover();
            }
            for (IAgent agent : agentList) {
//                Execute infect only for infected agents
                if (agent.getHealth() == 1) {
//                    Catch the exception defined in medic class
                    try {
                        agent.infect();
//                        List must be sorted after each infection to provide proper object fetching for further iterations
//                        If the list isn't sorted infection won't function properly - it will spread more than it should
//                        Keep in mind that agents are fetched differently from agentList than they are from [][]agents 2d array
                        Collections.sort(agentList, new SortByHealth());
//                        System.out.println("AgentsList order after infection: " + agentList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
//                Execute vaccinate only for medics
                if (agent instanceof Medic) {
//                    The infection duration can be different from the one in AgentCreator
//                    Down-casting object although in a safe way since instanceof check is done
//                    Vaccinate could also be empty function in agent class although that would be confusing
//                    Since vaccinate isn't common for all agents, going further infection could be removed from agent
                    ((Medic) agent).vaccinate(1,2);
                }
            }
            for (IAgent agent : agentList) {
//                agent.move();
            }
//            Print out map after each iteration
            System.out.println("\n");
            System.out.println("Iterations left: " + iterations);
            System.out.println(map.toString());
        }
    }

    public static void main(String[] args) {
        //TODO ideas:
        // A new way of printing is necessary:
        // - either restructure the app to be a window app in JavaFX
        // - or rewrite the printing in runSimulation to be reflective of every change that happens during an iteration
        // - eg. agent is infected - update the map or agent moves - update the map
        // - one more thing for printing is to keep track of how many agents for each of the class are on the map during given iteration
        // Testing for:
        // - hashCodes of classes - making sure they stay unchanged during the course of simulation
        // - amount of agents - shouldn't change throughout the animation
        // - ...
        // Change to infection method:
        // - add death after infectionIteration hits 0
        // Changes to error checking:
        // - check for situation when map size is smaller than the amount of agents (infinite loop in Simulation)

        //TODO app logic:
        // Possibly a logical flaw since infections are invoked before vaccinations:
        // - changing healthCondition to be 1 - for immune and 2 - for ill would invert the situation
        // - however that flaw is up to debate as of now and might not even be considered flaw

        MapCreator currentMap = new MapCreator(6, 6);

        IAgentCreator currentAgents = new AgentCreator(32,1,3,16,17,3);

        Simulation sim = new Simulation(currentMap, currentAgents,7, 10);
        sim.runSimulation();
//        Possibly a class that sums up everything that happened during these iterations? eg. amount of infections, healthy etc...
    }

}
