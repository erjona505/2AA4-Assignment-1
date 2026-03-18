package Catan_Part3;
import java.util.LinkedList;
import java.util.Stack;

public class CommandHistory {
    Stack<Command> redostack= new Stack<>();
    Stack<Command> undostack =new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        undostack.push(command);
        redostack.clear();
    }

    public void undoCommand() {
        if(canUndo()){
            Command undo =  undostack.pop();
            redostack.push(undo);
            undo.undo();
        }
        else{
            System.out.println("Nothing to undo");
        }
    }

    public void redoCommand() {
        if(canRedo()){
            Command redo =  redostack.pop();
            redo.execute();
            undostack.push(redo);
        }
        else {
            System.out.println("Nothing to redo");
        }
    }


    public boolean canUndo() {
        return !undostack.isEmpty();
    }
    public boolean canRedo() {
        return !redostack.isEmpty();
    }

}
