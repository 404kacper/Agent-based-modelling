package agentSim.agent;

import agentSim.map.IMap;

public abstract class AAgent implements IAgent {
    protected IMap map;
    static int objectCounter;


    public AAgent(IMap map) {
    this.map = map;
    objectCounter++;
    }
    @Override
    public void displayObjectCounter() {
        System.out.println(objectCounter);
    }
    @Override
    public IMap getMap() { return map;}
    @Override
    public void setMap(IMap map) {this.map=map;}
}
