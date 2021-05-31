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
//    !!! Current seed isn't the same as the one being passed in Simulation class
    protected long seed=0;
    protected int healthCondition;


    public Agent(IMap map, int health) {
        super(map);
        rnd = new Random(seed);
        this.healthCondition = health;
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
    public void infect(int fieldOfView) {
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
    public void setHealth(int healthStatus) {
        this.healthCondition = healthStatus;
    }

    @Override
    public int getHealth() {
        return this.healthCondition;
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
    public String toString() {
        return switch (healthCondition) {
            case 0 -> TEXT_GREEN+"h "+TEXT_RESET;
            case 1 -> TEXT_RED+"i "+TEXT_RESET;
            case 2 -> TEXT_BLUE+"r "+TEXT_RESET;
            default -> TEXT_YELLOW+"? "+TEXT_RESET;
        };
    }
}
