package agentSim.agent.man;

import agentSim.agent.Agent;
import agentSim.agent.IAgent;
import agentSim.map.IMap;
import com.google.common.collect.Multimap;

public class Medic extends Agent {

    private int medicSpeed;
    private int resDurationAfterVaccination;
    private int vaccinateFov;

    public Medic(IMap map, int health, int infDuration, int resDuration, double deathProb) {
        super(map, health, infDuration, resDuration, deathProb);
        medicSpeed = 1;
        resDurationAfterVaccination = resDuration;
    }

//    Specific recover implementation for Medic agents
    @Override
    public boolean recover() {
//        Empty since medic agents shouldn't lose their resistance nor shouldn't get infected
        return false;
    }

//    Specific infect implementation for Medic agents
    @Override
    public void infect() throws Exception {
        throw new Exception(this+ "object of hash code: "+ this.hashCode() +" is unable to invoke infection method. \n Medic objects shouldn't be allowed to access infection method.");
    }

//    Specific move implementation for Medic agents
    @Override
    public void move(){
        move(medicSpeed);
    }

    public void vaccinate() {
        Multimap<IAgent, Integer> neighbours = this.getNeighbours(vaccinateFov);
        int row = 0;
        int col;
        int i = 0;
        IAgent toBeVaccinated;

//        Two coordinates are stored repetitively so just iterate over even and uneven values from neighbours
        for (Integer value : neighbours.values()) {
            //            When i is uneven both row and col for one agent are known
            if ( i % 2 != 0) {
                col = value;
                toBeVaccinated = map.getAgent(row, col);
//                    If target agent is healthy then vaccinate
                if (toBeVaccinated.getHealth() == 0) {
                    toBeVaccinated.setHealth(2);
//                        Set resistance duration for vaccinated agents
                    toBeVaccinated.setResistanceDuration(resDurationAfterVaccination);
                }
            } else {
                row = value;
            }
            i++;
        }
    }

    public void setVaccinateFov(int vaccinateFov) {
        this.vaccinateFov = vaccinateFov;
    }

    public void setMedicSpeed(int medicSpeed) {
        this.medicSpeed = medicSpeed;
    }

    public void setResDurationAfterVaccination(int resDurationAfterVaccination) {
        this.resDurationAfterVaccination = resDurationAfterVaccination;
    }


    @Override
    public String toString() {
        if (healthCondition == 2) {
            return TEXT_CYAN+"M "+TEXT_RESET;
        } else {
            return TEXT_YELLOW+"?M "+TEXT_RESET;
        }
    }

}
