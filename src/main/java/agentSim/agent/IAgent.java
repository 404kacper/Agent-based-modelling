package agentSim.agent;

import agentSim.map.IMap;

public interface IAgent {
    void move();
    int getHealth();
    void infect() throws Exception;
    void recover();
    void setResistanceDuration(int resistanceDuration);
    void setInfectionDuration(int duration);
    void setHealth(int healthStatus);
    void displayObjectCounter();
    IMap getMap();
    void setMap(IMap map);
}
