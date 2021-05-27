package agentSim.agent;

import agentSim.map.IMap;

public interface IAgent {
    public void move();
    public IMap getMap();
    public void setMap(IMap map);
    public boolean isIll(IAgent agent);
}
