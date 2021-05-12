package agentSim.map;

import agentSim.agent.IAgent;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class SimulationMap implements IMap {

    private IAgent[][] agents;
//    Documentation for this structure: https://github.com/google/guava/wiki & explanation: https://github.com/google/guava/wiki/NewCollectionTypesExplained
    private Multimap<String, Integer> agentsPositions;

    public SimulationMap(int width, int height) {
    agents = new IAgent[width][height];
    agentsPositions = ArrayListMultimap.create();

//    putAll(key, value1, value2)
    agentsPositions.putAll("Marek", Ints.asList(2,3));
    }


    @Override
    public IAgent getAgent(int position_x, int position_y) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean placeAgent(IAgent agent, int x_coord, int y_coord) {
        return false;
    }

    @Override
    public Integer[] getAgentPosition(String agent) {
//        Collections are usually returned from guava structures
        Collection <Integer> view = agentsPositions.get("Marek");
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
}