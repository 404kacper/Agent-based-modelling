package agentSim.agent.man;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Man extends Agent {
    protected int speedRatio;
    protected double recoveryRatio;
    protected double contagious;
    protected int healthCondition;

    public Man(IMap map) {

    }
    public void move() {

    }
    protected int[] getNeigbours(){

        return new int[0];
    }

}
