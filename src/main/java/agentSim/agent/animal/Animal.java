package agentSim.agent.animal;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Animal extends Agent {

    private int animalSpeed;
    private int infectFov;
    private int resistanceDurationAfterRecovery;
    private int infectionDurationAfterInfection;

    public Animal(IMap map, int health, int infDuration, int resDuration, double deathProb) {
        super(map, health, infDuration, resDuration, deathProb);
        this.animalSpeed = 1;
    }

//    Specific recover implementation for Animal agents
    @Override
    public boolean recover() {
        return recover(resistanceDurationAfterRecovery);
    }

//    Specific infection implementation for Animal agents
    @Override
    public void infect() {
        infect(infectFov,infectionDurationAfterInfection, 1);
    }
//    Specific move implementation for Animal agents
    @Override
    public void move(){
        move(animalSpeed);
    }

    public void setAnimalSpeed(int animalSpeed) {
        this.animalSpeed = animalSpeed;
    }

    public void setResistanceDurationAfterRecovery(int resistanceDurationAfterInfection) {
        this.resistanceDurationAfterRecovery = resistanceDurationAfterInfection;
    }

    public void setInfectionDurationAfterInfection(int infectionDurationAfterInfection) {
        this.infectionDurationAfterInfection = infectionDurationAfterInfection;
    }

    public void setInfectFov(int infectFov) {
        this.infectFov = infectFov;
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
