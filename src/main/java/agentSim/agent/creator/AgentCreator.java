package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.min;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AgentCreator implements IAgentCreator {
    protected int noHealthy;
    protected int noIll;
    protected int noImmune;
    protected int noCivil;
    protected int noAnimals;
    protected int noMedics;

    public AgentCreator(int noHealthy, int noIll, int noImmune, int noCivil, int noAnimals, int noMedics){
      this.noHealthy = noHealthy;
      this.noIll = noIll;
      this.noImmune = noImmune;
      this.noCivil = noCivil;
      this.noAnimals = noAnimals;
      this.noMedics = noMedics;
    }

    @Override
    public List<IAgent> createAgents(IMap map, Random random) throws Exception{
        List <IAgent> agentList = new LinkedList<>();
        int agentsAmount = 0;
        int healthyAgents = 0;
        int illAgents = 0;
        int immuneAgents = 0;
        int maxAgents = noMedics+noAnimals+noCivil;


        if (noHealthy+noIll+noImmune != noCivil+noAnimals+noMedics) {
            throw new Exception("Number of agents must be equal to the sum of their health states.");
        } else if (noImmune<noMedics) {
            throw new Exception("Number of medics must be greater or equal to number of immune agents");
        }

        while (agentsAmount < maxAgents) {
//            Add all possible healthy agents
            if (healthyAgents < noHealthy) {
                agentList.add(new Animal(map, 0, 0, 0));
                agentsAmount++;
                healthyAgents++;
                System.out.println(healthyAgents);
            }
            if (healthyAgents < noHealthy) {
                agentList.add(new Civil(map, 0, 0, 0));
                agentsAmount++;
                healthyAgents++;
                System.out.println(healthyAgents);
            }
//            Add all possible ill agents
            if (illAgents < noIll) {
                agentList.add(new Animal(map, 1, 2, 0));
                agentsAmount++;
                illAgents++;
            }
            if (illAgents < noIll) {
                agentList.add(new Civil(map, 1, 2, 0));
                agentsAmount++;
                illAgents++;
            }
            if (immuneAgents < noImmune) {
                agentList.add(new Animal(map, 2, 0, 2));
                agentsAmount++;
                immuneAgents++;
            }
            if (immuneAgents < noImmune) {
                agentList.add(new Civil(map, 2, 0, 2));
                agentsAmount++;
                immuneAgents++;
            }
        }


        return agentList;
    }
}
