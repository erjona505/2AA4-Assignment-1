
package Catan_Part3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverSevenCardsHandler extends ConstraintHandler {
    @Override
    public boolean handle(Agent agent, GameMap map, int round) {

        if (agent.getResources().totalCards() <= 7) {
            return passToSuccessor(agent, map, round);
        }

        ComputerAgent computer = (ComputerAgent) agent;
        boolean alreadyBuilt = true;

        while (agent.getResources().totalCards() > 7 && alreadyBuilt) {
            alreadyBuilt = false;


            List<Integer> options = new ArrayList<>(); //list our options
            options.add(0);
            options.add(1);
            options.add(2);
            Collections.shuffle(options); //shuffling the cards

            //loop through the options
            for (int choice : options) {

                if (choice == 0){
                    alreadyBuilt = computer.tryBuildRoad(map);

                    if (alreadyBuilt) {
                        System.out.println("Constraint (over 7 cards): round" + " / " + computer.getId() + ": Built road at edge " + computer.getEdgeId());
                    }
                }
                else if ( choice == 1) {
                    alreadyBuilt = computer.tryBuildSettlement(map);

                    if (alreadyBuilt) {
                        System.out.println(round + " / " + computer.getId() + ": Built settlement at node " + computer.getNodeId());
                    }
                }

                else {
                    alreadyBuilt = computer.tryBuildCity(map);

                    if (alreadyBuilt) {
                        System.out.println(round + " / " + computer.getId() + ": Upgraded to city at node " + computer.getNodeId());
                    }
                }

                if (alreadyBuilt) {
                    break;
                }

            }


        }

        return true;
    }
}
