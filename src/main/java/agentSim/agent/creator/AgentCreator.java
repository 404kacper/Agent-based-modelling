package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.map.IMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AgentCreator implements IAgentCreator{
    protected int noHealthy;
    protected int noIll;
    protected int noImmune;
    protected int noPeople;
    protected int noAnimals;
    protected int peopleRatio;

    public AgentCreator(int noHealthy, int noIll, int noImmune, int noPeople, int noAnimals, int peopleRatio){
      this.noHealthy = noHealthy;
      this.noIll = noIll;
      this.noImmune = noImmune;
      this.noPeople = noPeople;
      this.noAnimals = noAnimals;
      this.peopleRatio = peopleRatio;
    }

    @Override
    public List<IAgent> createAgents(IMap map, Random random) {
        List <IAgent> agentList = new LinkedList<>();

        //TODO further changes to createAgents()
        // - Implement logic for the remaining variables that is : noHealthy, noIll, noImmune, noPeople, peopleRatio

        for (int i=0; i<noAnimals; i++) {
            agentList.add(new Animal(map, random.nextInt(3), 2, 3));
        }

        return agentList;
    }
}
