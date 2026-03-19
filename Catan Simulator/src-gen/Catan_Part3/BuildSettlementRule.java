package Catan_Part3;

public class BuildSettlementRule implements  Rule{
    @Override
    public double evaluate(Agent agent, GameMap map) {
        if (!agent.canBuildSettlement(map)) return 0;   //cannot build a settlement
        return 1.0; //settlement gives VP



    }

    @Override
    public boolean apply(Agent agent, GameMap map) {
        return agent.tryBuildSettlement(map);
    }
}
