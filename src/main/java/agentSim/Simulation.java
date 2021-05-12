package agentSim;

import agentSim.agent.IAgent;
import agentSim.map.IMap;
import agentSim.map.creator.IMapCreator;
import agentSim.map.creator.MapCreator;

import java.util.List;

public class Simulation {
    private IMap map;
    private int maxIter;
    private List<IAgent> agentList;

//    Implement IAgentCreator once agents are added to the project structure
    public Simulation(IMapCreator mapCreator, int maxIter){
    map = mapCreator.createMap();
    this.maxIter = maxIter;
    }

    public void runSimulation() {
        //empty for now
        return;
    }

    public static void main(String[] args) {
        MapCreator currentMap = new MapCreator(3, 3);
        Simulation sim = new Simulation(currentMap, 10);
//        Possibly a class that sums up everything that happend during these iterations? eg. amount of infections, healthy etc...
    }

}
