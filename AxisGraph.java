import java.util.ArrayList;

public class AxisGraph  {
    private Point[][] plane;
    private double highest;
    private ArrayList<String> graphs = new ArrayList<String>();
    private ArrayList<String> storage = new ArrayList<String>();

    //constructor method, makes a 41 by 41 array of Points and autozooms to 10
    public AxisGraph() {
	plane = new Point[41][41];
	
	for (int y = 0; y < plane.length; y++)
	    for (int x = 0; x < plane[0].length; x++)
		plane[y][x] = new Point();
	zoom(10);
    }
    
    //prints out the 2D array of points
    public String toString() {
	String retStr = "";

	for (Point[] row : plane) {
	    for (Point p : row)
		retStr += p;
	    retStr += "\n";
	}

	return retStr;
    }

    //zoom determines the spacing of each point given the highest values of both x and y
    public void zoom(double highVal){
	double increment = highVal / ((plane.length - 1) / 2.0);
	for (int y = 0; y < plane.length; y++){
	    double yVal = highVal - (y*increment); 
	    for(int x = 0; x < plane.length; x++){
		double xVal = (-1 * highVal) + (x*increment);
		plane[y][x].setCor(xVal,yVal);
		//	if (graphs.size() != 0)
		//    reGraphAll();
	    }
	}
	refresh();
	highest = highVal;
    }

    //runs Point.translate(double,double) on all points, moving the entire graph.
    public void translate(double dx, double dy) {
	for (Point[] row : plane)
	    for (Point p : row)
		p.translate(dx, dy);
	refresh();
    }

    //runa closeEnough on all the Points, forming a graph
    public void graph(String eq, int num){
	double increment =  highest / ((plane.length - 1) / 2.0);
	for(Point[] row: plane)
	    for(Point p: row)
		p.closeEnough_Color(eq, increment / 2.0, num);
    }

    //graphs new given equation as well as all of the previous ones for graph overlay.
    public void graphAll(String eq){
	graphs.add(eq);
	graphAll();
    }

    public void graphAll() {
	for(int x = 0; x < graphs.size();x++)
	    graph(graphs.get(x), x);
    }

    //runs reset() on all Points
    public void refresh(){
	for(Point[] row: plane){
	    for(Point p: row){
		p.reset();
		p.checkAxis();
	    }
	}
    }


    //clear gets rid of everything, all saved points and everything
    public void clear() {
	graphs = new ArrayList<String>();
	refresh();
    }

     //takes the input string, checks if it exist already in storage. If it does rewrite over that input, if not, add it to storage
    public void store(String eq){
	String var = eq.substring(eq.indexOf("[x]")-1,eq.indexOf("[x]"));
	for(int i = 0; i < storage.size(); i++){
	    Boolean truth = var.equals((storage.get(i)).substring(eq.indexOf("[x]")-1,eq.indexOf("[x]")));	   
	    if (truth)
	    {
		storage.remove(i);
	    }
	}
	storage.add(eq);

    }

     //takes the function name, matches it with its corresponding place in storage, and returns the expression that was stored.
     public String function(String input){
	 input = input.replace(" ", "");
	while(input.indexOf("[x]") != -1){
	    String var = findname(input);
	    String replaced = input.substring(input.indexOf("[x]")-1, input.indexOf("[x]")+3);
	    input = input.replace(replaced,findexp(var));
	}
	return input;
    }
    

        //finds the letter name of the function
    public String findname(String input){
	 String fname = input.substring(input.indexOf("[x]")-1,input.indexOf("[x]"));
	 return fname;
    }

        //finds the expression of the function with a certain name
    public String findexp(String name){
	String exp = "";
	for(int i = 0; i < storage.size(); i++){
	    if(name.equals(findname(storage.get(i)))){
		String fexp = storage.get(i);
		exp = fexp.substring(fexp.indexOf("=")+1);
		exp = "(" + exp + ")";
		
	    }
	}
	return exp;
    }

    public ArrayList<String> getGraphs() {
	return graphs;
    }    
}
