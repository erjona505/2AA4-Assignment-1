package Catan_Part3;

import Catan_Part3.GameMap;

public class BuildCityCommand implements Command {
    HumanAgent humanAgent;
    private int nodeID;
    GameMap gameMap;
    private boolean done = false;

    BuildCityCommand(HumanAgent humanAgent, GameMap gameMap, int nodeID) {
        this.humanAgent = humanAgent;
        this.nodeID = nodeID;
        this.gameMap = gameMap;
    }

    @Override
    public void execute() {
        done = humanAgent.tryBuildCity(gameMap);
        if(done){
            System.out.printf("Successfully built city for node %d" ,nodeID);}
        else{
            System.out.printf("Failed to built city for node %d",nodeID) ;
        }
    }

    @Override
    public void undo() {
        if(done){
            gameMap.removeCity(humanAgent ,nodeID);
            humanAgent.returnCity();
            humanAgent.citiesRemaining++;
            humanAgent.settlementsRemaining--;
            done=false;
            System.out.println("Undo: removed city");
        }
    }
}
