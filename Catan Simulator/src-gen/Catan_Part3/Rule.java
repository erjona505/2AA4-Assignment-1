package Catan_Part3;

public interface Rule {
    double evaluate (Agent agent, GameMap map);
    boolean apply (Agent agent, GameMap map);
}
