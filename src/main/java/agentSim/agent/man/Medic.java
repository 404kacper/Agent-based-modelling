package agentSim.agent.man;

import agentSim.map.IMap;

public class Medic extends Man{
    protected double contagious;
    protected int healthCondition;

    protected int[] getNeigbours(){

        return new int[0];
    }
    public void vaccine(){

    }
    public Medic(IMap map) {
        super(map);
    }
}
