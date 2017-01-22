import java.util.ArrayList;

public class AxisGraph  {
    private Point[][] plane;
    private double highest;
    public ArrayList<String> graphs = new ArrayList<String>();
    public ArrayList<String> storage = new ArrayList<String>();

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

    //
    public String whitespace(String input){
	while ( input.indexOf(" ") != -1){
	    String part1 = input.substring(0,input.indexOf(" "));

	    String part2 = input.substring(input.indexOf(" ")+1);

	    input = part1 + part2;
	}
	return input;
    }

    //
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

    //
     public String function(String input){
	 input = whitespace(input);
	while(input.indexOf("[x]") != -1){
	    String var = findname(input);
	    String replaced = input.substring(input.indexOf("[x]")-1, input.indexOf("[x]")+3);
	    input = input.replace(replaced,findexp(var));
	}
	return input;
    }
    

    //
    public String findname(String input){
	 String fname = input.substring(input.indexOf("[x]")-1,input.indexOf("[x]"));
	 return fname;
    }

    //
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

    
    
    /*
    public void setGraph(double cx, double cy, double scale) {
	for (int y = 0; y < plane.length; y++)
	    for (int x = 0; x < plane[0].length; x++) {
		plane[y][x].scale(scale);
		plane[y][x].translate(cx - 10, cy - 10);
	    }
    }

    public void zoom(double scale) {
	double[] coords = plane[10][10].getCor();
	setGraph(coords[0], coords[1], scale);
    }

    public void centerAt(double cx, double cy) {
	setGraph(cx, cy, 1);
    }
    */

    //main method for testing
    public static void main(String[] args) {
	AxisGraph a = new AxisGraph();
	/*	a.graphAll("y=x^2");
	System.out.println(a);
	a.graphAll("y=x");
	System.out.println(a);
	System.out.println(a.graphs);*/
	a.storage.add("f[x] = x^2");
	a.storage.add("g[x] = x + 1");
	//	System.out.println(a.findexp("x"));
	System.out.println(a.function("y = 3f[x]"));
    }
    
}
