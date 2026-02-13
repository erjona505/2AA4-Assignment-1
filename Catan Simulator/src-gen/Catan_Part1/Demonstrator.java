package Catan_Part1;


import java.io.File;
import java.util.Scanner;

public class Demonstrator {

    public static void main(String[] args) {

        int max_rounds = 0;


        //read config file for number of rounds
        try {
            Scanner sc = new Scanner(new File("config.txt"));
            String[] num = sc.nextLine().split(":");
            max_rounds = Integer.parseInt(num[1].trim());
            sc.close();
            if (max_rounds < 1 || max_rounds > 8192) {
                    System.out.println("Invalid number of rounds, must be between 1-8192.");
                    return;
                }

        } catch (Exception e) {
            System.out.println("Error reading config file: " + e.getMessage());
        }

        //create game map and agents
        GameMap map = new GameMap();
        map.initboard();

        Agent[] agents = new Agent[4];
            for (int i = 0; i < 4; i++) {
            agents[i] = new Agent(i + 1, new Resources(), 0);
            }


        Game game = new Game(map, agents, max_rounds);


        game.initalRound();

        game.runGame();

       
    }
}