import cs1.Keyboard;
import java.util.ArrayList;

public class Woo  {

    //main method runs program entirely
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
	helpText += "         ie: (zoom 10, no translations)\n";
	helpText += "status -- prints out information about the current state of the graph\n";
	helpText += "          ie: zoom level, translations, equation\n";
	helpText += "clear -- empties out the graph\n";
	helpText += "Storing functions -- Store a funtion by using any letter from the alphabet\n";
	helpText += "        exculding x,y,s,n. The syntax is letter[x] = expression.\n";
	helpText += "     eg: f[x] = x + 2 or g[x] = x^2 \n";
	helpText += "Additional Functions -- sin[x], cos[x], tan[x] and abs[x] are \n"; 
	helpText += "                        all valid functions that can be graphed and evaluated.\n ";
	helpText += "     eg: y = sin[x] : will graph the sin function.\n";
	helpText += "     eg: sin[20] : will return the value .9129452507276277\n";


	boolean graphMode = false;
	String eq = "";
	double highVal = 10;
	double totaldx = 0;
	double totaldy = 0;
	String falpha = "a b c d e f g h i j k l m o p q r t u v w z";
	//faplha are the letters eligible to be function names
	
	while (true) {
	    System.out.print("What to do...? (input 'help' for help or 'quit' to exit)\n>");
	    String input = "";
	    try {
		input = fixInput(in.readString());
	    } catch (Exception e) {
		System.out.println("Bad input");
		continue;
	    }

	    if (input.indexOf("[x]") != -1 && (falpha.contains(input.substring(input.indexOf("[x]")-1,input.indexOf("[x]")))) && (input.indexOf("y") != -1)){
		input = graph.function(input);
		System.out.println(input);
	    }
	    
	    if (input.equals("quit") || input.equals("exit"))
		break;
	    else if (input.equals("help"))
		System.out.println(helpText);
	    else if (graphMode && input.indexOf("zoom") == 0) {
		//zoom
		try {
		    double scale = MathString.notateToDouble(input.substring(5));
		    if (scale > 0) {
			graph.zoom(scale);
			graph.translate(totaldx, totaldy);
			graph.graphAll();
			highVal = scale;
			System.out.println(graph);
			System.out.println("Use 'status' to see the equations, zoom level, and translations");
		    } else
			System.out.println("Scale must be greater than 0");
		} catch (Exception e) {
		    //e.printStackTrace();
		    System.out.println("Use zoom in the format: zoom [scale]");
		}
	    } else if (graphMode && input.indexOf("translate") == 0) {
		//translate
		try {
		    String params = input.substring(10);
		    String[] coords = params.split(" ");
		    double dx = MathString.notateToDouble(coords[0]);
		    double dy = MathString.notateToDouble(coords[1]);

		    graph.translate(dx, dy);
		    graph.graphAll();

		    totaldx += dx;
		    totaldy += dy;

		    System.out.println(graph);
		    System.out.println("Use 'status' to see the equations, zoom level, and translations");
		} catch (Exception e) {
		    //e.printStackTrace();
		    System.out.println("Use translate in the format: translate [change in x] [change in y]");
		}
	    } else if (graphMode && input.indexOf("reset") == 0) {
		graph.translate(-1*totaldx, -1*totaldy);
		graph.zoom(10);
		graph.graphAll();
		totaldx = 0;
		totaldy = 0;
		highVal = 10;
		System.out.println(graph);
	    } else if (input.equals("status")) {
		System.out.println("Equations: ");
		ArrayList<String> graphs = graph.getGraphs();
		String myColor = "";
		for (int i = 0; i < graphs.size(); i++) {
		    switch (i%7) {
		    case 0:
			myColor = ANSI.WHITE;
			break;
		    case 1:
			myColor = ANSI.RED;
			break;
		    case 2:
			myColor = ANSI.GREEN;
			break;
		    case 3:
			myColor = ANSI.YELLOW;
			break;
		    case 4:
			myColor = ANSI.BLUE;
			break;
		    case 5:
			myColor = ANSI.PURPLE;
			break;
		    case 6:
			myColor = ANSI.CYAN;
			break;			
		    }
		    System.out.print(myColor);
		    System.out.print(graphs.get(i));
		    System.out.println(ANSI.RESET);
		}
		System.out.println("Zoom level: " + highVal);
		System.out.println("Total translations: " + totaldx + " " + totaldy);
	    } else if (input.equals("clear")) {
		graph.clear();
		System.out.println("Graph cleared!");

	    } else if (input.indexOf("[x]") != -1 && input.indexOf("] =") != -1 || input.indexOf("]=") != -1 && input.indexOf("=") != -1){
		try{
		    input = input.replace("X","x");
		    input = input.replace("Y","y");
		    graph.store(input);
		    if (input.indexOf("[x]") != -1 && (falpha.contains(input.substring(input.indexOf("[x]")-1,input.indexOf("[x]"))))){
			input = graph.function(input);
			input = input.substring(0,input.indexOf("="));
		    }

		    graph.graphAll("y = " + input);

		    System.out.println(graph);
		    if (!graphMode) {
			graphMode = true;
			System.out.println("Now you can use the 'zoom [scale]' and 'translate [x] [y]' commands");
		    } else if (graph.getGraphs().size() > 1) {
			System.out.println("Use 'clear' empty the graph");
		    } else if (graph.getGraphs().size() > 3) {
			System.out.println("You can store functions using the format 'f(x)=...' for later use");
		    }
		} catch (Exception e) {
		    //e.printStackTrace();
		    System.out.println("The function: " + input + " is invalid.");
		    System.out.println();
		}				    
		    
	    } else if (input.indexOf("=") != -1 && (input.indexOf("y") != -1 || input.indexOf("x") != -1)) {
		try {
		    input = input.replace("X","x");
		    input = input.replace("Y","y");

		    eq = input;
		    graph.graphAll(input);

		    System.out.println(graph);
		    if (!graphMode) {
			graphMode = true;
			System.out.println("Now you can use the 'zoom [scale]' and 'translate [x] [y]' commands");
		    } else if (graph.getGraphs().size() > 3) {
			System.out.println("You can store functions using the format 'f(x)=...' for later use");
		    } else if (graph.getGraphs().size() > 1) {
			System.out.println("Use 'clear' empty the graph");
		    }
		} catch (Exception e) {
		    //e.printStackTrace();
		    System.out.println("The equation: " + input + " is invalid. Make sure it includes at least one x or y");
		    System.out.println();
		}
	    } else {
		try {
		    System.out.println(MathString.pemdas(input).replace("~", "-"));
		} catch(Exception e) {
		    //e.printStackTrace();
		    System.out.println("The expression: " + input + " is invalid");
		    if (input.indexOf("--") != -1)
			System.out.println("Remember: use '~' for negative numbers instead of '-'");
		}
	    }
	    System.out.println();
	}
    }


    public static String fixInput(String input) {
	int index = -1;
	String nums = MathString.getNumbers()+"xy";
	
	while (input.indexOf('-', index + 1) != -1) {
	    index = input.indexOf('-', index + 1);
	    if (index == 0 || nums.indexOf(input.charAt(index-1)) == -1) {
		if (input.length() > index)
		    input = input.substring(0, index) + "~" + input.substring(index+1);
		else
		    input = input.substring(0, index);
	    }
	}

	index = 0;
	while (input.indexOf('(', index + 1) != -1) {
	    index = input.indexOf('(', index + 1);
	    int close = MathString.findClosingParen(input, index);
	    if (index > 0 && (nums+"-+/*").indexOf(input.charAt(index - 1)) == -1) {
		if (input.length() > index)
		    input = input.substring(0, index) + "[" + input.substring(index+1);
		else
		    input = input.substring(0, index);
		if (input.length() > close)
		    input = input.substring(0, close) + "]" + input.substring(close+1);
		else
		    input = input.substring(0, close);
	    }
	}
	
	return input;
    }
}
