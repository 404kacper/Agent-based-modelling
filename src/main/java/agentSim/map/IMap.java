package agentSim.map;

import agentSim.agent.IAgent;

public interface IMap {
    public IAgent getAgent(int x_coord, int y_coord);
    public int getSize();
    public boolean placeAgent(IAgent agent, int x_coord, int y_coord);
    public Integer[] getAgentPosition(IAgent spec);
}
