package agentSym.agent;

import agentSym.siatka.ISiatka;

public interface IAgent {
    public void ruch();
    public ISiatka getSiatka();
    public void setSiatka();
    public boolean jestChory(IAgent agent);
}
