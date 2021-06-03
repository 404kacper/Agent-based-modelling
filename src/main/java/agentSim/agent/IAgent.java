package agentSim.agent;

import agentSim.map.IMap;
import com.google.common.collect.Multimap;

public interface IAgent {
    public void move(int speed);
    public void infect(int fieldOfView, int duration);
    public void recover();
    public Multimap<IAgent, Integer> getNeighbours(int fieldOfView);
    public int getHealth();
    public void setHealth(int healthStatus);
    public IMap getMap();
    public void setMap(IMap map);
    public void setInfectionDuration(int infectionDuration);
}
