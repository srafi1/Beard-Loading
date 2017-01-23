public class Point{
    private double x,y;
    private String myString;
    private String myColor;

    //default constructor, bland
    public Point(){
	this(0, 0);
	myString = " ";
    }

    //overloaded constructor, takes coordinate values
    public Point (double xIn, double yIn){
	x = xIn;
	y = yIn;
	checkAxis();
    }

    //toString prints myString with whatever color the point is.
    public String toString() {
	return myColor + myString + ANSI.RESET;
    }

    //determines if a point is on x or y axis and sets myString accordingly
    public void checkAxis() {
	if(x==0 && y==0)
	    myString="+";
	else if(x==0)
	    myString="|";
	else if(y==0)
	    myString="-";
	myColor = ANSI.WHITE;
    }

    //makes myString blank
    public void reset() {
	myString = " ";
	myColor = ANSI.WHITE;
    }

    //this version of checkAxis does a "close enough" to axis sorta thing
    public void checkAxis(double halfInc) {
	boolean nearX = x < halfInc && x > -1*halfInc;
	boolean nearY = y < halfInc && y > -1*halfInc;

	if (nearX && nearY)
	    myString = "+";
	else if (nearX)
	    myString = "|";
	else if (nearY)
	    myString = "-";
	//	else
	//  myString = " ";
    }

    //substitues its coordinates into a given equation and checks for equality
    public boolean subEq(String eq){
	eq = MathString.sub(eq,"x",x);
	eq = MathString.sub(eq,"y",y);
	return (MathString.isEqual(eq));
    }


    //sets myColor variable using ANSI codes
    public void setColor(int graphNum) {
	graphNum = graphNum % 7;
	
	switch (graphNum) {
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
    }
    
    //closeEnough but with colors
    public void closeEnough_Color(String eq, double halfInc, int graphNum) {
	boolean graphed = myString.equals("*");
	myString = " ";
	closeEnough(eq, halfInc);
	if (myString.equals("*"))
	    setColor(graphNum);
	else if (graphed)
	    myString = "*";
    }

    //long and complicated algorithm that determines whether or not a point is close enough to the curve of a graph
    public void closeEnough(String eq, double halfInc) {
	String center = MathString.sub(eq, "x", x);
	center = MathString.sub(center, "y", y);
	
	boolean divZero = MathString.divZero(center);
	center = MathString.evaluateParens(center);
	int divZeroIndex = center.indexOf("/0.0");
	boolean numNext;
	if (center.length() > divZeroIndex + 4) {
	    String nextChar = center.substring(divZeroIndex+4, divZeroIndex+5);
	    numNext = MathString.getNumbers().indexOf(nextChar) != -1;
	} else
	    numNext = false;	
	
	if (divZero || divZeroIndex != -1 && halfInc > 0.001 && !numNext) {
	    //handle asymptotes
	    checkAxis(halfInc);
	    
	    double origX = x;
	    double origY = y;
	    
	    setCor(origX + halfInc/2, origY + halfInc/2);
	    closeEnough(eq, halfInc/2 - 0.01);
	    if (!myString.equals("*")) {
		setCor(origX + halfInc/2, origY - halfInc/2);
		closeEnough(eq, halfInc/2 - 0.01);
	    }
	    if (!myString.equals("*")) {
		setCor(origX - halfInc/2, origY - halfInc/2);
		closeEnough(eq, halfInc/2 - 0.01);
	    }
	    if (!myString.equals("*")) {
		setCor(origX - halfInc/2, origY + halfInc/2);
		closeEnough(eq, halfInc/2 - 0.01);
	    }
	    setCor(origX, origY);

	    if (!myString.equals("*"))
		checkAxis(halfInc);
	    
	    return;
	}

	boolean positives = false;
	boolean negatives = false;

	try {
	    if (MathString.isEqual(center)) {
		myString = "*";
		return;
	    }
	

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
	} catch(Exception e) {}


	if (positives && negatives) {
	    myString = "*";
	} else {
	    checkAxis(halfInc);
	}
	
    }

    //mutator method for x and y coordinates
    public void setCor(double X, double Y){
	x = X;
	y = Y;
    }

    //accessor method for x and y coordinates
    public double[] getCor() {
	double[] coords = {x, y};
	return coords;
    }

    //moves a point a certain distance
    public void translate(double dx, double dy) {
	x += dx;
	y += dy;
	checkAxis();
    }

}
