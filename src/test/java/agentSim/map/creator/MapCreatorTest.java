package agentSim.map.creator;

import agentSim.map.SimulationMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MapCreatorTest {
    @Test
    @DisplayName("Should Create Map")
    public void testMapCreator(){
//          Variables necessary to create agent list
        SimulationMap map = new SimulationMap(4,4);
        agentSim.map.IMap createMap;
//          Width
        Assertions.assertEquals(4, map.getXDim());
//          Height
        Assertions.assertEquals(4, map.getYDim());
    }

}
