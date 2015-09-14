package math;

public class ReverseInteger {

	int reverse(int num) {
		boolean neg = num < 0;
		int result = 0;
		if(neg) {
			num = -num;
		}
		while(num > 0) {
			int lastDigit = num % 10;
			result = 10 * result + lastDigit;
			num = num / 10;
		}
		return neg ? - result : result;
	}
}
