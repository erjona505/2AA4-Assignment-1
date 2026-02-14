package catan;

/**
 * Abstract base class for buildings (settlements and cities)
 */
public abstract class Building {
    protected Agent owner;

    public Building(Agent owner) {
        this.owner = owner;
    }

    public Agent getOwner() {
        return owner;
    }

    /**
     * Gets the victory points this building provides
     */
    public abstract int getVictoryPoints();

    /**
     * Gets the resource multiplier (1 for settlement, 2 for city)
     */
    public abstract int getResourceMultiplier();
}
