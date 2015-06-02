package Business;

import View.PaintPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 13/05/2015.
 */
public class CommandManager {

    private static List<Command> commandList=new ArrayList<Command>();
    private static List<Command> undoneCommands=new ArrayList<Command>();
    private static String previousAction="none";

    public static void executeCommand(Command command)
    {
       if(command instanceof UndoableCommand)
           commandList.add(command);
        command.execute();

        if(previousAction.equals("undo")) clearUndoneCommands();
        previousAction="executeCommand";
    }

    private static void clearUndoneCommands() {
        undoneCommands.clear();
    }

    public static void undo()
    {
        if(commandList.size()!=0) {
            undoneCommands.add(commandList.get(commandList.size() - 1));
            commandList.remove(commandList.size() - 1);
            redoAllCommands();
        }
        previousAction="undo";
    }

    public static void redo()
    {
        if(undoneCommands.size()!=0)
        {
            commandList.add(undoneCommands.get(undoneCommands.size()-1));
            undoneCommands.remove(undoneCommands.size()-1);
            redoAllCommands();
         }
        previousAction="redo";
    }

    private static void redoAllCommands() {
        PaintPanel.setInitialState();
        for(Command command:commandList)
            command.execute();
        previousAction="redoAllCommands";
    }

    public static void clearAll() {
        commandList.clear();
        undoneCommands.clear();
        previousAction="clearAll";
    }
}
