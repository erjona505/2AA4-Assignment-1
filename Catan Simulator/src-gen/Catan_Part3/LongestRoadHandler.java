package Catan_Part3;

/**
 * implements R3.3 constraints: if any opponents longest road is at most
 * one road shorter than the agents , the agent should extend their road to maintain
 * or increase their lead.
 *
 * player with the longest road (minimum 5) gets 2 bonus VPs
 */
public class LongestRoadHandler extends ConstraintHandler {

    private Agent[] allAgents;

    // creates a handler with all agents
    public LongestRoadHandler(Agent[] allAgents) {
        this.allAgents = allAgents;
    }

    @Override
    public boolean handle(Agent agent, GameMap map, int round) {

        // gets longest road for agent and opponent
        int agentRoad = map.getLongestRoad(agent);
        int opponentRoad = map.getOpponentLongestRoad(agent, allAgents);

        // no threat
        if (opponentRoad < agentRoad - 1) {
            return passToSuccessor(agent, map, round);
        }

        // threat - but cant afford road so pass to next handler
        if (!agent.checkRoadCost()) {
            return passToSuccessor(agent, map, round);
        }

        // find valid spot to extend the road
        int edgeId = agent.roadLocation(map);
        if (edgeId == -1) {
            return passToSuccessor(agent, map, round);
        }

        // build the road to keep lead
        agent.buyRoad();
        map.placeRoad(agent, edgeId);
        System.out.println(round + " / " + agent.getId() + ": Constraint (longest road) - built road at edge " + edgeId);
        System.out.println("Agent Road Length: " + agentRoad + ", Opponent Road Length: " + opponentRoad);
        return true;


    }
}
