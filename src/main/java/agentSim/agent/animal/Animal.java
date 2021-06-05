package agentSim.agent.animal;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Animal extends Agent {
//    protected int speedRatio;
//    protected double recoveryRatio;
//    protected double contagious;

    public Animal(IMap map, int health, int infDuration, int resDuration) {
        super(map, health, infDuration, resDuration);
//        this.speedRatio = speed;
//        this.recoveryRatio = recovery;
//        this.contagious = contagious;
    }
    public void move(){
       move(2);
    }

    @Override
    public String toString() {
        return switch (healthCondition) {
            case 0 -> TEXT_GREEN+"A "+TEXT_RESET;
            case 1 -> TEXT_RED+"A "+TEXT_RESET;
            case 2 -> TEXT_BLUE+"A "+TEXT_RESET;
            default -> TEXT_YELLOW+"?A "+TEXT_RESET;
        };
    }
}
