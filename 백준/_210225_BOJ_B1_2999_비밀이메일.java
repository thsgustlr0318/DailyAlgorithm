import java.util.Scanner;

public class _210225_BOJ_B1_2999_비밀이메일 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		int C, R = 1, N = str.length();
		for (int x = 1; x < N; x++) {
			if (N % x == 0) {
				int y = N / x;
				if (y <= x) {
					R = Math.max(R, y);
				}
			}
		}
		C = N / R;
		char[][] arr = new char[R][C];
		int idx = 0;
		for (int x = 0; x < C; x++) {
			for (int y = 0; y < R; y++) {
				arr[y][x] = str.charAt(idx++);
			}
		}
		for (int y = 0; y < R; y++)
			for (int x = 0; x < C; x++)
				sb.append(arr[y][x]);
		System.out.println(sb);
	}
}
