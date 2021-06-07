package agentSim;

import agentSim.agent.IAgent;
import com.google.common.primitives.Ints;

import java.util.Comparator;

public class SortByHealth implements Comparator<IAgent> {
    @Override
    public int compare(IAgent o1, IAgent o2) {
        return Ints.compare(o1.getHealth(), o2.getHealth());
    }
}
