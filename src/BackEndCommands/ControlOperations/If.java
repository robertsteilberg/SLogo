package BackEndCommands.ControlOperations;

import BackEndCommands.ControlCommand;
import BackEndInternalAPI.Command;
import BackEndInternalAPI.ParseTreeExecutor;

import java.util.List;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         This command instance represents an if statement in Logo.
 */
public class If extends ControlCommand {

    private static final int ARGS = 2;

    @Override
    public double executeCommand(List<Double> args) {
        if (args.get(0) == 0) {
            return 0;
        } else {
            ParseTreeExecutor executor = new ParseTreeExecutor();
            return executor.executeTree(executables.get(1));
        }
    }

    @Override
    public int numArguments() {
        return ARGS;
    }
}