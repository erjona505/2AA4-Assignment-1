package Catan_Part1;


import java.io.File;
import java.util.Scanner;

/**
 * The Demonstrator class is responsible for setting up and 
 * running the game. 
 * 
 * It reads configuration data from a file, initializes the game board 
 * & agents, starts the initial setup round, and runs the game.
 * 
 * @author Erjona Kalari
 * 
 */
public class Demonstrator {

    public static void main(String[] args) {

        int maxRounds = 0;


        //read the configuration file to determine the max number of rounds
        try (Scanner sc = new Scanner(new File("config.txt"))){
            
            //expected format: "maxRounds: <number>"
            String[] num = sc.nextLine().split(":");
            maxRounds = Integer.parseInt(num[1].trim());
            

            if (maxRounds < 1 || maxRounds > 8192) {
                    System.out.println("Invalid number of rounds, must be between 1-8192.");
                    return;
                }

        } catch (Exception e) {
            System.out.println("Error reading config file: " + e.getMessage());
            return;
        }
       

        //create and initialize the game map
        GameMap map = new GameMap();
        map.initboard();

        //create 4 agents with unique IDs and empty resources
        Agent[] agents = new Agent[4];
            for (int i = 0; i < 4; i++) {
            agents[i] = new Agent(i + 1, new Resources(), 0);
            }


        Game game = new Game(map, agents, maxRounds);


        game.initalRound();

        game.runGame();

       
    }
}