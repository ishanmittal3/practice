package sandbox;

import static java.lang.Character.isDigit;
import static java.lang.Double.parseDouble;

/**
 * Write a function that calculates input strings with operators +,-,*,/ 
 * e.g. "5+5*6" should output 35
 */
public class Evaluate {

	public static void main(String[] args) {
		System.out.println(evaluate("5+5*6"));
		System.out.println(evaluate("5+5*6/2"));
		System.out.println(evaluate("5+5*6/2-2"));
	}
	
	public static double evaluate(String exp) {
		exp = evaluate(exp, '*');
		exp = evaluate(exp, '/');
		exp = evaluate(exp, '+');
		exp = evaluate(exp, '-');
		
		return Double.parseDouble(exp);
	}
	
	public static String evaluate(String exp, char operator) {

		while (exp.indexOf(operator) > 0) {
			int posOperator = exp.indexOf(operator);

			int arg1Length = findLeftArgLength (exp, posOperator);
			int arg2Length = findRightArgLength(exp, posOperator);

			double arg1 = parseDouble(exp.substring(posOperator - arg1Length, posOperator));
			double arg2 = parseDouble(exp.substring(posOperator + 1, posOperator + 1 + arg2Length));

			double result = evaluate(arg1, operator, arg2);

			exp = exp.substring(0, posOperator - arg1Length) + String.valueOf(result) + 
				  exp.substring(posOperator + 1 + arg2Length, exp.length());
		}
		return exp;
	}

	private static double evaluate(double arg1, char operator, double arg2) {
		switch(operator) {
		case('*'): 
			return arg1 * arg2;
		case('/'):
			return arg1 / arg2;
		case('+'):
			return arg1 + arg2;
		case('-'):
			return arg1 - arg2;
		default:
			return 0;
		}
	}

	private static int findLeftArgLength(String exp, int posOperator) {
		int pos = posOperator - 1;
		while (pos >= 0 && 
			  (exp.charAt(pos) == '.' || isDigit(exp.charAt(pos)))) {
			pos--;
		}
		return posOperator - (pos + 1);
	}

	private static int findRightArgLength(String exp, int posOperator) {
		int pos = posOperator + 1;
		while (pos < exp.length() && 
			  (exp.charAt(pos) == '.' || isDigit(exp.charAt(pos)))) {
			pos++;
		}
		return pos - 1 - posOperator;
	}
}
