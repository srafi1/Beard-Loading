import cs1.Keyboard;

public class Woo  {
    
    public static void main(String[] args) {
	System.out.println("Welcome to Line Forge!");
	
	Keyboard in = new Keyboard();
	while (true) {
	    System.out.print("What to do? (input 'help' for help)\n>");
	    String input = in.readString();

	    if (input.equals("quit"))
		break;
	    else if (input.equals("help"))
		System.out.println("Enter an expression to evaluate it or 'quit' to quit");
	    else {
		try {
		    System.out.println(MathString.pemdas(input));
		} catch(Exception e) {
		    System.out.println("A voice asks you to rethink your actions");
		}
	    }
	}
    }
    
}
