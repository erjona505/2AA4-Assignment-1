package Catan_Part3;

/**
 * abstract class for the chain of responsibility pattern
 *
 * each handler in the chain checks if a constraint applies to the situation.
 * if it does, handler solves it
 * otherwise it passes the request to the next handler in chain
 *
 * chain order: overSevenCards -> ConnectedRoadSegments -> LongestRoad
 *
 */
public abstract class ConstraintHandler {

    // references to the next handler in the chain
    protected ConstraintHandler successor;

    // sets the next handler in the chain
    public void setSuccessor(ConstraintHandler successor) {
        this.successor = successor;
    }

    // checks if the constraint hadnles it
    public abstract boolean handle(Agent agent, GameMap map, int round);

    // passes request to next handler
    protected boolean passToSuccessor(Agent agent, GameMap map, int round) {

        if (successor != null) {
            return successor.passToSuccessor(agent, map, round);
        }

        // no constraint applied
        return false;
    }

}
