package Catan_Part3;

/**
 * This class represents the Adapter in the Adapter Pattern.
 *
 * It connects Visualizer interface to the GameState class and translates
 * calls from update(Game) into export(Game), allowing the Game
 * class to use the visualizer without knowing about JSON or file-writing details.
 */

public class VisualizerAdapter implements Visualizer {

    private final GameState state;

    //initializes the adaptee (GameState) with a file path
    public VisualizerAdapter(String filePath) {
        this.state = new GameState(filePath);

    }

    //implements the Visualizer interface method
    @Override
    public void update(Game game) {

        state.export(game); //delegate the call to adaptee
    }

    //closes visualizer
    @Override
    public void close(){
        System.out.println("Visualizer closed.");
    }


}