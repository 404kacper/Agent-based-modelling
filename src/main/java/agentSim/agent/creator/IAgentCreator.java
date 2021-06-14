package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.customizer.SimulationCustomizer;
import agentSim.map.IMap;

import java.util.List;
import java.util.Random;

public interface IAgentCreator {
    List <IAgent> createAgents(IMap map, Random random) throws Exception;
}
