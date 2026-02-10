package Catan_Part1;

public class Game {
	
	private GameMap map;
	
	private Agent[] agents;
	
	private int round;

	private int max_rounds = 8192;

	public Agent[] agent;

	//constructor 
	public Game(GameMap map, Agent[] agents, int round, int max_rounds){
		this.map = map;
		this.agent = agent;
		this.round = round;
		this.max_rounds = max_rounds;
	}

	//initial round, each agent palces 2 settlements & 2 roads
	public void initalRound(){
		for (Agent agent : agents){
			for (int round = 0; round < 2; round++){
				int nodeId = agent.settlementLocation(map, true);
				map.placeSettlement(agent, nodeId, true);

				int edgeId = agent.roadLocation(map);
				map.placeRoad(agent, edgeId);
			}
		}
		stats();
	}

	//runs the game
	public void runGame() {
		while(!gameOver()){
			runRound();
			round ++;
		}

	}

	//runs one full round
	public void runRound() {

		Dice dice = new Dice();
		int dice_roll = dice.roll();
		map.distributeResources(dice_roll);

		for (Agent agent : agents){
			agent.takeTurn(map, round);
        }

		stats();
	}


	//check conditions to end the game
	public boolean gameOver() {
		if (round > max_rounds){
			return true;
		}

		for (Agent agent : agents) {
            if (agent.getTotalPoints() == 10) {
                return true;
            }
        
		}

	return false;

	}

	//print round and each player points
	public void stats(){

		System.out.print("Round: " + round);

		for (Agent agent : agents) {
            System.out.print( " Player: " + agent.getId() + "=" + agent.getTotalPoints() + " ");
        }

        System.out.println();
    }


}
