package BackEndExternalAPI;

import BackEndInternalAPI.*;
import GUIController.GUIVariables;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;

import java.util.*;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This class interprets a Logo command and executes the action(s) associated with it
 *         using the specified arguments. This is done by recursively creating a parse tree
 *         and executing down the nodes of the parse tree, checking for errors along the way.
 *         <p>
 *         Dependencies: ParseTreeBuilder, ObservableProperties
 */
public class CommandParser {


    private static ObservableComposite myTurtleProperties;
    private static DisplayProperties myDisplayProperties;
    private static ObservableMap<String,Double> myVariables;
    private static HashMap<String, Double> myMethodVariables; // temporary map for method variables
    private static HashMap<String, LogoMethod> myMethods;
    private static HashMap<String, Integer> myMethodVariableDeclarations;
    private static SimpleStringProperty myLanguageBinding;
    private static HashSet<String> myErrors;


    public CommandParser() {
        myMethodVariables = new HashMap<String, Double>();
        myMethods = new HashMap<String, LogoMethod>();
        myMethodVariableDeclarations = new HashMap<String, Integer>();
    }


    // TODO SET BINDINGS

    /**
     * Initializes the String binding that for language detection
     *
     * @param languageBinding the SimpleStringProperty representing the bound String
     */
    public void initLanguageBinding(SimpleStringProperty languageBinding) {
        myLanguageBinding = languageBinding;
    }

    /**
     * Initializes the observable properties for Turtle manipulation
     *
     * @param properties the ObservableProperties object representing bound Turtle properties
     */
    public void initPropertiesBinding(ObservableComposite turtleProperties, DisplayProperties displayProperties) {
        myTurtleProperties = turtleProperties;
        myDisplayProperties = displayProperties;
    }

    /**
     * Initializes the global variable map
     *
     * @param variables the GUIVariables object that interfaces variables with the GUI
     */
    public void initVariablesBinding(GUIVariables variables) {
        myVariables = FXCollections.observableHashMap();
        myVariables.addListener((MapChangeListener<String, Double>) (change) ->
                variables.setMap(change.getMap()));
    }

    /**
     * Getter for set containing error messages
     *
     * @return the set containing error messages
     */
    public static HashSet<String> getErrors() {
        return myErrors;
    }


    /**
     * Initializes a ParseTreeBuilder by creating it, specifying its language,
     * binding its Turtle properties, setting its variable maps, and passing in
     * a set in which errors are placed
     *
     * @return the newly initialized ParseTreeBuilder
     */
    private ParseTreeBuilder initBuilder() {
        ParseTreeBuilder newBuilder = new ParseTreeBuilder();
        newBuilder.setLanguage(myLanguageBinding);
        newBuilder.setProperties(myTurtleProperties, myDisplayProperties);
        newBuilder.setMappings(new Mappings(myVariables, myMethods, myMethodVariables, myMethodVariableDeclarations));
        newBuilder.setErrorSet(myErrors);
        return newBuilder;
    }

    private void buildAndExecuteTree(String[] command, ArrayList<Double> results, int line) {
        ParseTreeBuilder builder = initBuilder();
        ParseTreeNode parseTree = builder.buildNewParseTree(command, line);
        myErrors.addAll(builder.getErrors());
        if (myErrors.size() == 0) {
            // TODO NOT ENOUGH ARGS ERROR
            double result = parseTree.getCommandObj().executeCommand(parseTree);
            myMethodVariables.clear(); // clear temporary method variables
            results.add(result);
        }
        line++;
    }

    /**
     * Executes the cumulative action associated with a Logo command issued
     * from the GUI
     *
     * @param commands a string containing the commands issued from the editor
     */
    public ArrayList<Double> executeCommands(String[] commands) {
        ArrayList<String> commandList = new ArrayList<String>();

        ArrayList<Double> results = new ArrayList<Double>();
        myErrors = new HashSet<String>();


        commandList.add("[");
        for (String command : commands) {
            String[] splitCommands = command.trim().split("\\p{Space}");
            for (String splitCommand : splitCommands) {
                if (!splitCommand.equals("")) {
                    commandList.add(splitCommand);
                }
            }
        }
        commandList.add("]");
        String[] coms = new String[commandList.size()];
        coms = commandList.toArray(coms);
        buildAndExecuteTree(coms, results, 1);


        return results;
    }
}