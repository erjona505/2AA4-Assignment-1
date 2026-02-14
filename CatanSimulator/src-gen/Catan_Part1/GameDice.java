package Catan_Part1;

import java.util.Random;

public class GameDice implements Dice{

    private Random random;

    public GameDice(Random random){
        this.random = random;
    }

    @Override
    public int roll(){
        int die1 = random.nextInt(6) + 1; 
        int die2 = random.nextInt(6) + 1; 
        return die1 + die2; 
    }

}
