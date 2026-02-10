package Catan_Part1;


public class Road extends Building {
	
	private int edgeId; //path edge where road is placed

	
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
		return edgeId >= 0;
	}

	@Override
	public int getPoints() {
		return 0;
	}

	@Override
    public int getResourceAmount() {
        return 0; //roads do not produce resources
    }

	@Override
	public String toString() {
		return "Road(owner=" + getOwner() + ", pathId=" + edgeId + ")";
	}



}
