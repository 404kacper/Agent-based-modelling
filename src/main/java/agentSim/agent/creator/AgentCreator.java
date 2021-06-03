package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Medic;
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
    protected int noMedics;

    public AgentCreator(int noHealthy, int noIll, int noImmune, int noPeople, int noAnimals, int noMedics){
      this.noHealthy = noHealthy;
      this.noIll = noIll;
      this.noImmune = noImmune;
      this.noPeople = noPeople;
      this.noAnimals = noAnimals;
      this.noMedics = noMedics;
    }

    @Override
    public List<IAgent> createAgents(IMap map, Random random) {
        List <IAgent> agentList = new LinkedList<>();

        int currentHealth = 0;

        //TODO further changes to createAgents()
        // - Implement logic for the remaining variables that is : noHealthy, noIll, noImmune, noPeople, peopleRatio

        for (int i=0; i<noAnimals; i++) {
            IAgent animal = new Animal(map, random.nextInt(3), 2, 3);
            currentHealth = animal.getHealth();
            count(currentHealth, noIll, noHealthy, noImmune);
            agentList.add(animal);
        }
        for (int i=0; i<noMedics; i++) {
//            Infinite duration for resistance so it never runs out for him
            agentList.add(new Medic(map, 2, 0, Integer.MAX_VALUE));
        }

        return agentList;
    }

    @Override
    public int count(int health, int illNum, int healthyNum, int immuneNum) {
        switch (health) {
            case 0:
                return --healthyNum;
            case 1:
                return --illNum;
            case 2:
                return --immuneNum;
            default:
                return 0;
        }
    }
}
