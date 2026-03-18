package Catan_Part3;

import Catan_Part3.GameMap;

public class BuildRoadCommand implements Command {

    HumanAgent humanAgent;
    private int fromNodeID;
    private int tomNodeID;
    GameMap gameMap;
    private boolean done = false;

    public BuildRoadCommand(HumanAgent humanAgent, GameMap gameMap, int fromNodeID, int tomNodeID) {
        this.humanAgent = humanAgent;
        this.gameMap = gameMap;
        this.fromNodeID = fromNodeID;
        this.tomNodeID = tomNodeID;
    }
    @Override
    public void execute() {
        done = humanAgent.tryBuildRoad(gameMap);
        if(done){
            System.out.printf("Successfully built road form node %d to node %d" ,fromNodeID,tomNodeID);}
        else{
            System.out.printf("Failed to built road form node %d to node %d",fromNodeID, tomNodeID) ;
        }
    }

    @Override
    public void undo() {
        if(done){
            gameMap.removeRoad(humanAgent ,fromNodeID, tomNodeID);
            humanAgent.returnRoad();
            humanAgent.roadsRemaining++;
            done=false;
            System.out.println("Undo: removed road");
        }
    }
}
