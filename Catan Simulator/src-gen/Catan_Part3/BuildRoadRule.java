package Catan_Part3;

public class BuildRoadRule implements Rule{


    @Override
    public double evaluate(Agent agent, GameMap map) {
        if (!agent.canBuildRoad(map)) return 0; //if thet cannot build it
        if (agent.getResources().totalCards() -2 <5) return 0.5; //card rule enforced
        return 0.8;

    }

    @Override
    public boolean apply(Agent agent, GameMap map) {
        return agent.tryBuildRoad(map);
    }
}
