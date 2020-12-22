package basics;

public class FibonacciNumber {

	public static int getFibonacciNumber(int num) {
		if (num < 2) {
			return num;
		}
		int result = 0;
		int[] acc = new int[] {0, 1};

		for (int i = 2; i <= num; i++) {
			result = acc[0] + acc[1];

			if (i % 2 == 0) {
				acc[0] = result;
			} else {
				acc[1] = result;
			}
		}

		return result;
	}

	public static void main(String[] args) {
//		for (int i = 1; i <= 15; i++) {
//			System.out.print(getFibonacciNumber(i) + " ");
//		}
		int a = ((-2 % 3) + 3) % 3;
		System.out.println(a);
	}

}
