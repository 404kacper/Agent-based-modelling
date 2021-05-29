package agentSim.agent.animal;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Animal extends Agent {
//    protected int speedRatio;
//    protected double recoveryRatio;
//    protected double contagious;
    protected int healthCondition;

    public Animal(IMap map, int health) {
        super(map);
//        this.speedRatio = speed;
//        this.recoveryRatio = recovery;
//        this.contagious = contagious;
        this.healthCondition = health;
    }
    public void move() {

    }
    public void infecting(){

    }
    protected int[] getNeighbours(){

        return new int[0];
    }


    @Override
    public String toString() {
        System.out.println("executed");
        return healthCondition == 2 ? "A" : "a";
    }
}
