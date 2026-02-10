package Catan_Part1;

import java.util.Random;

public class Dice {
    
    private Random random;
    
    //constructor 
    public Dice(){
        this.random = new Random();
    }
    
    //simulates rolling 2 six-sided dice and return the sum
    public int roll(){
        int die1 = random.nextInt(6) + 1; 
        int die2 = random.nextInt(6) + 1; 
        return die1 + die2; 
    }

}