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
	highest = highVal;
    }

    public void graph(String eq){
	double increment =  highest / ((plane.length - 1) / 2.0);
	for(Point[] row: plane)
	    for(Point p: row)
		p.closeEnough(eq,increment / 2.0);
	graphs.add(eq);
    }

    public void reGraphAll(){
	for(int x = 0; x < graphs.size();x++)
	    graph(graphs.get(x));
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
	a.graph("y=x^2");
	System.out.println(a);

    }
    
}
