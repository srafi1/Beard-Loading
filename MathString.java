public class MathString {

    private static final String numbers = "1234567890.~";

    public static String negativeNotate(String negNum){
	if(negNum.substring(0,1).equals( "-"))
	    return "~" + negNum.substring(1);
	else
	    return negNum;
    }

    public static double notateToDouble(String negNum){
	if (negNum.substring(0,1).equals("~")){
	    return -1 * Double.parseDouble(negNum.substring(1));
	}
	else{
	    return Double.parseDouble(negNum);
	}
    }

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
    
    public static String evaluateParens(String exp) {
	while (exp.indexOf("(") != -1) {
	    //System.out.println(exp);
	    int openParen = exp.indexOf("(");
	    int nextParen = exp.substring(openParen+1).indexOf("(");
	    int closeParen = exp.indexOf(")");
	    while (nextParen < closeParen && nextParen != -1) {
		openParen = nextParen;
		nextParen = exp.indexOf("(", openParen+1);
	    }
	    String parens = exp.substring(openParen, closeParen+1);
	    if (openParen != 0 && numbers.indexOf(exp.substring(openParen-1, openParen)) != -1) {
		exp = exp.replace(parens, "*"+parens);
		openParen++;
		closeParen++;
	    }
	    if (closeParen != exp.length()-1 && numbers.indexOf(exp.substring(closeParen+1, closeParen+2)) != -1) {
		exp = exp.replace(parens, parens+"*");
	    }
	    String inParens = parens.substring(1, parens.length()-1);
	    exp = exp.replace(parens, pemdas(inParens));
	}
	return exp;
    }

    public static String pemdas(String exp){
	exp = exp.replace(" ", "");
	//System.out.println(exp);
	exp = evaluateParens(exp);
	exp = powerLtoR(exp);
	//System.out.println(exp);
	exp = multiplyLtoR(exp);
	//System.out.println(exp);
	exp = addLtoR(exp);
	return exp;
    }

    public static boolean isEqual(String eq){
	int test = 1;
	int equalsIndex = eq.indexOf("=");
	String lhs = eq.substring(0,equalsIndex);
	String rhs = eq.substring(equalsIndex + 1);
	double side1 = notateToDouble(pemdas(lhs));
	double side2 = notateToDouble(pemdas(rhs));

	return (side1 == side2);
    }

    public static String sub(String exp,String var, double val){
	exp = exp.replace(var, "(" + negativeNotate("" + val) + ")");
	return exp;	
    }




    public static void main (String[] args){
	/*
	  System.out.println(simpleAdd("6.18+~7.27"));
	  System.out.println(simpleSubtract("6.18-7.28"));
	  System.out.println(simpleMultiply("6.18*~7.28"));
	  System.out.println(simpleDivide("6.18/~7.28"));
	  System.out.println(simplePower("6^2"));
	  System.out.println(addLtoR("3+3+4"));
	  System.out.println(multiplyLtoR("2*3*3*5/3"));
	  System.out.println(powerLtoR("2^2+4^2"));
	  System.out.println(pemdas("3^2+3*2-6/2"));
	
	  System.out.println(pemdas("3^2 + 3*2      - 6 / ~2"));
	  System.out.println(pemdas("3^2 - 4*3 + ~6/3"));
       

	  System.out.println(notateToDouble("~3.0"));
	*/
	//System.out.println(pemdas(sub("x^2 + 3x + 7","x",2)));
	//System.out.println(pemdas("(2)^2 + 3(2) + 7"));
	
	
	  for(String s : args){
	  System.out.println(MathString.pemdas(s));
	  }
	
	
    }

}
