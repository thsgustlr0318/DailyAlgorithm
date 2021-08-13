import java.math.BigInteger;
import java.util.Scanner;

public class _210813_BOJ_B5_2338_긴자리_계산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger A = sc.nextBigInteger();
		BigInteger B = sc.nextBigInteger();
		System.out.println(A.add(B));
		System.out.println(A.subtract(B));
		System.out.println(A.multiply(B));
		sc.close();
	}
}
