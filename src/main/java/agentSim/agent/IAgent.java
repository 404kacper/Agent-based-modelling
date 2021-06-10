package agentSim.agent;

import agentSim.map.IMap;
import com.google.common.collect.Multimap;

public interface IAgent {
    void move();
    int getHealth();
    void infect() throws Exception;
    boolean recover();
    void setResistanceDuration(int resistanceDuration);
    Multimap<IAgent, Integer> getNeighboursRing(int fieldOfView);
    public double getDeathProb();
    void setInfectionDuration(int duration);
    void setHealth(int healthStatus);
    void displayObjectCounter();
    IMap getMap();
    void setMap(IMap map);
}
