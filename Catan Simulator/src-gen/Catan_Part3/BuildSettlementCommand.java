package Catan_Part3;

import Catan_Part3.GameMap;

public class BuildSettlementCommand implements Command {
    HumanAgent humanAgent;
    private int nodeID;
    GameMap gameMap;
    private boolean done = false;

    BuildSettlementCommand(HumanAgent humanagent,  GameMap gameMap, int nodeID) {
        this.humanAgent = humanagent;
        this.gameMap = gameMap;
        this.nodeID = nodeID;
    }

    @Override
    public void execute() {
        done = humanAgent.tryBuildSettlement(gameMap);
        if(done){
            System.out.printf("Successfully built settlement for node %d" ,nodeID);}
        else{
            System.out.printf("Failed to built settlement for node %d",nodeID) ;
        }
    }

    @Override
    public void undo() {
        if(done){
            gameMap.removeSettlement(humanAgent ,nodeID);
            humanAgent.returnSettlemet();
            humanAgent.settlementsRemaining++;
            done=false;
            System.out.println("Undo: removed settlement");
        }
    }
}
