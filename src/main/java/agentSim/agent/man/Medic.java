package agentSim.agent.man;

import agentSim.agent.Agent;
import agentSim.agent.IAgent;
import agentSim.map.IMap;
import com.google.common.collect.Multimap;

public class Medic extends Agent {

    public Medic(IMap map, int health, int infDuration, int resDuration) {
        super(map, health, infDuration, resDuration);
    }
    public void move(){
        move(1);
    }
    public void vaccinate(int fieldOfView, int duration) {
        Multimap<IAgent, Integer> neighbours = this.getNeighbours(fieldOfView);
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
                    toBeVaccinated.setResistanceDuration(duration);
                }
            } else {
                row = value;
            }
            i++;
        }
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
