package agentSim.agent;

import agentSim.agent.creator.AgentCreator;
import agentSim.agent.creator.IAgentCreator;
import agentSim.agent.man.Civil;
import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AgentTest {

    @Test
    @DisplayName("Should Move Agent")
    public void shouldMoveAgent() {
        IMap map;
        List<IAgent> agentList = new LinkedList<>();
        Random rnd = new Random();
        IAgent agentToBeMoved = null;
        int initialRow = 0;
        int initialCol = 0;
        int finalRow;
        int finalCol;


        MapCreator currentMap = new MapCreator(4, 4);

        map = currentMap.createMap();

//        Only one ill agent that will move
        IAgentCreator currentAgents = new AgentCreator(4,1,0,4,1,0);

//        Fill the agentList
        try {
            agentList = currentAgents.createAgents(map, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Fill the 2d array
        for (IAgent agent : agentList)
            while (true) {
                if (map.placeAgent(agent, rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim()))) break;
            }

        for (IAgent agent : agentList) {
            if (agent.getHealth() == 1) {
                agentToBeMoved = agent;
//                Values before move
                initialRow = map.getAgentPosition(agent)[0];
                initialCol = map.getAgentPosition(agent)[1];
            }
        }

//        Assert that agentToBeMoved isn't null at this point
        assert agentToBeMoved != null;
        agentToBeMoved.move();

//        Values after move
        finalRow = map.getAgentPosition(agentToBeMoved)[0];
        finalCol = map.getAgentPosition(agentToBeMoved)[1];


//        If the agent has decided to stay in place - keep moving until coordinates are different
        while (initialRow==finalRow && initialCol==finalCol) {
            agentToBeMoved.move();
            finalRow = map.getAgentPosition(agentToBeMoved)[0];
            finalCol = map.getAgentPosition(agentToBeMoved)[1];
        }

//        Arrays for assertions
        int [] initialCoords = {initialRow, initialCol};
        int [] finalCoords = {finalRow, finalCol};

//        Assertions
//        Check if the arrays are not equal
        Assertions.assertFalse(Arrays.equals(finalCoords, initialCoords));
    }

    @Test
    @DisplayName("Should Infect Agent")
    public void shouldInfectAgent() {
        IMap map;
        List<IAgent> agentList = new LinkedList<>();
        Random rnd = new Random();
        IAgent agentToBeInfected = null;
        int toBeInfectedHealthBefore = -1;
        int toBeInfectedHealthAfter = -1;

        MapCreator currentMap = new MapCreator(2, 1);

        map = currentMap.createMap();

//        Only two agents one that will get infected and one that will infect
        IAgentCreator currentAgents = new AgentCreator(1,1,0,2,0,0);

//        Fill the agentList
        try {
            agentList = currentAgents.createAgents(map, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Fill the 2d array
        for (IAgent agent : agentList)
            while (true) {
                if (map.placeAgent(agent, rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim()))) break;
            }


        for (IAgent agent : agentList) {
            if (agent.getHealth() == 1) {
                try {
//                    Get the neighbour that will be infected from multimap
                    for (IAgent key: agent.getNeighboursRing(1).keySet()) {
                        agentToBeInfected = key;
                    }
//                    Assert that agentToBeInfected isn't null at this point
                    assert agentToBeInfected != null;
                    toBeInfectedHealthBefore = agentToBeInfected.getHealth();
//                    Downcast to adjust the probability of infection
                    if (agent instanceof Civil) {
//                        The higher the agentDepProb the easier it is to infect - 2 guarantees infection in fov 1
                        ((Civil) agent).infect(1, 1, 2);
                    }
                    toBeInfectedHealthAfter = agentToBeInfected.getHealth();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//        Assertions
        Assertions.assertEquals(0, toBeInfectedHealthBefore);
        Assertions.assertEquals(1, toBeInfectedHealthAfter);
    }

    @Test
    @DisplayName("Should Get Neighbours In Ring Around Agent")
    public void shouldGetNeighboursRing() {
        IMap map;
        List<IAgent> agentList = new LinkedList<>();
//        Here seed is important since we will actually be checking for certain number of elements in neighbours Multimap
        Random rnd = new Random(0);
//        Initialize empty Multimap of neighbour values
        Multimap<IAgent, Integer> neighboursRing = ArrayListMultimap.create();

        MapCreator currentMap = new MapCreator(4, 4);

        map = currentMap.createMap();

//        Only one ill agent that will move
        IAgentCreator currentAgents = new AgentCreator(9,1,0,10,0,0);

//        Fill the agentList
        try {
            agentList = currentAgents.createAgents(map, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Fill the 2d array
        for (IAgent agent : agentList)
            while (true) {
                if (map.placeAgent(agent, rnd.nextInt(map.getXDim()), rnd.nextInt(map.getYDim()))) break;
            }

        for (IAgent agent : agentList) {
//            Check neighbours of the only infected agent
            if (agent.getHealth() == 1) {
                neighboursRing = agent.getNeighboursRing(2);
            }
        }

//        At this point multimap should be filled with neighbour values
        assert neighboursRing.keySet().size() != 0;

//        Assert if neighbours ring contains only 4 entries representing neighbours in fieldOfView 2
        Assertions.assertEquals(4, neighboursRing.keySet().size());
    }
}
