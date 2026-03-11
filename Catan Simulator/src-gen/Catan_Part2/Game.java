package Catan_Part2;

import java.util.List;
import java.util.Random;

public class Game {
	
	private GameMap map;
	
	private Agent[] agents;
	
	private int round;

	private int maxRounds;

	private int startPlayerIndex = 0; //index of player who starts each round, rotates each round

    private Robber robber;


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

        Robber robber = new Robber();

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
    public void runGame(GameState exporter) {

        while(!gameOver()){
            runRound();
            exporter.export(this);

        }

    }

	//runs one full round
	public void runRound() {

		int dice_roll = dice.roll();

        if (dice_roll == 7) {
            Agent agentRolled = agents[startPlayerIndex];
            handleRobber(agentRolled);
        }
        else {
            map.distributeResources(dice_roll);
        }


		for (int i = 0; i < agents.length; i++){
			int index = (startPlayerIndex + i) % agents.length;
			agents[index].takeTurn(map, round);
		}
        round++;
		stats();

	}

    private void handleRobber(Agent agentRolled) {

        Random random = new Random();

        for (Agent agent : agents) {

            if (agent.getResources().totalCards() > 7) {
                agent.loseHalf();
            }
        }

        map.moveRobberRandom(robber);

        List<Agent> choices = map.robberTileAdjacent(agentRolled, robber.getTileId());

        if (!choices.isEmpty()) {
            Agent victim = choices.get(random.nextInt(choices.size()));
            agentRolled.stealCard(victim);
        }
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


    public GameMap getMap() {
        return map;
    }
}
