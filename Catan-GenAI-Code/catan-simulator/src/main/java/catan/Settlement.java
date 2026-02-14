package catan;

/**
 * Represents a settlement building
 */
public class Settlement extends Building {
    
    public Settlement(Agent owner) {
        super(owner);
    }

    @Override
    public int getVictoryPoints() {
        return 1;
    }

    @Override
    public int getResourceMultiplier() {
        return 1;
    }

    @Override
    public String toString() {
        return "Settlement(Player " + owner.getId() + ")";
    }
}
