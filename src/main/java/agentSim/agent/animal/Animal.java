package agentSim.agent.animal;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Animal extends Agent {

    private int animalSpeed;

    public Animal(IMap map, int health, int infDuration, int resDuration, double deathProb) {
        super(map, health, infDuration, resDuration, deathProb);
    }

//    Specific recover implementation for Animal agents
    @Override
    public boolean recover() {
        return recover(2);
    }

//    Specific infection implementation for Animal agents
    @Override
    public void infect() {
        infect(1,2, 1);
    }
//    Specific move implementation for Animal agents
    @Override
    public void move(){
        move(animalSpeed);
    }

    public void setAnimalSpeed(int animalSpeed) {
        this.animalSpeed = animalSpeed;
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
