package agentSim.agent.man;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Medic extends Agent {
    protected double contagious;
    protected int healthCondition;

    protected int[] getNeigbours(){

        return new int[0];
    }
    public void vaccine(){

    }
    public Medic(IMap map, int health) {
        super(map, health);
    }
}
