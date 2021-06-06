package agentSim.agent;

import agentSim.map.IMap;

public interface IAgent {
    void move();
    int getHealth();
    void infect(int fieldOfView, int duration);
    void recover();
    void setResistanceDuration(int resistanceDuration);
    void setInfectionDuration(int duration);
    void setHealth(int healthStatus);
    IMap getMap();
    void setMap(IMap map);
}
