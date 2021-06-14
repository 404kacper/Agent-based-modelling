package agentSim.agent.creator;

import agentSim.agent.IAgent;
import agentSim.agent.animal.Animal;
import agentSim.agent.man.Civil;
import agentSim.agent.man.Medic;
import agentSim.customizer.SimulationCustomizer;
import agentSim.map.IMap;

import java.util.*;

public class AgentCreator implements IAgentCreator {
    protected int noHealthy;
    protected int noIll;
    protected int noImmune;
    protected int noCivil;
    protected int noAnimals;
    protected int noMedics;
    protected SimulationCustomizer simCustomizer;

    public AgentCreator(int noHealthy, int noIll, int noImmune, int noCivil, int noAnimals, int noMedics) {
      this.noHealthy = noHealthy;
      this.noIll = noIll;
      this.noImmune = noImmune;
      this.noCivil = noCivil;
      this.noAnimals = noAnimals;
      this.noMedics = noMedics;
    }

//    Constructor specifically for gui and it's customization
    public AgentCreator(int noHealthy, int noIll, int noImmune, int noCivil, int noAnimals, int noMedics, SimulationCustomizer simCust) {
        this.noHealthy = noHealthy;
        this.noIll = noIll;
        this.noImmune = noImmune;
        this.noCivil = noCivil;
        this.noAnimals = noAnimals;
        this.noMedics = noMedics;
        this.simCustomizer = simCust;
    }

    @Override
    public List<IAgent> createAgents(IMap map, Random random) throws Exception{
        List <IAgent> agentList = new LinkedList<>();

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

//        Condition for the first constructor
        if (simCustomizer == null) {
            int j = 0;
            for (int i = 0; i<noAnimals; i++, j++) {
                agentList.add(new Animal(map, agentStatus[j], agentStatus[j]==1 ? 3:0, agentStatus[j]==2 ? 3:0, 0.5));
            }
            for (int i = 0; i<noCivil; i++, j++) {
                agentList.add(new Civil(map, agentStatus[j], agentStatus[j]==1 ? 3:0, agentStatus[j]==2 ? 3:0, 0.5));
            }

            for (int i = 0; i <noMedics; i++) {
                agentList.add(new Medic(map, 2, 0, Integer.MAX_VALUE, 0));
            }
        } else {
//            Condition for the second constructor
            int resDuration = simCustomizer.getResistanceDuration();
            int infDuration = simCustomizer.getInfectionDuration();
            int vaccinationFov = simCustomizer.getVaccinateFov();
            int infectFov = simCustomizer.getInfectionFov();

            int j = 0;
            for (int i = 0; i<noAnimals; i++, j++) {
                IAgent animal = new Animal(map, agentStatus[j], agentStatus[j]==1 ? infDuration:0, agentStatus[j]==2 ? resDuration:0, 0.5);
                setAgentsInteractionDurations(animal, resDuration, infDuration);
                setFieldsOfView(animal, infectFov);
                agentList.add(animal);
            }
            for (int i = 0; i<noCivil; i++, j++) {
                IAgent civil = new Civil(map, agentStatus[j], agentStatus[j]==1 ? infDuration:0, agentStatus[j]==2 ? resDuration:0, 0.5);
                setAgentsInteractionDurations(civil, resDuration, infDuration);
                setFieldsOfView(civil, infectFov);
                agentList.add(civil);
            }

            for (int i = 0; i <noMedics; i++) {
                IAgent medic = new Medic(map, 2, 0, Integer.MAX_VALUE, 0);
                setAgentsInteractionDurations(medic, resDuration, infDuration);
                setFieldsOfView(medic, vaccinationFov);
                agentList.add(medic);
            }

        }

        return agentList;
    }

    public void setAgentsInteractionDurations(IAgent agent, int resDur, int infDur) {
        if (agent instanceof Civil) {
            ((Civil) agent).setInfectionDurationAfterInfection(infDur);
            ((Civil) agent).setResistanceDurationAfterRecovery(resDur);
        }
        if (agent instanceof Animal) {
            ((Animal) agent).setInfectionDurationAfterInfection(infDur);
            ((Animal) agent).setResistanceDurationAfterRecovery(resDur);
        }
        if (agent instanceof  Medic) {
            ((Medic) agent).setResDurationAfterVaccination(resDur);
        }
    }
    public void setFieldsOfView(IAgent agent, int fov) {
        if (agent instanceof  Medic) {
            ((Medic) agent).setVaccinateFov(fov);
        }
        if (agent instanceof  Civil) {
            ((Civil) agent).setInfectFov(fov);
        }
        if (agent instanceof  Animal) {
            ((Animal) agent).setInfectFov(fov);
        }
    }

}
