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
    public IAgent getAgent(int row, int col) {
        return agents[row][col];
    }

    @Override
    public int getXDim() {
        return agents.length;
    }

    @Override
    public int getYDim() {
        return agents[0].length;
    }


// This method is meant to be used only during initial placement of agents
    @Override
    public boolean placeAgent(IAgent agent, int row, int col) {

//        Check if the position is already occupied
        if (agents[row][col] != null) {
            return false;
        }

        agent.setMap(this);

//        Place agent
        agents[row][col] = agent;
//        Remove all values before putting them - just in case
        agentsPositions.removeAll(agent);
        agentsPositions.putAll(agent, Ints.asList(row,col));
        return true;
    }

    @Override
    public void placeAgentInclusive(IAgent agent, int row, int col) {
        int[] coordinates = getAgentPosition(agent);
//        Remove the previous position from agents array
        if (coordinates[0] >= 0 || coordinates[1] >= 0) {
            agents[coordinates[0]][coordinates[1]] = null;
        }
        agent.setMap(this);
//        Place agent
        agents[row][col] = agent;
//        Replace all values for given agent during move
        agentsPositions.replaceValues(agent, Ints.asList(row,col));

    }

    @Override
    public int[] getAgentPosition(IAgent agent) {
//        Collections are usually returned from guava structures
        Collection <Integer> view = agentsPositions.get(agent);
//        Size of 2 for x and y coordinate
        int[] position = new int[2];
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
            if (i != 0) {
                buff.append("\n");
            }
            for(int j = 0; j < agents[i].length; j++) {
                if (agents[i][j] == null) {
                    buff.append("# ");
                } else {
                    buff.append(agents[i][j].toString());
                }
            }
        }
        return buff.toString();
    }
}