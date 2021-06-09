package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import agentSim.map.creator.MapCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AgentCreatorTest {
    @Test
    @DisplayName("Should Create Agent")
    public void shouldCreateAgent() {
//        Variables necessary to create agent list
        IAgentCreator currentAgentsCreator = new AgentCreator(1,0,0,0,1,0);
        MapCreator currentMapCreator = new MapCreator(4, 4);
        IMap currentMap = currentMapCreator.createMap();
        Random rnd = new Random();

        List<IAgent> agentList = new LinkedList<>();
        try {
            agentList = currentAgentsCreator.createAgents(currentMap, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Assertions.
        Assertions.assertEquals(1, agentList.size());
    }

    @Test
    @DisplayName("Should Create Three Agents With Distinct Health States")
    public void shouldCreateThreeAgents() {
//        Variables necessary to create agent list
        IAgentCreator currentAgentsCreator = new AgentCreator(1,1,1,1,1,1);
        MapCreator currentMapCreator = new MapCreator(4, 4);
        IMap currentMap = currentMapCreator.createMap();
        Random rnd = new Random();

        List<IAgent> agentList = new LinkedList<>();
        try {
            agentList = currentAgentsCreator.createAgents(currentMap, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Assertions.
//        Healthy animal
        Assertions.assertEquals(Animal.class, agentList.get(0).getClass());
        Assertions.assertEquals(0, agentList.get(0).getHealth());

//        Ill civil
        Assertions.assertEquals(Civil.class, agentList.get(1).getClass());
        Assertions.assertEquals(1, agentList.get(1).getHealth());

//        Immune medic
        Assertions.assertEquals(Medic.class, agentList.get(2).getClass());
        Assertions.assertEquals(2, agentList.get(2).getHealth());
    }

    @Test
    @DisplayName("Should Create Ill and Immune Agents Without Medics")
    public void shouldCreateTwoAgents() {
//        Variables necessary to create agent list
        IAgentCreator currentAgentsCreator = new AgentCreator(0,1,1,1,1,0);
        MapCreator currentMapCreator = new MapCreator(4, 4);
        IMap currentMap = currentMapCreator.createMap();
        Random rnd = new Random();

        List<IAgent> agentList = new LinkedList<>();
        try {
            agentList = currentAgentsCreator.createAgents(currentMap, rnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Assertions.
//        Ill animal
        Assertions.assertEquals(Animal.class, agentList.get(0).getClass());
        Assertions.assertEquals(1, agentList.get(0).getHealth());

//        Immune civil
        Assertions.assertEquals(Civil.class, agentList.get(1).getClass());
        Assertions.assertEquals(2, agentList.get(1).getHealth());
    }

    @Test
    @DisplayName("Should Throw Exception")
    public void shouldThrowException() {
//        Variables necessary to create agent list
        IAgentCreator currentAgentsCreator = new AgentCreator(0,0,0,0,1,0);
        MapCreator currentMapCreator = new MapCreator(4, 4);
        IMap currentMap = currentMapCreator.createMap();
        Random rnd = new Random();

//        Assertions.
        Assertions.assertThrows(Exception.class, () -> {
            currentAgentsCreator.createAgents(currentMap, rnd);
        });
    }
}
