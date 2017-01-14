import cs1.Keyboard;

public class Woo  {
    
    public static void main(String[] args) {
	System.out.println("Welcome to Line Forge!");
	System.out.println("Enter an expression or equation to begin!");
	System.out.println();
	
	Keyboard in = new Keyboard();
	AxisGraph graph = new AxisGraph();
	
	String helpText = "";
	helpText += "help -- displays this help text\n";
	helpText += "quit (or exit) -- exits the program\n\n";
	helpText += "Enter an expression -- evaluates expression\n";
	helpText += "    eg: input: 2*(1+1)\n";
	helpText += "        output: 4.0\n";
	helpText += "Enter an equation -- graphs the equation\n";
	helpText += "    eg: input: y=x\n";
	helpText += "        output: graph of y=x\n\n";
	helpText += "After graphing, you can use these commands:\n";
	helpText += "zoom [high] -- zooms in or out on the graph so that the parameter\n";
	helpText += "               is the highest value along each axis\n";
	helpText += "    eg: input: zoom 30\n";
	helpText += "        output: the equation is graphed from [-30,30] along both axes\n";
	helpText += "translate [x] [y] -- moves the graph over\n";
	helpText += "    eg: input: translate 1 -2\n";
	helpText += "        output: moves graph 1 point left and 2 down\n";
	helpText += "reset -- reverts graph back to original settings\n";
	helpText += "         ie: (zoom 20, no translations)\n";
	helpText += "status -- prints out information about the current state of the graph\n";
	helpText += "          ie: zoom level, translations, equation\n";
	helpText += "NOTE: Use '~' instead of '-' for negative numbers\n";

	boolean graphMode = false;
	String eq = "";
	double highVal = 10;
	double totaldx = 0;
	double totaldy = 0;
	
	while (true) {
	    System.out.print("What to do...? (input 'help' for help or 'quit' to exit)\n>");
	    String input = in.readString();

	    if (input.equals("quit") || input.equals("exit"))
		break;
	    else if (input.equals("help"))
		System.out.println(helpText);
	    else if (graphMode && input.indexOf("zoom") == 0) {
		//zoom
		try {
		    double scale = Double.parseDouble(input.substring(5));
		    graph.zoom(scale);
		    graph.translate(totaldx, totaldy);
		    graph.graph(eq);
		    highVal = scale;
		    System.out.println(graph);
		} catch (Exception e) {
		    System.out.println("Use zoom in the format: zoom [scale]");
		}
	    } else if (graphMode && input.indexOf("translate") == 0) {
		//translate
		try {
		    String params = input.substring(10);
		    String[] coords = params.split(" ");
		    double dx = Double.parseDouble(coords[0]);
		    double dy = Double.parseDouble(coords[1]);

		    graph.translate(dx, dy);
		    graph.graph(eq);

		    totaldx += dx;
		    totaldy += dy;

		    System.out.println(graph);
		} catch (Exception e) {
		    System.out.println("Use translate in the format: translate [change in x] [change in y]");
		}
	    } else if (graphMode && input.indexOf("reset") == 0) {
		graph.translate(-1*totaldx, -1*totaldy);
		graph.zoom(20);
		graph.graph(eq);
		totaldx = 0;
		totaldy = 0;
		highVal = 20;
		System.out.println(graph);
	    } else if (input.equals("status")) {
		System.out.println("Equation: " + eq);
		System.out.println("Graph mode: " + graphMode);
		System.out.println("Zoom level: " + highVal);
		System.out.println("Total translations: " + totaldx + " " + totaldy);
	    } else if (input.indexOf("=") != -1 && (input.indexOf("y") != -1 || input.indexOf("x") != -1)) {
		try {
		    input = input.replace("X","x");
		    input = input.replace("Y","y");

		    eq = input;
		    graph.graph(input);

		    System.out.println(graph);
		    graphMode = true;
		    System.out.println("Now you can use the 'zoom [scale]' and 'translate [x] [y]' commands");
		} catch (Exception e) {		    
		    System.out.println("The equation: " + input + " is invalid. Make sure it includes at least one x or y");
		    System.out.println();
		}
	    } else {
		try {
		    System.out.println(MathString.pemdas(input));
		} catch(Exception e) {
		    System.out.println("The expression: " + input + " is invalid");
		    if (input.indexOf("--") != -1)
			System.out.println("Remember: use '~' for negative numbers instead of '-'");
		}
	    }
	}

	System.out.println();
    }
    
}
