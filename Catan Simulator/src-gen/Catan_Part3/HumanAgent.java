package Catan_Part3;

import java.util.Scanner;

public class HumanAgent extends Agent {

    private CommandParser parser = new CommandParser();
    private CommandHistory history = new CommandHistory();
    private Visualizer visualizer;


    /**
     * Constructor for agent
     *
     * @param id
     * @param resources
     * @param points
     */
    public HumanAgent(int id, Resources resources, int points, Visualizer visualizer) {
        super(id, resources, points);
        this.visualizer = visualizer;

    }

    @Override
    public void takeTurn(GameMap map, int round, int diceRoll) {

        Scanner scanner = new Scanner(System.in);
        boolean hasRolledThisTurn=false;

        boolean done = false;

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();


            CommandType type = parser.parser(input);
            //TESTING TAKE OUT LATER
            //System.out.println("Command type: " + type);

            //ROLL actually happens in game but human has to trigger it since they are always the first player
            //if not triggered then they cannot move on
            if (type== CommandType.ROLL){
                if (hasRolledThisTurn){
                    System.out.println("You already rolled this turn ");
                }
                else {
                    System.out.println( "Rolled:" +diceRoll +" for this turn" );
                    //System.out.println("Your hand"+ getResources());
                    hasRolledThisTurn=true;

                }



            }
            else if (type== CommandType.LIST){
                System.out.println("Your hand"+ getResources());

            }



            else if(type == CommandType.BUILD_CITY){
                if (!hasRolledThisTurn){
                    System.out.println("You must roll first");
                    continue;
                }
                history.executeCommand(new BuildCityCommand(this, map, parser.getNodeId() ));
            }

            else if (type == CommandType.BUILD_SETTLEMENT) {
                if (!hasRolledThisTurn){
                    System.out.println("You must roll first");
                    continue;
                }
                history.executeCommand(new BuildSettlementCommand(this, map, parser.getNodeId() ));
            }

            else if (type == CommandType.BUILD_ROAD) {
                if (!hasRolledThisTurn){
                    System.out.println("you must roll first");
                    continue;
                }
                history.executeCommand(new BuildRoadCommand(this, map, parser.getFromNodeId(), parser.getToNodeId() ));
            }

            else if(type==CommandType.UNDO){
                history.undoCommand();
            }

            else if(type==CommandType.REDO){
                history.redoCommand();
            }

            else if (type == CommandType.GO) {
                if (!hasRolledThisTurn){
                    System.out.println("you must roll first");
                    continue;
                }
                System.out.println("Go command detected. Exiting test.");
               // scanner.close();
                return;
            }

            else if (type == CommandType.INVALID) {
                System.out.println("Invalid command");
            }
        }


    }


    @Override
    public boolean tryBuildCity(GameMap map){
        if (citiesRemaining <= 0) return false;
        if(!checkCityCost()){return false;}

        if(parser.getNodeId()==-1){return false;}

        if(map.upgrade(this, parser.getNodeId())){
            buyCity();
            citiesRemaining--;
            return true;
        }

        return false;
    }

    @Override
    public void initialTurn(GameMap map, boolean distributeResources) {
        Scanner scanner = new Scanner(System.in);

        // Settlement
        boolean settlementPlaced = false;
        while (!settlementPlaced) {
            System.out.print("Enter settlement command (e.g. 'build settlement 5'): ");
            String input = scanner.nextLine();
            CommandType type = parser.parser(input);

            if (type == CommandType.BUILD_SETTLEMENT) {
                int nodeId = parser.getNodeId();
                if (map.placeSettlement(this, nodeId, true)) {
                    if (distributeResources) map.distributeInitialResources(this, nodeId);
                    settlementPlaced = true;
                } else {
                    System.out.println("Invalid node, try again.");
                }
            } else {
                System.out.println("Must use: build settlement <nodeId>");
            }
        }

        // Road
        boolean roadPlaced = false;
        while (!roadPlaced) {
            System.out.print("Enter road command (e.g. 'build road 3 ,5'): ");
            String input = scanner.nextLine();
            CommandType type = parser.parser(input);

            if (type == CommandType.BUILD_ROAD) {
                int from = parser.getFromNodeId();
                int to = parser.getToNodeId();
                int edgeId = map.getEdgeIdFromTwoNodes(from, to);
                if (edgeId != -1 && map.placeRoad(this, edgeId)) {
                    roadPlaced = true;
                } else {
                    System.out.println("Invalid road, try again.");
                }
            } else {
                System.out.println("Must use: build road <fromNode> <toNode>");
            }
        }
    }

    @Override
    public boolean tryBuildSettlement(GameMap map){

        if (settlementsRemaining <= 0){
            System.out.println("no settlements remaining");
            return false;

        }
        if(!checkSettlementCost()){
            System.out.println("Does not have enough funds");
            return false;}


        if(parser.getNodeId()==-1){
            System.out.println("node does not exists");
            return false;}

        if(map.placeSettlement(this, parser.getNodeId(), false))
        {
            buySettlement();
            settlementsRemaining--;
            System.out.println("placed settlement with no errors");

            return true;
        }

        return false;

    }
    @Override
    public boolean tryBuildRoad(GameMap map){
        if (roadsRemaining <= 0) {
            System.out.println("no roads remaining ");
            return false;}
        if(!checkRoadCost()){
            System.out.println("no funds left");
            return false;}

        //get the nodes
        int from= parser.getFromNodeId();
        int to= parser.getToNodeId();


        if(from==-1 || to== -1){
            System.out.println("nodes does not exist ");
            return false;}

        //Convert nodes into their connecting edge
        int edgeId= map.getEdgeIdFromTwoNodes(to, from);
        if (edgeId==-1){
            System.out.println("no edge connects these two nodes");
            return false;
        }

        if(map.placeRoad(this, edgeId)){
            buyRoad();
            roadsRemaining--;
            System.out.println("placed road with no errors");
            return true;
        }

        return false;
    }

    //Return the resources when undo building
    public void returnRoad(){
        resources.add(ResourceType.WOOD, 1);
        resources.add(ResourceType.BRICK, 1);
    }

    public void returnSettlemet(){

        resources.add(ResourceType.WOOD, 1);
        resources.add(ResourceType.BRICK, 1);
        resources.add(ResourceType.WHEAT, 1);
        resources.add(ResourceType.SHEEP, 1);
    }

    public void returnCity(){
        resources.add(ResourceType.WHEAT, 2);
        resources.add(ResourceType.ORE, 3);

    }
}
