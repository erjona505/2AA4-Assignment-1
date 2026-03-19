package Catan_Part3;

public class ConnectRoadSegmentsHandler extends ConstraintHandler{
    @Override
    public boolean handle(Agent agent, GameMap map, int round) {
        int edgeId = map.findConnectingEdge(agent);

        if (edgeId == -1) {
            return passToSuccessor(agent, map, round);
        }

        if (!agent.checkRoadCost()) {
            return passToSuccessor(agent, map, round);
        }

        agent.buyRoad();
        map.placeRoad(agent, edgeId);
        System.out.println(round + " / " + agent.getId() + ": Constraint (connect road segments) - Built road at " + edgeId);
        return true;
    }
}
