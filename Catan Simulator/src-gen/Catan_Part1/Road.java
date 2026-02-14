package Catan_Part1;

/**
 * This class represents a Road in the Catan game.
 * 
 * A Road is a type of Building that awards 0 victory points, 
 * produces 0 resources & is placed on a specific edge of the game board.
 * Each Road is owned by an Agent and is associated with a valid edge ID.
 * 
 * @author Erjona Kalari
 */

public class Road extends Building {
	
	private int edgeId; //ID of the board edge where road is placed

	/**
	 * Constructs a Road with the specified owner and edge location.
	 *
	 * @param owner  the Agent who owns the road
	 * @param edgeId the ID of the board edge where the road is placed
	 * @throws IllegalArgumentException if edgeId is negative
	 */
	public Road(Agent owner, int edgeId){
		super(owner);
		if (edgeId < 0){
			throw new IllegalArgumentException("Path ID cannot be negative");

		}
		this.edgeId = edgeId;
	}


	public int getEdgeId(){
		return edgeId;
	}


	public boolean isPlaced() {
		return edgeId >= 0; //true if edgeID is non-negative
	}

	@Override
	public int getPoints() {
		return 0;
	}

	@Override
    public int getResourceAmount() {
        return 0; //roads do not produce resources
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
