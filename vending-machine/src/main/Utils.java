package main;

public class Utils {
	public static int getDollars(double money) {
		String str = String.valueOf(money);
		return Integer.valueOf(str.split(".")[0]);
	}
	
	public static int getCents(double money) {
		String str = String.valueOf(money);
		return Integer.valueOf(str.split(".")[1]);
	}
}
