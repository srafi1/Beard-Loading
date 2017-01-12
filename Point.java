public class Point{
    public double x,y;
    public String myString;

    public Point(){
	this(0, 0);
	myString = " ";
    }

    public Point (double xIn, double yIn){
	x = xIn;
	y = yIn;
	checkAxis();
    }

    public String toString(){
	return myString;
    }

    public void checkAxis(){
	if(x==0 && y==0)
	    myString="+";
	else if(x==0)
	    myString="|";
	else if(y==0)
	    myString="-";
	else
	    myString=" ";
    }

    public void subEq(String eq){
	eq = MathString.sub(eq,"x",x);
	eq = MathString.sub(eq,"y",y);
	if (MathString.isEqual(eq))
	    myString = "*";
	else{
	    myString=" ";
	    checkAxis();
	}
    }

    public void setCor(double X, double Y){
	x = X;
	y = Y;
	checkAxis();
    }

    public double[] getCor() {
	double[] coords = {x, y};
	return coords;
    }

    public void scale(double scale) {
	x *= scale;
	y *= scale;
	checkAxis();
    }

    public void translate(double dx, double dy) {
	x += dx;
	y += dy;
	checkAxis();
    }

}
