package Catan_Part1;


public abstract class Building {
	
	private Agent owner; //immutable after construction

	//constructor 
	public void Building(Agent owner) {
		if (owner == null){
			throw new IllegalArgumentException("Owner cannot be null");
		}

		this.owner = owner;
	}


	public Agent getOwner(){
		return owner;
	}

	//victory points (0 for roads, 1 for settlements, 2 for cities)
	public abstract int getPoints();

	//determines how many resource cards player gets
	public abstract int getResourceAmount();

	


}
