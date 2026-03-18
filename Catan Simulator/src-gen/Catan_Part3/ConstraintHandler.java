package Catan_Part3;

/**
 *
 */
public abstract class ConstraintHandler {

    protected ConstraintHandler successor;

    public void setSuccessor(ConstraintHandler successor) {
        this.successor = successor;
    }

    public abstract boolean handle(Agent agent, GameMap map, int round);

    protected boolean passToSuccessor(Agent agent, GameMap map, int round) {

        if (successor != null) {
            return successor.passToSuccessor(agent, map, round);
        }

        return false;
    }

}
