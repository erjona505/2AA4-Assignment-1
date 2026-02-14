package catan;

/**
 * Represents a city building (upgraded settlement)
 */
public class City extends Building {
    
    public City(Agent owner) {
        super(owner);
    }

    @Override
    public int getVictoryPoints() {
        return 2;
    }

    @Override
    public int getResourceMultiplier() {
        return 2;
    }

    @Override
    public String toString() {
        return "City(Player " + owner.getId() + ")";
    }
}
