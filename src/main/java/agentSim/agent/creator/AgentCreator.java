package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.map.IMap;
import org.checkerframework.checker.units.qual.min;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AgentCreator implements IAgentCreator {
    protected int noHealthy;
    protected int noIll;
    protected int noImmune;
    protected int noCivil;
    protected double noAnimals;
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

//        int currNoAnimals = noAnimals;
        int currNoCivil = noCivil;
        int currNoMedics = noMedics;
        int currNoIll = 0;
        int currNoImmune = 0;
        int currNoHealthy = 0;
        int sumForIll = 0;
        int sumForImmune = 0;
        int sumForHealthy = 0;
       double halfNoAnimals = (noCivil+noAnimals)/6;
//        or
//        double halfNoAnimals = 1.0;
        System.out.println(halfNoAnimals);


        if (noHealthy+noIll+noImmune != noCivil+noAnimals+noMedics) {
            throw new Exception("Number of agents must be equal to the sum of their health states.");
        } else if (noImmune<noMedics) {
            throw new Exception("Number of medics must be greater or equal to number of immune agents");
        }

        while (sumForImmune <= noImmune) {
            if (sumForImmune < halfNoAnimals && currNoImmune <= noImmune) {
                agentList.add(new Animal(map, 2, 0, 2));
                sumForImmune++;
                currNoImmune++;
            }
            if (sumForImmune >= halfNoAnimals && currNoImmune <= noImmune) {
                agentList.add(new Civil(map, 2, 0, 2));
                sumForImmune++;
                currNoImmune++;
            }
        }

        while (sumForIll < noIll) {
            if (sumForIll < halfNoAnimals && currNoIll <= noIll) {
                agentList.add(new Animal(map, 1, 2, 0));
                sumForIll++;
                currNoIll++;
            }
            if (sumForIll >= halfNoAnimals && currNoIll <= noIll) {
                agentList.add(new Civil(map, 1, 2, 0));
                sumForIll++;
                currNoIll++;
            }
        }

        while (sumForHealthy < noHealthy) {
            if (sumForHealthy < halfNoAnimals && currNoHealthy <= noHealthy) {
                agentList.add(new Animal(map, 0, 0, 0));
                sumForHealthy++;
                currNoHealthy++;
            }
            if (sumForHealthy >= halfNoAnimals && currNoHealthy <= noHealthy) {
                agentList.add(new Civil(map, 0, 0, 0));
                sumForHealthy++;
                currNoHealthy++;
            }
        }

//        for (int i = 0; i<=currNoHealthy; i++) {
//            if (currNoAnimals>0 && currNoHealthy > 0) {
//                agentList.add(new Animal(map, 0, 0, 0));
//                currNoAnimals--;
//                currNoHealthy--;
//            }
////            if (currNoCivil>0 && currNoHealthy > 0) {
//////                agentList.add(new Civil(map, 0, 2, 0));
////                currNoCivil--;
////                currNoHealthy--;
////            }
//        }

        return agentList;
    }
}
