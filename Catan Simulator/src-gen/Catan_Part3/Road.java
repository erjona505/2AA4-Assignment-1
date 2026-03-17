package Catan_Part3;

/**
 * This class represents a Road in the Catan game.
 * 
 * A Road is a type of Building that awards 0 victory points, 
 * produces 0 resources & is placed on a specific edge of the game board.
 * Each Road is owned by an Agent and is associated with a valid edge ID.
 * 
 * @author Erjona Kalari
 */

public class Road {
	
	private final Agent owner;
    private int edgeId; //ID of the board edge where road is placed


	/**
	 * Constructs a Road with the specified owner and edge location.
	 *
	 * @param owner  the Agent who owns the road
	 * @param edgeId the ID of the board edge where the road is placed
	 * @throws IllegalArgumentException if edgeId is negative
	 */
    public Road(Agent owner, int edgeId){
        if (owner==null || edgeId < 0){
            throw new IllegalArgumentException("Agent cannot be null and/or Edge ID cannot be negative");
        }
        this.owner = owner;
        this.edgeId = edgeId;
    }

    public Agent getOwner() {
        return owner;
    }

	public int getEdgeId(){
        return edgeId;
	}


	public boolean isPlaced() {

        return edgeId >= 0; //true if edgeID is non-negative
	}


	public int getPoints() {

        return 0;
	}


	/**
	 * Returns a string representation of this Road
	 *
	 * @return formatted road description
	 */
	@Override
	public String toString() {
		return "Road(owner=" + getOwner() + ", pathId=" + edgeId + ")";
	}


}
