package agentSim.agent.animal;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Animal extends Agent {
    protected int speedRatio;
    protected double recoveryRatio;
    protected double contagious;
    protected int healthCondition;

    public Animal(IMap map) {

    }
    public void move() {

    }
    public void infecting(){

    }
    protected int[] getNeigbours(){

        return new int[0];
    }
}
