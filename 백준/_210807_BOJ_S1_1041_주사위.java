import java.util.Scanner;

public class _210807_BOJ_S1_1041_주사위 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextInt();
		long[] arr = new long[6];
		long one = Integer.MAX_VALUE, two = Integer.MAX_VALUE, three = Integer.MAX_VALUE, maxv = 0;
		long result = 0;
		for (int i = 0; i < 6; i++) {
			arr[i] = sc.nextLong();
			one = Math.min(one, arr[i]);
			maxv = Math.max(maxv, arr[i]);
		}
		//두 면의 최솟값
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				if (i == j || i + j == 5)
					continue;
				two = Math.min(two, arr[i] + arr[j]);
			}
		}
		//세 면의 최솟값
		three = Math.min(three, arr[0] + arr[1] + arr[2]);
		three = Math.min(three, arr[0] + arr[1] + arr[3]);
		three = Math.min(three, arr[0] + arr[3] + arr[4]);
		three = Math.min(three, arr[0] + arr[2] + arr[4]);
		three = Math.min(three, arr[5] + arr[1] + arr[3]);
		three = Math.min(three, arr[5] + arr[1] + arr[2]);
		three = Math.min(three, arr[5] + arr[2] + arr[4]);
		three = Math.min(three, arr[5] + arr[3] + arr[4]);
		// N이 1이면, 5개의 면
		if (N == 1) {
			for (long num : arr)
				result += num;
			System.out.println(result - maxv);
		} else {
			// 한 면
			if (N - 2 > 0)
				result += ((N - 2) * (N - 2) + 4 * (N - 2) * (N - 1)) * one;
			// 두 면
			result += (4 * (N - 2) + 4 * (N - 1)) * two;
			// 세 면
			result += 4 * three;
			System.out.println(result);
		}
	}
}
