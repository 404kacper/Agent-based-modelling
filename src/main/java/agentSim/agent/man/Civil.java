package agentSim.agent.man;

import agentSim.agent.Agent;
import agentSim.map.IMap;

public class Civil extends Agent {

    private int civilSpeed;
    private int infectFov;
    private int resistanceDurationAfterRecovery;
    private int infectionDurationAfterInfection;

    public Civil(IMap map, int health, int infDuration, int resDuration, double deathProb) {
        super(map, health, infDuration, resDuration, deathProb);
        civilSpeed = 1;
    }

    //    Specific recover implementation for Civil agents
    @Override
    public boolean recover() {
        return recover(resistanceDurationAfterRecovery);
    }

    //    Specific infection implementation for Civil agents
    @Override
    public void infect() {
        infect(infectFov, infectionDurationAfterInfection, 1);
    }

    //    Specific move implementation for Civil agents
    public void move() {
        move(civilSpeed);
    }

    public void setCivilSpeed(int civilSpeed) {
        this.civilSpeed = civilSpeed;
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
            case 0 -> TEXT_GREEN + "C " + TEXT_RESET;
            case 1 -> TEXT_RED + "C " + TEXT_RESET;
            case 2 -> TEXT_BLUE + "C " + TEXT_RESET;
            default -> TEXT_YELLOW + "?C " + TEXT_RESET;
        };
    }
}
