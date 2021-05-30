package agentSim.agent;

import agentSim.map.IMap;

import java.util.Random;

public class Agent extends AAgent implements IAgent {

    protected Random rnd;
//    !!! Current seed isn't the same as the one being passed in Simulation class
    protected long seed=0;

    public Agent(IMap map) {
        super(map);
        rnd = new Random(seed);
    }

    @Override
    public void move() {
    do {
        // getSize has changed
        int position_x = rnd.nextInt(map.getXDim());
        int position_y = rnd.nextInt(map.getYDim());
        if (map.getAgent(position_x, position_y) == null) {
            map.placeAgent(this, position_x, position_y);
            break;
        }
     } while(true);
    }
}
