package agentSim.map;

import agentSim.agent.IAgent;

import java.util.List;

public interface IMap {
    IAgent getAgent(int row, int col);
    int getXDim();
    int getYDim();
    boolean placeAgent(IAgent agent, int row, int col);
    void removeAgent(IAgent agent);
    void placeAgentInclusive(IAgent agent, int row, int col);
    int[] getAgentPosition(IAgent spec);
}
