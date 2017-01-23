public class MathString {
    
    //global variable saves known number characters
    private static final String numbers = "1234567890.~";

    //converts a doubleString such as "-3.0" to the "~" standard
    public static String negativeNotate(String negNum){
	if(negNum.substring(0,1).equals( "-"))
	    return "~" + negNum.substring(1);
	else
	    return negNum;
    }

    //takes a positive or negative number in string form and returns its double value.
    public static double notateToDouble(String negNum) {
	if (negNum.charAt(0) == '~')
	    return -1*Double.parseDouble(negNum.substring(1));
	else
	    return Double.parseDouble(negNum);
    }


    //performs a simple addition of a string (Eg: "5+4") and returns string representation of answer.
    public static String simpleAdd(String exp){
	double val1;
	double val2;
	int index = exp.indexOf("+");
	String arg1 = (exp.substring(index + 1));
	String arg2 = (exp.substring(0,index));
	//System.out.println(arg1);
	//System.out.println(arg2);
	val1 = notateToDouble(arg1);
	val2 = notateToDouble(arg2);
	return negativeNotate("" + (val1 + val2));	
    }

    //simpleAdd but with SUBTRACTION!!!
    public static String simpleSubtract(String exp){
	double val1;
	double val2;
	int index = exp.indexOf("-");
	String arg1 = (exp.substring(index + 1));
	String arg2 = (exp.substring(0,index));
	//System.out.println(arg1);
	//System.out.println(arg2);
	val1 = notateToDouble(arg1);
	val2 = notateToDouble(arg2);
	return negativeNotate("" + (val2 - val1));
    }

    //simpleAdd but with MULTIPLICATION!!!
    public static String simpleMultiply(String exp){
	double val1;
	double val2;
	int index = exp.indexOf("*");
	String arg1 = (exp.substring(index + 1));
	String arg2 = (exp.substring(0,index));
	//System.out.println(arg1);
	//System.out.println(arg2);
	val1 = notateToDouble(arg1);
	val2 = notateToDouble(arg2);
	return negativeNotate("" + (val1 * val2));
    }
    
    //simpleAdd but with DIVISION!!!
    public static String simpleDivide(String exp){
	double val1;
	double val2;
	int index = exp.indexOf("/");
	String arg1 = (exp.substring(index + 1));
	String arg2 = (exp.substring(0,index));
	//System.out.println(arg1);
	//System.out.println(arg2);
	val1 = notateToDouble(arg1);
	val2 = notateToDouble(arg2);
	return negativeNotate("" + (val2 / val1));	
    }

    //simpleAdd but with POWERS!!!
    public static String simplePower(String exp){
	double val1;
	double val2;
	int index = exp.indexOf("^");
	String arg1 = (exp.substring(index + 1));
	String arg2 = (exp.substring(0,index));
	//System.out.println(arg1);
	//System.out.println(arg2);
	val1 = notateToDouble(arg1);
	val2 = notateToDouble(arg2);
	return negativeNotate("" + Math.pow(val2,val1));
    }
    
    //Performs any add/subtract operations from left to right of a string
    public static String addLtoR(String exp){
	if(exp.indexOf("+") != -1 || exp.indexOf("-") != -1){
	    int opIndex = Math.min(exp.indexOf("+"),exp.indexOf("-"));
	    if (opIndex == -1)
		opIndex = Math.max(exp.indexOf("+"),exp.indexOf("-"));
	    //System.out.println(opIndex);
	    int start = 0;
	    int end = 0;

	    //Finds end index of simple string
	    int dotsEnd = 0;
	    for(int x = opIndex + 1; x < exp.length();x++){
		if(numbers.indexOf(exp.substring(x,x+1)) != -1){
		    end = x + 1;
		    //System.out.println(end);
		    if(exp.substring(x,x+1).equals("."))
			dotsEnd ++;
		    if (dotsEnd > 1)
			return "NO MORE DOTS";		    
		}
		else{
		    end = x;
		    break;
		}
	    }

	    //Finds start index of simple string
	    int dotsStart = 0;
	    for(int x = opIndex - 1; x > 0; x--){
		if(numbers.indexOf(exp.substring(x-1,x)) != -1){
		    start = x-1;
		    if(exp.substring(x,x+1).equals("."))
			dotsStart ++;
		    if (dotsStart > 1)
			return "NO MORE DOTS";
		    
		}
		else{
		    start = x;
		    break;
		}
	    }

	    String simpleExp = exp.substring(start,end);
	    // System.out.println("EASY EVAL: " + simpleExp);
	    String before = exp.substring(0, start);
	    //System.out.println("BEFORE: " + before);
	    String after = exp.substring(end);
	    //System.out.println("AFTER: " + after);
	    if (simpleExp.indexOf("+") != -1){
		return addLtoR(before + simpleAdd(simpleExp) + after);
	    }
	    else{
		return addLtoR(before + simpleSubtract(simpleExp) + after);
	    }
	}
	else{
	    return exp;
	}	
    }//end addLtoR


    //addLtoR but with MULTIPLICATION/DIVISION
    public static String multiplyLtoR(String exp){
	if(exp.indexOf("*") != -1 || exp.indexOf("/") != -1){
	    int opIndex = Math.min(exp.indexOf("*"),exp.indexOf("/"));
	    if (opIndex == -1)
		opIndex = Math.max(exp.indexOf("*"),exp.indexOf("/"));
	    //System.out.println(opIndex);
	    int start = 0;
	    int end = 0;

	    //Finds end index of simple string
	    int dotsEnd = 0;
	    for(int x = opIndex + 1; x < exp.length();x++){
		if(numbers.indexOf(exp.substring(x,x+1)) != -1){
		    end = x + 1;
		    //System.out.println(end);
		    if(exp.substring(x,x+1).equals("."))
			dotsEnd ++;
		    if (dotsEnd > 1)
			return "NO MORE DOTS";
		    
		}
		else{
		    end = x;
		    break;
		}
	    }

	    //Finds start index of simple string
	    int dotsStart = 0;
	    for(int x = opIndex - 1; x > 0; x--){
		if(numbers.indexOf(exp.substring(x-1,x)) != -1){
		    start = x-1;
		    if(exp.substring(x,x+1).equals("."))
			dotsStart ++;
		    if (dotsStart > 1)
			return "NO MORE DOTS";
		    
		}
		else{
		    start = x;
		    break;
		}
	    }

	    String simpleExp = exp.substring(start,end);
	    //System.out.println("EASY EVAL: " + simpleExp);
	    String before = exp.substring(0, start);
	    //System.out.println("BEFORE: " + before);
	    String after = exp.substring(end);
	    //System.out.println("AFTER: " + after);
	    if (simpleExp.indexOf("*") != -1){
		return multiplyLtoR(before + simpleMultiply(simpleExp) + after);
	    }
	    else{
		return multiplyLtoR(before + simpleDivide(simpleExp) + after);
	    }
	}
	else{
	    return exp;
	}
    }//end multiplyLtoR

    //addLtoR but with POWERS!!!
    public static String powerLtoR(String exp){
	if(exp.indexOf("^") != -1){
	    int opIndex = exp.indexOf("^");

	    int start = 0;
	    int end = 0;

	    //Finds end index of simple string
	    int dotsEnd = 0;
	    for(int x = opIndex + 1; x < exp.length();x++){
		if(numbers.indexOf(exp.substring(x,x+1)) != -1){
		    end = x + 1;
		    // System.out.println(end);
		    if(exp.substring(x,x+1).equals("."))
			dotsEnd ++;
		    if (dotsEnd > 1)
			return "NO MORE DOTS";
		    
		}
		else{
		    end = x;
		    break;
		}
	    }

	    //Finds start index of simple string
	    int dotsStart = 0;
	    for(int x = opIndex - 1; x > 0; x--){
		if(numbers.indexOf(exp.substring(x-1,x)) != -1){
		    start = x-1;
		    if(exp.substring(x,x+1).equals("."))
			dotsStart ++;
		    if (dotsStart > 1)
			return "NO MORE DOTS";
		    
		}
		else{
		    start = x;
		    break;
		}
	    }

	    String simpleExp = exp.substring(start,end);
	    //System.out.println("EASY EVAL: " + simpleExp);
	    String before = exp.substring(0, start);
	    //System.out.println("BEFORE: " + before);
	    String after = exp.substring(end);
	    //System.out.println("AFTER: " + after);
	    
	    return powerLtoR(before + simplePower(simpleExp) + after);
	    
	}
	else{
	    return exp;
	}
    }//end powerLtoR


    //Handles parentheses in a string.
    public static String evaluateParens(String exp) {
	exp = exp.replace("~(","~1(");
	while (exp.indexOf("(") != -1) {
	    //System.out.println("start: " + exp);
	    int openParen = exp.indexOf("(");
	    int nextParen = exp.indexOf("(", openParen+1);
	    int closeParen = exp.indexOf(")");
	    while (nextParen < closeParen && nextParen != -1) {
		openParen = nextParen;
		nextParen = exp.indexOf("(", openParen+1);
	    }
	    String parens = exp.substring(openParen, closeParen+1);
	    //System.out.println(parens);
	    if (openParen != 0 && numbers.indexOf(exp.substring(openParen-1, openParen)) != -1) {
		exp = exp.substring(0, openParen) + "*" + exp.substring(openParen);
		openParen++;
		closeParen++;
	    }
	    if (closeParen != exp.length()-1 && numbers.indexOf(exp.substring(closeParen+1, closeParen+2)) != -1) {
		exp = exp.substring(0, closeParen+1) + "*" + exp.substring(closeParen+1);
	    }
	    String inParens = parens.substring(1, parens.length()-1);
	    exp = exp.substring(0, openParen) + pemdas(inParens) + exp.substring(closeParen + 1);
	    //System.out.println("end: " + exp);
	}
	return exp;
    }

    //runs all the functions
    public static String evaluateFuncs(String exp) {
	exp = evaluateFunc(exp, "abs");
	exp = evaluateFunc(exp, "sin");
	exp = evaluateFunc(exp, "cos");
	exp = evaluateFunc(exp, "tan");
	return exp;
    }

    //takes an expression and runs the proper operation on whatever's inside the brackets of any function inside
    //Eg: sin[], cos[], tan[], abs[]
    public static String evaluateFunc(String exp, String func) {
	exp = exp.replace("~" + func + "[","~1" + func +"[");
	while (exp.indexOf(func + "[") != -1) {
	    int openFunc = exp.indexOf(func + "[");
	    int closeFunc = findClosingBracket(exp, openFunc + func.length());
	    String wholeFunc = exp.substring(openFunc, closeFunc + 1);
	    if (openFunc != 0 && numbers.indexOf(exp.substring(openFunc-1, openFunc)) != -1) {
		exp = exp.substring(0, openFunc) + "*" + exp.substring(openFunc);
		openFunc++;
		closeFunc++;
	    }
	    if (closeFunc != exp.length()-1 && numbers.indexOf(exp.substring(closeFunc+1, closeFunc+2)) != -1) {
		exp = exp.substring(0, closeFunc+1) + "*" + exp.substring(closeFunc+1);
	    }
	    String inFunc = wholeFunc.substring(func.length()+1, wholeFunc.length()-1);
	    String result = pemdas(inFunc);
	    if (func.equals("abs")) {
		if (result.charAt(0) == '~')
		    result = result.substring(1);
	    } else if (func.equals("sin")) {
		result = "" + Math.sin(notateToDouble(result));
	    } else if (func.equals("cos")) {
		result = "" + Math.cos(notateToDouble(result));
	    } else if (func.equals("tan")) {
		result = "" + Math.tan(notateToDouble(result));
	    }	    
	    exp = exp.substring(0, openFunc) + negativeNotate(result) + exp.substring(closeFunc + 1);
	    //System.out.println("end: " + exp);
	}
	return exp;
    }

    //helperMethod for finding closing brackets.
    public static int findClosingBracket(String exp, int open) {
	int close = open;
	int counter = 1;
	while (counter > 0) {
	    close++;
	    char next = exp.charAt(close);
		
	    if (next == '[') {
		counter++;
	    } else if (next == ']') {
		counter--;
	    }
	}
	return close;
    }

    //main expression evaluation function, runs operations in order. 
    public static String pemdas(String exp){
	exp = exp.replace(" ", "");
	//System.out.println(exp);
	exp = evaluateFuncs(exp);
	exp = evaluateParens(exp);
	exp = powerLtoR(exp);
	//System.out.println(exp);
	exp = multiplyLtoR(exp);
	if (exp.indexOf("Infinity") != -1)
	    return "Infinity";
	//System.out.println(exp);
	exp = addLtoR(exp);
	return exp;
    }

    //takes an equation and determines if both sides are equal.
    public static boolean isEqual(String eq){
	int equalsIndex = eq.indexOf("=");
	String lhs = eq.substring(0,equalsIndex);
	String rhs = eq.substring(equalsIndex + 1);
	double side1 = notateToDouble(pemdas(lhs));
	double side2 = notateToDouble(pemdas(rhs));

	return (side1 == side2);
    }
    
    //takes an equation, subtracts one side from another, returns positive, negative, or zero depending on outcome.
    public static int subSides(String eq){
	int equalsIndex = eq.indexOf("=");
	String lhs = eq.substring(0,equalsIndex);
	String rhs = eq.substring(equalsIndex + 1);
	double side1 = notateToDouble(pemdas(lhs));
	double side2 = notateToDouble(pemdas(rhs));
	if(side1 > side2)
	    return -1;
	else if(side1 < side2)
	    return 1;
	else
	    return 0;		
    }

    //substitutes a given variable for a given double value.
    public static String sub(String exp,String var, double val){
	exp = exp.replace(var, "(" + negativeNotate("" + val) + ")");
	//System.out.println(exp);
	return exp;	
    }

    //final variable numbers accessor
    public static String getNumbers() {
	return numbers;
    }

    //runs divZero on an equation
    public static boolean divZero(String eq) {
	String lhs = eq.substring(0, eq.indexOf("="));
	String rhs = eq.substring(eq.indexOf("=")+1);

	return divZeroExp(lhs) || divZeroExp(rhs);
    }

    //determines if an expression CAN divide by zero
    public static boolean divZeroExp(String exp) {
	int index = exp.indexOf("/");
	while (index != -1) {
	    String divisor = exp.substring(index+1, index+2);
	    if (divisor.equals("(")) {
		divisor = exp.substring(index+1, findClosingParen(exp, index+1)+1);
	    } else {
		int end = index + 2;
		while (end < exp.length() && numbers.indexOf(exp.charAt(end)) != -1) {
		    end++;
		}
		divisor = exp.substring(index+1, end);
	    }
	    
	    String result = pemdas(divisor);
	    if (result.equals("Infinity") || notateToDouble(result) == 0)
		return true;
	    
	    index = exp.indexOf("/", index + 1);
	}

	return false;
    }

    
    //finds closing paren: helper method
    public static int findClosingParen(String exp, int open) {
	int close = open;
	int counter = 1;
	while (counter > 0) {
	    close++;
	    char next = exp.charAt(close);
		
	    if (next == '(') {
		counter++;
	    } else if (next == ')') {
		counter--;
	    }
	}
	return close;
    }
}
