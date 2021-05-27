package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.map.IMap;

import java.util.LinkedList;
import java.util.List;

public class AgentCreator implements IAgentCreator{
    protected int noHealthy;
    protected int noIll;
    protected int noImmune;
    protected int noPeople;
    protected int noAnimals;
    protected int peopleRatio;
    protected int animalRatio;

    public AgentCreator(int noHealthy, int noIll, int noImmune, int noPeople, int noAnimals, int peopleRatio, int animalRatio){
      this.noHealthy = noHealthy;
      this.noIll = noIll;
      this.noImmune = noImmune;
      this.noPeople = noPeople;
      this.noAnimals = noAnimals;
      this.peopleRatio = peopleRatio;
      this.animalRatio = animalRatio;
    }

    @Override
    public List<IAgent> createAgents(IMap map) {
        List <IAgent> agentList = new LinkedList<>();
//        Implement rest (filling of agentList) when agent classes are completed

        return agentList;
    }
}
