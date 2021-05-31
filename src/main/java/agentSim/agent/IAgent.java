package agentSim.agent;

import agentSim.map.IMap;
import com.google.common.collect.Multimap;

public interface IAgent {
    public void move();
    public Multimap<IAgent, Integer> getNeighbours(int fieldOfView);
    public void infect(int fieldOfView);
    public IMap getMap();
    public void setMap(IMap map);
    public void setHealth(int healthStatus);
    public int getHealth();
}
