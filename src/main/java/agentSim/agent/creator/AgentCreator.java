package agentSim.agent.creator;

import java.util.List;

public class AgentCreator implements IAgentCreator{
    @Override
    public List createAgents() {
        return null;
    }
    protected int noHealthy;
    protected int noIll;
    protected int noImmune;
    protected int noPeople;
    protected int noAnimals;
    protected int peopleRatio;
    protected int animalRatio;

    public KreatorAgentow(int noHealthy, int noIll, int noImmune, int noPeople, int noAnimals, int peopleRatio, int animalRatio){
      this.noHealthy = noHealthy;
      this.noIll = noIll;
      this.noImmune = noImmune;
      this.noPeople = noPeople;
      this.noAnimals = noAnimals;
      this.peopleRatio = peopleRatio;
      this.animalRatio = animalRatio;
    }
}
