package agentSim.counter;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;

public class AgentCounter {
    private int illNo;
    private int immNo;
    private int healthyNo;
    private int civilNo;
    private int animalNo;
    private int medicNo;

    public int getIllNo() {
        return illNo;
    }

    public int getImmNo() {
        return immNo;
    }

    public int getHealthyNo() {
        return healthyNo;
    }

    public int getCivilNo() {
        return civilNo;
    }

    public int getAnimalNo() {
        return animalNo;
    }

    public int getMedicNo() {
        return medicNo;
    }

    public AgentCounter() {
        illNo = 0;
        immNo = 0;
        healthyNo = 0;
        civilNo = 0;
        animalNo = 0;
        medicNo = 0;
    }

    public void count (IAgent agent) {
        if(agent instanceof Animal) {
            animalNo++;
        } else if(agent instanceof Civil) {
            civilNo++;
        } else if(agent instanceof Medic) {
            medicNo++;
        }

        if(agent.getHealth() == 0) {
            healthyNo++;
        } else if(agent.getHealth() == 1) {
            illNo++;
        } else if(agent.getHealth() == 2) {
            immNo++;
        }
    }

    public void restCount () {
        animalNo=civilNo=medicNo=illNo=immNo=healthyNo=0;
    }
}
