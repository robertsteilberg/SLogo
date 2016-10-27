package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;
import BackEndInternalAPI.ParseTreeNode;

/**
 * Executes the PenUp command
 * @author ezra
 *
 */
public class PenUp extends TurtleCommand {
	private static final int ARGS = 0;

	/**
	 * Sets path visible property to true
	 * Returns 1
	 */
	@Override
	public double executeCommand(ParseTreeNode node) {
		properties.getPathVisibleProperty().set(false);

		return 0;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
