package Catan_Part1;


public class Road extends Building {
	
	private int pathId; //path edge where road is placed

	/*constructor for when pathId is unspecified
	public void Road(Agent owner) {
		super(owner);
		this.pathId = -1; 
	}*/

	public Road(Agent owner, int pathId){
		super(owner);
		if (pathId < 0){
			throw new IllegalArgumentException("Path ID cannot be negative");

		}
		this.pathId = pathId;
	}


	public int getPathId(){
		return pathId;
	}


	public boolean isPlaced() {
		return pathId >= 0;
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
		return "Road(owner=" + getOwner() + ", pathId=" + pathId + ")";
	}



}
