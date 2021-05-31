package agentSim.agent;

import agentSim.map.IMap;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.primitives.Ints;

import java.util.Random;

import static com.google.common.primitives.Ints.max;
import static com.google.common.primitives.Ints.min;

public class Agent extends AAgent implements IAgent {

    protected Random rnd;
//    !!! Current seed isn't the same as the one being passed in Simulation class
    protected long seed=0;
    protected int healthCondition;


    public Agent(IMap map, int health) {
        super(map);
        rnd = new Random(seed);
        this.healthCondition = health;
    }

    public void getNeighbours(int fieldOfView) {
        int currR = map.getAgentPosition(this)[0];
        int currC = map.getAgentPosition(this)[1];
        int rowLimit = map.getXDim()-1;
        int colLimit = map.getYDim()-1;

//        Multimap to store agent and their neighbour locations
        Multimap<IAgent, Integer> positions;
//        Initializing the multimap
        positions = ArrayListMultimap.create();

            mapLoop: for (int x = max(0, currR-fieldOfView); x <= Math.min(currR+fieldOfView, rowLimit); x++) {
                for (int y = max(0, currC-fieldOfView); y <= min(currC+fieldOfView, colLimit); y++) {
//                    Check if the x and y aren't coordinates of the agent
                        if (x != currR || y != currC) {
//                            Check if neighbour is not empty
                            if (map.getAgent(x,y) != null) {
//                                Store positions in multiMap
                                positions.putAll(this, Ints.asList(x,y));
//                                System.out.println("Found non-empty neighbour of " + this + ":" + map.getAgent(x, y));
                            }
                        }
                    }
                }
        System.out.println(positions.toString());

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
        char charVal = (char)healthCondition;
        return String.valueOf(charVal)+" ";
//        return switch (healthCondition) {
//            case 0 -> "h ";
//            case 1 -> "r ";
//            case 2 -> "i ";
//            default -> "? ";
//        };
    }
}
