package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
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
    public List<IAgent> createAgents(IMap map, int health) {
        List <IAgent> agentList = new LinkedList<>();

        for (int i=0; i<noAnimals; i++) {
            agentList.add(new Animal(map, health));
        }

        return agentList;
    }
}
