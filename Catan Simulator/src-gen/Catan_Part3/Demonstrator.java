package Catan_Part3;

import java.io.File;
import java.util.Scanner;


/**
 * The Demonstrator class is responsible for setting up and running the game.
 * It reads configuration data from a file, initializes the game board and agents,
 * starts the initial setup round, and runs the game loop.
 *
 * This class contains the main entry point of the simulation. It demonstrates
 * the key functionality of the Catan simulator including:
 * - board initialization
 * - agent creation (1 human + 3 computer agents)
 * - initial placement phase
 * - full game loop with JSON state export for visualization
 *
 * @author Erjona Kalari
 */
public class Demonstrator {

        public static void main(String[] args) {


            int maxRounds = 0;
            String visualizerPath = "state.json"; //default path if none is specified in config


        //read the configuration file to determine the max number of rounds
            try (Scanner sc = new Scanner(new File("config.txt"))){

                //parse the max number of rounds from the first line
               String[] num = sc.nextLine().split(":");
               maxRounds = Integer.parseInt(num[1].trim());

               //validate that maxRounds is within the allowed range
            if (maxRounds < 1 || maxRounds > 8192) {
                    System.out.println("Invalid number of rounds, must be between 1-8192.");
                    return;
                }

            // parse the visualizer path from the second line if present
            //this path points to the state.json file in the local visualizer repo
            if (sc.hasNextLine()) {
                String[] path = sc.nextLine().split(":", 2);
                visualizerPath = path[1].trim();
            }

            } catch (Exception e) {
                System.out.println("Error reading config file: " + e.getMessage());
                return;
            }

        //create and initialize the game map
            GameMap map = new GameMap();
            map.initboard();
            Visualizer visualizer = new VisualizerAdapter(visualizerPath);

       //create 4 agents with unique IDs and empty resources
            Agent[] agents = new Agent[4];
            agents[0]= new HumanAgent(1, new Resources(), 0, visualizer);

                for (int i = 1; i < 4; i++) {
                agents[i] = new ComputerAgent(i + 1, new Resources(), 0);
                }


            //create the game with the board, agents, and round limit
            Game game = new Game(map, agents, maxRounds);

            //run the initial placement phase where each agent places
            game.initalRound();
            visualizer.update(game); //update visualizer after initial setup

            //run the main game loop
            game.runGame(visualizer);


        }
}