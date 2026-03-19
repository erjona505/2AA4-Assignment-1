package Catan_Part3;

public class LongestRoadHandler extends ConstraintHandler {

    private Agent[] allAgents;

    public LongestRoadHandler(Agent[] allAgents) {
        this.allAgents = allAgents;
    }

    @Override
    public boolean handle(Agent agent, GameMap map, int round) {

        int agentRoad = map.getLongestRoad(agent);
        int opponentRoad = map.getOpponentLongestRoad(agent, allAgents);

        if (opponentRoad < agentRoad - 1) {
            return passToSuccessor(agent, map, round);
        }

        if (!agent.checkRoadCost()) {
            return passToSuccessor(agent, map, round);
        }

        int edgeId = agent.roadLocation(map);
        if (edgeId == -1) {
            return passToSuccessor(agent, map, round);
        }

        agent.buyRoad();
        map.placeRoad(agent, edgeId);
        System.out.println(round + " / " + agent.getId() + ": Constraint (longest road) - built road at edge " + edgeId);
        System.out.println("Agent Road Length: " + agentRoad + ", Opponent Road Length: " + opponentRoad);
        return true;


    }
}
