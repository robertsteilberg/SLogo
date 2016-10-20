package BackEndCommands.TurtleCommands;

import java.util.List;

import BackEndCommands.TurtleCommand;


public class Home extends TurtleCommand {
	private static final int ARGS = 0;

	@Override
	public double executeCommand(List<Double> args) {
		//Need to update imageview rotation
		double answer = calculateTotalDistance(properties.getXProperty().get(), 0,
												properties.getYProperty().get(), 0);
		properties.getXProperty().set(0);
		properties.getYProperty().set(0);
		return answer;
	}

	@Override
	public int numArguments() {
		return ARGS;
	}
}
