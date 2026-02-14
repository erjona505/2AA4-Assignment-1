package Catan_Part1;

import java.util.Random;

public class Game {
	
	private GameMap map;
	
	private Agent[] agents;

	private Dice dice;
	
	private int round;

	private int maxRounds;

	private int startPlayerIndex = 0; //index of player who starts each round, rotates each round


	//constructor 
	public Game(GameMap map, Agent[] agents, int maxRounds) {
		this.map = map;
		this.agents = agents;
		this.dice = new GameDice(new Random());
		this.round = 1;
		this.maxRounds = maxRounds;
	}

	//initial round, each agent place 1 settlement & 1 road
	public void initalRound(){
		
		for (int i = 0; i < agents.length; i++){
			Agent agent = agents[i];
			int nodeId = agent.settlementLocation(map, true);
			map.placeSettlement(agent, nodeId, true);
            System.out.println("Player " + agent.getId() + ": Settlement at node " + nodeId);

			int edgeId = agent.roadLocation(map);
			map.placeRoad(agent, edgeId);
            System.out.println("Player " + agent.getId() + ": Road at edge " + edgeId);
			
		}

		//reverse agent turn order for round 2
		for (int i = agents.length - 1; i >= 0; i--){
			Agent agent = agents[i];
			
			int nodeId = agent.settlementLocation(map, true);
			map.placeSettlement(agent, nodeId, true);
			System.out.println("Player " + agent.getId() + ": Settlement at node " + nodeId);

            map.distributeInitialResources(agent, nodeId);
            System.out.println("Player " + agent.getId() + ": collected initial resources");

            int edgeId = agent.roadLocation(map);
			map.placeRoad(agent, edgeId);
			System.out.println("Player " + agent.getId() + ": Road at edge " + edgeId);

		}


		stats();
	}

	//runs the game
	public void runGame() {
		while(!gameOver()){
			runRound();
		}

	}

	//runs one full round
	public void runRound() {

		int dice_roll = dice.roll();
		map.distributeResources(dice_roll);

		for (int i = 0; i < agents.length; i++){
			int index = (startPlayerIndex + i) % agents.length;
			agents[index].takeTurn(map);
		}
		round++;
		stats();
	}


	//check conditions to end the game
	public boolean gameOver() {
		if (round >= maxRounds){
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
            System.out.print( " Player " + agent.getId() + ": " + agent.getTotalPoints() + " ");
        }

        System.out.println();
    }


}
