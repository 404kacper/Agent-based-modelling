package agentSim.agent.man;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Civil extends Agent {
    protected double contagious;
    protected int healthCondition;

    public void infecting(){

    }
    public Civil(IMap map, int health, int infDuration, int resDuration) {
        super(map, health, infDuration, resDuration);
    }
}
