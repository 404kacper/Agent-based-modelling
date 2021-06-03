package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.map.IMap;

import java.util.List;
import java.util.Random;

public interface IAgentCreator {
    public List <IAgent> createAgents(IMap map, Random random);
    public int count(int health, int illNum, int healthyNum, int resistantNum);
}
