package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.min;

import java.util.*;

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

//        Add medics first
        for (int i = 0; i <noMedics; i++) {
            agentList.add(new Medic(map, 2, 0, Integer.MAX_VALUE));
        }

        int remImmune = noImmune - noMedics;

//        Throw exception for invalid input
        if (!(noCivil + noAnimals == noHealthy + noIll + remImmune)) {
            throw new Exception("The number of health states doesn't equal population sizes - check arguments in createAgents() \n make sure there is enough space reserved for medics in noImmune");
        }

        int caAgents = noCivil + noAnimals;

//        Create array with length equal to remaining population of animals and civilians
        Integer[] agentStatus = new Integer[caAgents];

//        Add all health states to the array in their corresponding ranges
        Arrays.fill(agentStatus, 0 , noHealthy, 0);
        Arrays.fill(agentStatus, noHealthy , noHealthy+noIll, 1);
        Arrays.fill(agentStatus, noHealthy+noIll , noHealthy+noIll+remImmune, 2);

        Collections.shuffle(Arrays.asList(agentStatus));

//        Add remaining agents
        int j = 0;
        for (int i = 0; i<noAnimals; i++, j++) {
            System.out.println("Adding health status for animal: " + agentStatus[j]);
            agentList.add(new Animal(map, agentStatus[j], agentStatus[j]==1 ? 2:0, agentStatus[j]==2 ? 3:0));
        }
        for (int i = 0; i<noCivil; i++, j++) {
            System.out.println("Adding health status for civil: " + agentStatus[j]);
            agentList.add(new Civil(map, agentStatus[j], agentStatus[j]==1 ? 2:0, agentStatus[j]==2 ? 3:0));
        }

        return agentList;
    }
}
