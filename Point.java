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

    public void closeEnough(String eq,double halfInc){
	boolean positives = false;
	boolean negatives = false;
	try{
	    String eq1 = MathString.sub(eq,"x",x + halfInc);
	    //System.out.println(eq1);
	    eq1= MathString.sub(eq1,"y",y + halfInc);
	    //System.out.print(MathString.subSides(eq1));

	    if(MathString.subSides(eq1) == -1)
		negatives = true;
	    else if(MathString.subSides(eq1) == 1)
		positives = true;

	
	    String eq2 = MathString.sub(eq,"x",x - halfInc);
	    eq2 = MathString.sub(eq2,"y",y - halfInc);
	    //System.out.print(MathString.subSides(eq2));
	    if(MathString.subSides(eq2) == -1)
		negatives = true;
	    else if(MathString.subSides(eq2) == 1)
		positives = true;
	   
	    String eq3 = MathString.sub(eq,"x",x + halfInc);
	    eq3 = MathString.sub(eq3,"y",y - halfInc);
	    //System.out.print(MathString.subSides(eq3));
	    if(MathString.subSides(eq3) == -1)
		negatives = true;
	    else if(MathString.subSides(eq3) == 1)
		positives = true;	

	    String eq4 = MathString.sub(eq,"x",x - halfInc);
	    eq4 = MathString.sub(eq4,"y",y + halfInc);
	    //System.out.print(MathString.subSides(eq4) + "\n");
	    if(MathString.subSides(eq4) == -1)
		negatives = true;
	    else if(MathString.subSides(eq4) == 1)
		positives = true;
	}
	catch (Exception e) {}
	checkAxis();
	if(positives && negatives)
	    myString = "*";
	else{
	    myString = " ";
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
    /*
      public void scale(double scale) {
      x *= scale;
      y *= scale;
      checkAxis();
      }
    */
    public void translate(double dx, double dy) {
	x += dx;
	y += dy;
	checkAxis();
    }

}
