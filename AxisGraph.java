import java.util.ArrayList;

public class AxisGraph  {
    private Point[][] plane;
    private double highest;
    public ArrayList<String> graphs = new ArrayList<String>();

    public AxisGraph() {
	plane = new Point[41][41];
	
	for (int y = 0; y < plane.length; y++)
	    for (int x = 0; x < plane[0].length; x++)
		plane[y][x] = new Point();
	zoom(10);
    }

    public String toString() {
	String retStr = "";

	for (Point[] row : plane) {
	    for (Point p : row)
		retStr += p;
	    retStr += "\n";
	}

	return retStr;
    }

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

    public void translate(double dx, double dy) {
	for (Point[] row : plane)
	    for (Point p : row)
		p.translate(dx, dy);
	refresh();
    }
    
    public void graph(String eq, int num){
	double increment =  highest / ((plane.length - 1) / 2.0);
	for(Point[] row: plane)
	    for(Point p: row)
		p.closeEnough_Color(eq, increment / 2.0, num);
    }

    public void graphAll(String eq){
	graphs.add(eq);
	for(int x = 0; x < graphs.size();x++)
	    graph(graphs.get(x), x);
    }

    public void refresh(){
	for(Point[] row: plane){
	    for(Point p: row){
		p.reset();
		p.checkAxis();
	    }
	}
    }

    public void clear() {
	graphs = new ArrayList<String>();
	refresh();
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
    public static void main(String[] args) {
	AxisGraph a = new AxisGraph();
	a.graphAll("y=x^2");
	System.out.println(a);
	a.graphAll("y=x");
	System.out.println(a);
	System.out.println(a.graphs);
	

    }
    
}
