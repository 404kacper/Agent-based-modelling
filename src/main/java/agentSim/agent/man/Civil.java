package agentSim.agent.man;

import agentSim.map.IMap;

public class Civil extends Man{
    protected double contagious;
    protected int healthCondition;

    public void infecting(){

    }
    public Civil(IMap map) {
        super(map);
    }
}
