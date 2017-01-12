public class Point{
    public double x,y;
    public String myString;

    public Point(){
	x=0;
	y=0;
	myString="O";
	checkAxis();
    }

    public Point (double xIn, double yIn){
	this();
	x=xIn;
	y=yIn;
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
	    myString="O";
    }

    public void subEq(String eq){
	eq = MathString.sub(eq,"x",x);
	eq = MathString.sub(eq,"y",y);
	if (MathString.isEqual(eq))
	    myString = "*";
	else{
	    myString="O";
	    checkAxis();
	}
    }

    public void setCor(double X, double Y){
	x=X;
	y=Y;
	checkAxis();
    }

}
