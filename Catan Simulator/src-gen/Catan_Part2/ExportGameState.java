//package Catan_Part2;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
///**
// * The ExportGameState class exports the current state of the game to a JSON file.
// * It provides a method to convert the game state to JSON format and write it to a specified file.
// *
// * @author Erjona Kalari
// *
// */
//
//public class ExportGameState {
//
//    //name of the file to export the game state to
//    private String filename;
//
//    public ExportGameState(String filename) {
//        this.filename = filename;
//    }
//
//    //method to convert the game state to JSON format
//    public void export(Game game) {
//        String json = toJson(game);
//        writeToFile(json); //write JSON string to file
//    }
//}
