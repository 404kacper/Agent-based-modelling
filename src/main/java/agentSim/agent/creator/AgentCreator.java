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

        int maxAgents = noMedics+noAnimals+noCivil;
        double sumTheRatio = noHealthy+noIll+noImmune;
        double animalPart = noAnimals/sumTheRatio;
        double ratioHealthyAnimal = noHealthy*animalPart;
        double ratioIllAnimal = noIll*animalPart;
        double ratioImmuneAnimal = noImmune*animalPart;

        double civilPart = noCivil/sumTheRatio;
        double ratioHealthyCivil = noHealthy*civilPart;
        double ratioIllCivil = noIll*civilPart;
        double ratioImmuneCivil = noImmune*civilPart;

        if (noHealthy+noIll+noImmune != noCivil+noAnimals+noMedics) {
            throw new Exception("Number of agents must be equal to the sum of their health states.");
        } else if (noImmune<noMedics) {
            throw new Exception("Number of medics must be greater or equal to number of immune agents");
        }

//        The only issue is when ratios of agent health states is 1:1:1
//        - for example for ratios like 18:18:18 and 26 civilians 28 animals will give 27 agents for both animals and civilians (+/- 1 from expected numbers)
//        - another example 7:7:7 and 1 civil and 20 animals will give 0 agents for civilians and 21 agents for animals (+/- 1 from expected numbers)
//        - another example 14:14:14 and 38 civilians and 4 animals will give 39 agents for civilians and 3 agents for animals (+/- 1 from expected numbers)

        System.out.println("Ratio number for animals:\n healthy - " + ratioHealthyAnimal + " ill - " + ratioIllAnimal + " immune - " + ratioImmuneAnimal);
        System.out.println("Rounded ratio number for animals:\n healthy - " + Math.round(ratioHealthyAnimal) + " ill - " + Math.round(ratioIllAnimal) + " immune - " + Math.round(ratioImmuneAnimal));
        System.out.println("Ratio number for civilians:\n healthy - " + ratioHealthyCivil + " ill - " + ratioIllCivil + " immune - " + ratioImmuneCivil);
        System.out.println("Rounded ratio number for civilians:\n healthy - " + Math.round(ratioHealthyCivil) + " ill - " + Math.round(ratioIllCivil) + " immune - " + Math.round(ratioImmuneCivil));

        return agentList;
    }
}
