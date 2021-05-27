package agentSim.agent;

import agentSim.map.IMap;

public abstract class AAgent {
    protected IMap map;

    public AAgent(IMap) {

    }
    public abstract IMap getMap();
    public abstract void setMap();
}
