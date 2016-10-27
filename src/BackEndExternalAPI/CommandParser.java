package BackEndExternalAPI;

import BackEndCommands.Comment;
import BackEndInternalAPI.*;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo commandType and executes the action associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 */
public class CommandParser { // TODO DE STATIC EVERYTHING

    private static final String SETTINGS_PATH = "resources/internal/Settings";
    protected static HashMap<String, Double> myVariables = new HashMap<String, Double>();
    public static HashMap<String, LogoMethod> myMethods = new HashMap<String, LogoMethod>();
    public static HashMap<String, Double> myMethodVariables = new HashMap<String, Double>();
    public static ObservableProperties myProperties;


    public HashMap<String, Double> getVariables() {
        return myVariables;
    }

    public void setProperties(ObservableProperties properties) {
        myProperties = properties;
    }


    /**
     * Determines if a specified command is indeed an executable Command
     * (i.e. not a comment or an empty line)
     *
     * @param command is the specified String command
     */
    private boolean notCommand(String[] command) {
        if (command[0].equals("")) { // this is an empty line
            return true;
        }
        CommandTypeDetector detector = new CommandTypeDetector();
        // TODO FIX
        if (detector.getCommandObj(command[0]).getClass() == Comment.class) { // this is a comment
            return true;
        }
        return false;
    }

    /**
     * Executes the cumulative action associated with a Logo commandType issued
     * from the editor
     *
     * @param command a string containing the commandType issued from the editor
     */
    public double getAction(String command) {
        ResourceBundle settings = ResourceBundle.getBundle(SETTINGS_PATH);
        String[] commands = command.trim().split(settings.getString("Delimiter"));

        if (notCommand(commands)) {
            return 0.0;
        }
        ParseTreeBuilder builder = new ParseTreeBuilder();
        builder.setProperties(myProperties);

//        ParseTreeExecutor executor = new ParseTreeExecutor();
        //  executor.executeTree(builder.initParseTree(commands)); // TODO LEAVE COMMENTED WHEN DEBUGGING

        ParseTreeNode root = builder.initParseTree(commands); // TODO DEBUGGING
        double a = root.getCommandObj().executeCommand(root.getChildren());
        return root.getCommandObj().executeCommand(root.getChildren());
    }

    public void printTree(ParseTreeNode r) { // TODO DEBUGGING
        r = r.getChild(0);
//        System.out.println("VALUE: " + r.getValue()); // make sure workign
        System.out.println("COMMAND: " + r.getCommand());

    }


//    public static void main(String[] args) { // TODO DEBUGGING
//        System.out.println("===============================================================");
//
//        System.out.println("===============================================================");
//
//    }
}