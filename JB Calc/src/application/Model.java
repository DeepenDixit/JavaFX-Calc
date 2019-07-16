package application;

public class Model {
	public static double calculate(double var1,double var2,String operator)
	{
		switch (operator) {
		case "+":
			return var1+var2;
		case "-":
			return var1-var2;
		case "*":
			return var1*var2;
		case "/":
			if(var2!=0)
				return var1/var2;
			else
				return -1;
		default:
			break;
		}
		return 0;
	}
}