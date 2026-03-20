package Catan_Part3;

public class BuildCityRule implements Rule{
    @Override
    public double evaluate(Agent agent, GameMap map) {
        if (!agent.tryBuildCity(map)) return 0;
        return 1;  //city gives VP
    }

    @Override
    public boolean apply(Agent agent, GameMap map) {
        return agent.tryBuildCity(map);
    }
}
