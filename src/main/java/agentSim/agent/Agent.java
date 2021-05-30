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
        int currX = map.getAgentPosition(this)[0];
        int currY = map.getAgentPosition(this)[1];
        int tmp = currX - fieldOfView;
        int  minPosX = tmp >= 0 ? tmp : 0;
        tmp = currX + fieldOfView;
        int maxPosX = tmp >= map.getXDim() ? (map.getXDim()-1) : tmp;


        System.out.println("Current X position: " + currX + "\nCurrent Y position: " + currY);
        System.out.println("Max X pos is: " + maxPosX + "\nMin X pos is: " + minPosX);
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
