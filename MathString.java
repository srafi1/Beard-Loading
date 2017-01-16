public class MathString {

    private static final String numbers = "1234567890.~";

    public static String negativeNotate(String negNum){
	if(negNum.substring(0,1).equals( "-"))
	    return "~" + negNum.substring(1);
	else
	    return negNum;
    }

    public static double notateToDouble(String negNum){
	int mult = 1;
	
	if (negNum.substring(0,1).equals("~")){
	    negNum = negNum.substring(1);
	    mult = -1;
	}
	
	if (negNum.indexOf("E") != -1) {
	    int eIndex = negNum.indexOf("E");
	    String base = negNum.substring(0, eIndex);
	    String power = negNum.substring(eIndex+1);
	    if (power.equals(""))
		power = "1";
	    String num = base + "*10^" + power;
	    negNum = pemdas(num);
	}

	return mult * Double.parseDouble(negNum);
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

    public static String evaluateAbs(String exp) {
	exp = exp.replace("~abs[","~1abs[");
	while (exp.indexOf("abs[") != -1) {
	    //System.out.println("start: " + exp);
	    int openParen = exp.indexOf("abs[");
	    int nextParen = exp.indexOf("abs[", openParen+1);
	    int closeParen = exp.indexOf("]");
	    while (nextParen < closeParen && nextParen != -1) {
		openParen = nextParen;
		nextParen = exp.indexOf("abs[", openParen+1);
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
	    String inParens = parens.substring(4, parens.length()-1);
	    String result = pemdas(inParens);
	    if (result.indexOf("~") == 0)
		result = result.substring(1);
	    exp = exp.substring(0, openParen) + result + exp.substring(closeParen + 1);
	    //System.out.println("end: " + exp);
	}
	return exp;
    }

    public static String pemdas(String exp){
	exp = exp.replace(" ", "");
	//System.out.println(exp);
	exp = evaluateParens(exp);
	exp = evaluateAbs(exp);
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

 
    public static String sub(String exp,String var, double val){
	exp = exp.replace(var, "(" + negativeNotate("" + val) + ")");
	//System.out.println(exp);
	return exp;	
    }

    public static String getNumbers() {
	return numbers;
    }

    /*   public static String abs(String exp){
	 while (exp.indexOf("abs[") != -1){
	 String exps = exp.substring(1,(exp.length()-1));
	 return exps;
	 }
	 }*/

    /*
      public static String evaluateAbs(String exp) {
      while (exp.indexOf("abs[") != -1) {
      //System.out.println(exp);
      int openAbs = exp.indexOf("abs[");
      int nextAbs = exp.substring(openAbs+4).indexOf("abs[");
      int closeAbs = exp.indexOf("]");
      while (nextAbs < closeAbs && nextAbs != -1) {
      openAbs = nextAbs;
      nextAbs = exp.indexOf("abs[", openAbs+4);
      }
      String abs = exp.substring(openAbs, closeAbs+1);
      if (openAbs != 0 && numbers.indexOf(exp.substring(openAbs-1, openAbs)) != -1) {
      exp = exp.replace(abs, "*"+abs);
      openAbs++;
      closeAbs++;
      }
      if (closeAbs != exp.length()-1 && numbers.indexOf(exp.substring(closeAbs+1, closeAbs+2)) != -1) {
      exp = exp.replace(abs, abs+"*");
      }
      String inAbs = abs.substring(4, abs.length()-1);
      if (inAbs.indexOf("~") > -1){
      inAbs = abs.substring(5, abs.length()-1);
      }
      String evaluated = pemdas(inAbs);
      if(evaluated.substring(0,1).equals("~"))
      evaluated = evaluated.substring(1);
      exp = exp.replace(abs,evaluated);
      }
      return exp;
      }
    */

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
	System.out.println(subSides("1=0"));
	/*
	  for(String s : args){
	  System.out.println(MathString.pemdas(s));
	  }
	*/
	
    }

}
