package agentSim.agent;

import agentSim.IColors;
import agentSim.map.IMap;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.primitives.Ints;
import java.util.Random;

import static com.google.common.primitives.Ints.max;
import static com.google.common.primitives.Ints.min;

public class Agent extends AAgent implements IAgent, IColors {

    protected Random rnd;
    protected long seed;
    protected int healthCondition;
    protected int infectionDuration;
    protected int resistanceDuration;


    public Agent(IMap map, int health, int infDuration, int resDuration) {
        super(map);
//        !!! Current seed isn't the same as the one being passed in Simulation class
        this.seed = 0;
        rnd = new Random(seed);
        this.healthCondition = health;
//        If generated agent is ill then set duration for infection, if it isn't then keep it at 0
        this.infectionDuration = (healthCondition == 1) ? infDuration : 0 ;
//        Same as above but for resistant agents
        this.resistanceDuration = (healthCondition == 2) ? resDuration : 0 ;
    }

    @Override
    public void move() {
        do {
            int position_x = rnd.nextInt(map.getXDim());
            int position_y = rnd.nextInt(map.getYDim());
            if (map.getAgent(position_x, position_y) == null) {
                map.placeAgent(this, position_x, position_y);
                break;
            }
        } while(true);
    }

    @Override
    public void infect(int fieldOfView, int duration) {
        Multimap<IAgent, Integer> neighbours = this.getNeighbours(fieldOfView);
        int row = 0;
        int col;
        int i = 0;
        int amount = 0;
        IAgent toBeInfected;

//        Two coordinates are stored repetitively so just iterate over even and uneven values from neighbours
        for (Integer value : neighbours.values()) {
    //            When i is uneven both row and col for one agent are known
                if ( i % 2 != 0) {
                    col = value;
                    toBeInfected = map.getAgent(row, col);
//                    If target agent is healthy then infect
                    if (toBeInfected.getHealth() == 0) {
                        toBeInfected.setHealth(1);
//                        Set infection duration for infected agents
                        toBeInfected.setInfectionDuration(duration);
                        amount++;
                    }
                } else {
                    row = value;
                }
                i++;
            }
//        Print how many agents were infected
        if (amount > 0) {
            System.out.println("Infected : " + amount + " agent/s");
        }
    }

    @Override
    public void recover() {
//        Part for recovering from infection
        if (infectionDuration > 0 ) {
            infectionDuration--;
//            Once infection duration is 0 then set the agent to be healthy
//            healthCondition == 1 is there to ensure only ill agents can recover
            if (infectionDuration == 0 && healthCondition == 1) {
                healthCondition = 0;
            }
//            Prevents infectionDuration from having negative values (just in case)
        } else if (infectionDuration < 0) {
            infectionDuration = 0;
        }
//        Part for losing resistance
        if (resistanceDuration > 0 ) {
            resistanceDuration--;
//            Once resistance duration is 0 then set the agent to be healthy
//            healthCondition == 2 is there to ensure only resistant agents can recover
            if (resistanceDuration == 0 && healthCondition == 2) {
                healthCondition = 0;
            }
        } else if (resistanceDuration < 0) {
            resistanceDuration = 0;
        }
    }

    public Multimap<IAgent, Integer> getNeighbours(int fieldOfView) {
        int currR = map.getAgentPosition(this)[0];
        int currC = map.getAgentPosition(this)[1];
        int rowLimit = map.getXDim()-1;
        int colLimit = map.getYDim()-1;

//        Multimap to store agent and their neighbour locations
        Multimap<IAgent, Integer> positions = ArrayListMultimap.create();

        for (int x = max(0, currR-fieldOfView); x <= Math.min(currR+fieldOfView, rowLimit); x++) {
            for (int y = max(0, currC-fieldOfView); y <= min(currC+fieldOfView, colLimit); y++) {
//                    Check if the x and y aren't coordinates of the agent
                if (x != currR || y != currC) {
//                            Check if neighbour is not empty
                    if (map.getAgent(x,y) != null) {
//                                Store positions in multiMap
                        positions.putAll(this, Ints.asList(x,y));
                    }
                }
            }
        }
        return positions;
    }

    @Override
    public void setHealth(int healthStatus) {
        this.healthCondition = healthStatus;
    }

    @Override
    public int getHealth() {
        return this.healthCondition;
    }

    @Override
    public void setInfectionDuration(int infectionDuration) {
        this.infectionDuration = infectionDuration;
    }


    @Override
    public String toString() {
        return switch (healthCondition) {
            case 0 -> TEXT_GREEN+"h "+TEXT_RESET;
            case 1 -> TEXT_RED+"i "+TEXT_RESET;
            case 2 -> TEXT_BLUE+"r "+TEXT_RESET;
            default -> TEXT_YELLOW+"? "+TEXT_RESET;
        };
    }
}
