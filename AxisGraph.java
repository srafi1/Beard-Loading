public class AxisGraph  {
    private Point[][] plane;

    public AxisGraph() {
	plane = new Point[21][21];
	
	for (int y = 0; y < plane.length; y++)
	    for (int x = 0; x < plane[0].length; x++)
		plane[y][x] = new Point(x-10, y-10);
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
    
    public static void main(String[] args) {
	AxisGraph a = new AxisGraph();
	System.out.println(a);

	a.centerAt(5, 1);
	System.out.println(a);
    }
    
}
