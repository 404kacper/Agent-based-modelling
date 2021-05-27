package agentSim.agent;

import agentSim.map.IMap;

public abstract class AAgent implements IAgent {
    protected IMap map;

    public AAgent(IMap map) {
    this.map = map;
    }
    @Override
    public IMap getMap() {
        return map;
    };
    @Override
    public void setMap(IMap map) {this.map=map;};
}
