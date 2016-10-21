package BackEndCommands.TurtleQueries;

import java.util.List;

import BackEndCommands.TurtleCommand;

/**
 * Executes the Showing command
 * @author ezra
 *
 */
public class Showing extends TurtleCommand {
	private static final int ARGS = 0;
	
	/**
	 * Gets the visible turtle property value and return 1 if its visible and 0 if its not
	 */
	@Override
	public double executeCommand(List<Double> args) {
		double answer = 0;
		if (properties.getImageVisibleProperty().get()) {
			answer++;
		}
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}