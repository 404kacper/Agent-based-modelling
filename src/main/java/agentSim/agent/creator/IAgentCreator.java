package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.map.IMap;

import java.util.List;

public interface IAgentCreator {
    public List <IAgent> createAgents(IMap map, int health);
}
