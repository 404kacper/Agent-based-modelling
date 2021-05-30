package agentSim.agent;

import agentSim.map.IMap;

public interface IAgent {
    public void move();
    public void getNeighbours(int fieldOfView);
    public IMap getMap();
    public void setMap(IMap map);
}
