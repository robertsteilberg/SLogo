package BackEndExternalAPI;

import java.util.ArrayList;

import BackEndInternalAPI.ParserTreeNode;
import BackEndInternalAPI.TreeParser;

/**
 * 
 * @author Robert H. Steilberg II
 *
 */
public class CommandParser {

	private static double executeTree(ParserTreeNode currNode) {
	// 			sum 2 3
	// 			sum 
		//		/  \
		//	   2    3
		// start at head; if head is a function, get its children, and execute the function with the children, set value of curr to be resulting value
		// if a child is a function, recursively execute that until it is equivalent to something, then pop back up
		// CHECK IS IF THE VALUE IS NULL

		if (currNode.isConstant()) {
			return Double.parseDouble(currNode.getValue());
		}
		// otherwise, we must have a function!! get children values, execute
		// get args, put into arraylist
		
		ArrayList<Double> arguments = new ArrayList<Double>();
		
		for (int i = 0; i < currNode.numChildren; i++) {
			arguments.add(  executeTree(currNode.children.get(i))  );
		}
		
		Double result = currNode.getCmdObj().executeCommand(arguments); // we have the result
		currNode.setValue(Double.toString(result)); // update value of node
		currNode.setConstant(true);
		return result;
	}
	
	
	/**
	 * 
	 * @param command
	 *            a string containing the command issued from the editor
	 */
	public static void getAction(String command) {
		String[] commands = command.split(" ");
		TreeParser parser = new TreeParser();
		ParserTreeNode root = parser.makeParserTree(commands);
		// root is now the parser tree, execute
		executeTree(root);
//		System.out.println(root.numChildren);
		printTree(root);
	}

	public static void printTree(ParserTreeNode r) {
		System.out.println(r.value);
		if (r.children.isEmpty()) {
			return;
		}
		for (int i = 0; i < r.numChildren; i++) {
			System.out.println("CHILD " + i);
			printTree(r.children.get(i));
		}
	}

	public static void main(String[] args) {
		getAction("sum 232 3");
	}
}
