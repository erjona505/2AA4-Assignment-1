package Catan_Part2;

import java.util.Scanner;

public class Game {
	
	private GameMap map;
	
	private Agent[] agents;
	
	private int round;

	private int maxRounds;

	private int startPlayerIndex = 0; //index of player who starts each round, rotates each round


    GameDice dice = new GameDice();

	//constructor 
	public Game(GameMap map, Agent[] agents, int maxRounds) {
		this.map = map;
		this.agents = agents;
		this.round = 0;
		this.maxRounds = maxRounds;
	}

	//initial round, each agent place 1 settlement & 1 road
	public void initalRound(){

        System.out.println("------INITIAL SETUP-------");


		for (int i = 0; i < agents.length; i++){

			Agent agent = agents[i];
			int nodeId = agent.settlementLocation(map, true);
			map.placeSettlement(agent, nodeId, true);
            System.out.println(1 + " / " + agent.getId() + ": Built settlement at node " + nodeId);

			int edgeId = agent.roadLocation(map);
			map.placeRoad(agent, edgeId);
            System.out.println(1 + " / " + agent.getId() + ": Built road at edge " + edgeId);
			
		}

        System.out.println();


		//reverse agent turn order for round 2
		for (int i = agents.length - 1; i >= 0; i--){



			Agent agent = agents[i];
			
			int nodeId = agent.settlementLocation(map, true);
			map.placeSettlement(agent, nodeId, true);
            System.out.println(2 + " / " + agent.getId() + ": Built settlement at node " + nodeId);

            map.distributeInitialResources(agent, nodeId);
            System.out.println("Player " + agent.getId() + ": collected initial resources");

            int edgeId = agent.roadLocation(map);
			map.placeRoad(agent, edgeId);
            System.out.println(2 + " / " + agent.getId() + ": Built road at edge " + edgeId);



		}

        System.out.println();


	}


    //run the game and export state after each round
    public void runGame() {

        while(!gameOver()){
            runRound();
            //exporter.export(this);

        }

    }

    //Helper function for runround
    private void waitForGo(){
        CommandParser p =new CommandParser();
        Scanner sc= new Scanner(System.in);

        while (true){
            System.out.println("> ");
            String input= sc.nextLine();
            if (p.parser(input)==CommandType.GO){return;}
            else{
            System.out.println("Invalid! Type go to continue");}

        }
    }

	//runs one full round
	public void runRound() {

		int dice_roll = dice.roll();
		map.distributeResources(dice_roll);
        // Print each player's resources


		for (int i = 0; i < agents.length; i++){
			int index = (startPlayerIndex + i) % agents.length;
			agents[index].takeTurn(map, round, dice_roll);

            //if this was a computers turn, we have to wait for human to let us go
            if (agents[index] instanceof  ComputerAgent){
                System.out.println("Player" + agents[index].getId() + "turn");
                System.out.println("Type Go to Proceed");
                waitForGo();
            }
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
            if (agent.getTotalPoints() >= 10) {
                return true;
            }
        
		}

	return false;

	}

	//print round and each player points
	public void stats(){

		System.out.print("Round " + round + ": ");

		for (Agent agent : agents) {
            System.out.print( " Player " + agent.getId() + ": " + agent.getTotalPoints() + " ");
        }

        System.out.println();
    }


}
