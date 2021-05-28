package agentSim.map;

import agentSim.agent.IAgent;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.Collection;



public class SimulationMap implements IMap {

    private IAgent[][] agents;
//    Documentation for this structure: https://github.com/google/guava/wiki & explanation: https://github.com/google/guava/wiki/NewCollectionTypesExplained
    private Multimap<IAgent, Integer> agentsPositions;

    public SimulationMap(int width, int height) {
    agents = new IAgent[width][height];
    agentsPositions = ArrayListMultimap.create();
    }


    @Override
    public IAgent getAgent(int x_coord, int y_coord) {
        return agents[x_coord][y_coord];
    }

    @Override
    public int getSize() {
        return agents.length;
    }

    @Override
    public boolean placeAgent(IAgent agent, int x_coord, int y_coord) {
//        pass agent object instead of String in end version of the app
        Integer[] coordinates = getAgentPosition(agent);
//        Check if the position is already occupied
        if (agents[x_coord][y_coord] != null) {
            return false;
        }
//        Check if coordinates are correct
        if (coordinates[0] >= 0 || coordinates[1] >= 0) {
            agents[coordinates[0]][coordinates[1]] = null;
        }
//        Uncomment this once agent class is implemented
//        agent.setMap(this);

//        Place agent
        agents[x_coord][y_coord] = agent;
        agentsPositions.putAll(agent, Ints.asList(x_coord,y_coord));
        return true;
    }

    @Override
    public Integer[] getAgentPosition(IAgent agent) {
//        Collections are usually returned from guava structures
        Collection <Integer> view = agentsPositions.get(agent);
//        Size of 2 for x and y coordinate
        Integer[] position = new Integer[2];
//        Keep track of iterations in for each loop
        int counter = 0;
//        Loop through collection
        for (Integer num : view) {
//            Assign coordinate to array that will be returned
            position[counter] = num;
            counter++;
        }
        return position;
    }

//    Prints out map
    @Override
    public String toString(){
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < agents.length; i++) {
            buff.append("\n");
            for(int j = 0; j < agents[i].length; j++) {
                if (agents[i][j] == null) {
                    buff.append("#");
                } else {
                    buff.append(agents[i][j].toString());
                }
            }
        }
        return buff.toString();
    }
}