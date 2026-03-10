package Catan_Part2;

import java.util.Scanner;

public class HumanAgent extends Agent {

    CommandParser parser = new CommandParser();
    /**
     * Constructor for agent
     *
     * @param id
     * @param resources
     * @param points
     */
    public HumanAgent(int id, Resources resources, int points) {
        super(id, resources, points);

    }

    @Override
    public void takeTurn(GameMap map, int round) {

        Scanner scanner = new Scanner(System.in);

        boolean done = false;

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            CommandType type = parser.parser(input);

            System.out.println("Command type: " + type);

            if(type == CommandType.BUILD_CITY){
                done=tryBuildCity(map);
                if(done){
                    System.out.println("Successfully built city for node " + parser.getNodeId());}
                else{
                    System.out.println("Failed to built city for node " + parser.getNodeId());
                }


            }

            if (type == CommandType.BUILD_SETTLEMENT) {
                done=tryBuildSettlement(map);
                if(done){
                    System.out.println("Successfully built settlement for node " + parser.getNodeId());}
                else{
                    System.out.println("Failed to built settlement for node " + parser.getNodeId());
                }
            }

            if (type == CommandType.BUILD_ROAD) {
                System.out.println("From node: " + parser.getFromNodeId());
                System.out.println("To node: " + parser.getToNodeId());
                done = tryBuildRoad(map);
                if(done){
                    System.out.printf("Successfully built road form node %d to node %d" ,parser.getFromNodeId(),parser.getToNodeId());}
                else{
                    System.out.printf("Failed to built road for form node %d to node %d",parser.getFromNodeId(),parser.getToNodeId());
                }
            }

            if (type == CommandType.GO) {
                System.out.println("Go command detected. Exiting test.");
                scanner.close();
                return;
            }

            if (type == CommandType.INVALID) {
                System.out.println("Invalid command");
            }
        }


    }

    @Override
    public boolean tryBuildCity(GameMap map){
        if (citiesRemaining <= 0) return false;
        if(!checkCityCost()){return false;}

        if(getNodeId()==-1){return false;}

        if(map.upgrade(this, parser.getNodeId())){
            buyCity();
            citiesRemaining--;
            return true;
        }

        return false;
    }
    @Override
    public boolean tryBuildSettlement(GameMap map){

        if (roadsRemaining <= 0) return false;
        if(!checkSettlementCost()){return false;}

        if(getNodeId()==-1){return false;}

        if(map.placeSettlement(this, parser.getNodeId(), false))
        {
            buySettlement();
            settlementsRemaining--;
            return true;
        }

        return false;

    }
    @Override
    public boolean tryBuildRoad(GameMap map){
        if (roadsRemaining <= 0) return false;
        if(!checkRoadCost()){return false;}

        if(getNodeId()==-1){return false;}

        if(map.placeRoad(this, parser.getNodeId())){
            buyRoad();
            roadsRemaining--;
            return true;
        }

        return false;
    }
}
