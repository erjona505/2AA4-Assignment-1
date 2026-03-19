package Catan_Part3;

/**
 * implements R3.3 Constraint if two of the agent's road segments are at most two edges apart,
 * then the agent should try to connect them by building a road in the gap
 */
public class ConnectRoadSegmentsHandler extends ConstraintHandler{
    @Override
    public boolean handle(Agent agent, GameMap map, int round) {

        // finds empty edge to connect road segments
        int edgeId = map.findConnectingEdge(agent);


        // pass to next handler if no gap found
        if (edgeId == -1) {
            return passToSuccessor(agent, map, round);
        }

        // can't afford a road  pass to the next handler
        if (!agent.checkRoadCost()) {
            return passToSuccessor(agent, map, round);
        }

        // build the road
        agent.buyRoad();
        map.placeRoad(agent, edgeId);
        System.out.println(round + " / " + agent.getId() + ": Constraint (connect road segments) - Built road at " + edgeId);
        return true;
    }
}
