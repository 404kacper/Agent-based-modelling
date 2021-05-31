package agentSim.agent;

import agentSim.map.IMap;

import java.util.Random;

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
        int tmp = currR - fieldOfView;
        int  minRow = tmp >= 0 ? tmp : 0;
        tmp = currR + fieldOfView;
        int maxRow = tmp >= map.getXDim() ? (map.getXDim()-1) : tmp;


        System.out.println("Current row: " + currR + "\nCurrent col: " + currC);
        System.out.println("Min row: " + currR + "\nMax X col: " + currC);
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
            case 0 -> "h ";
            case 1 -> "r ";
            case 2 -> "i ";
            default -> "? ";
        };
    }
}
