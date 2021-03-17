
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class _210316_BOJ_P5_1086_박성원 {
//	static int n, k;
//	static int[] num, len, ten;
//	static String[] arr;
//	static long[][] dp;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		n = Integer.parseInt(br.readLine());
//		arr = new String[n];
//		num = new int[n];
//		ten = new int[51];
//		len = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = br.readLine();
//			len[i] = arr[i].length();
//		}
//		k = Integer.parseInt(br.readLine());
//		for (int i = 0; i < n; i++) {
//			String cur = arr[i];
//			int temp = cur.charAt(0) - '0';
//			for (int idx = 1; idx < cur.length(); idx++) {
//				temp *= 10;
//				temp += (cur.charAt(idx) - '0');
//				temp %= k;
//			}
//			num[i] = temp % k;
//		}
//		ten[0] = 1;
//		for (int i = 1; i <= 50; i++) {
//			ten[i] = (ten[i - 1] * 10) % k;
//		}
//
//		//분모
//		long motherNum = 1;
//		for (int i = 1; i <= n; i++)
//			motherNum *= i;
//
//		//분자
//		dp = new long[1 << n][k];
//		dp[0][0] = 1;
//		for (int i = 0; i < (1 << n); i++) {
//			for (int j = 0; j < k; j++) {
//				for (int idx = 0; idx < n; idx++) {
//					if ((i & (1 << idx)) == 0) {
//						int nMod = (j * ten[len[idx]] + num[idx]) % k;
//						dp[i | (1 << idx)][nMod] += dp[i][j];
//					}
//				}
//			}
//		}
//		long childNum = dp[(1 << n) - 1][0];
//		long gcd = getGCD(motherNum, childNum);
//		motherNum /= gcd;
//		childNum /= gcd;
//		System.out.println(childNum + "/" + motherNum);
//	}
//
//	private static long getGCD(long a, long b) {
//		if (b == 0)
//			return a;
//		return getGCD(b, a % b);
//	}
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210316_BOJ_P5_1086_박성원 {
	static int n, k;
	static int[] num, len, ten;
	static String[] arr;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new String[n];
		num = new int[n];
		ten = new int[51];
		len = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
			len[i] = arr[i].length();
		}
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String cur = arr[i];
			int temp = cur.charAt(0) - '0';
			for (int idx = 1; idx < cur.length(); idx++) {
				temp *= 10;
				temp += (cur.charAt(idx) - '0');
				temp %= k;
			}
			num[i] = temp % k;
		}
		ten[0] = 1;
		for (int i = 1; i <= 50; i++) {
			ten[i] = (ten[i - 1] * 10) % k;
		}

		//분모
		long motherNum = 1;
		for (int i = 1; i <= n; i++)
			motherNum *= i;

		dp = new long[1 << n][k];
		for (int i = 0; i < (1 << n); i++)
			for (int j = 0; j < k; j++)
				dp[i][j] = -1;

		//분자
		long childNum = dfs(0, 0, 0);
		long gcd = gcd(childNum, motherNum);
		motherNum /= gcd;
		childNum /= gcd;
		System.out.println(childNum + "/" + motherNum);
	}

	private static long dfs(int cnt, int visit, int cur) {
		if (cnt == n) {
			if (cur % k == 0)
				return 1;
			else
				return 0;
		}

		long ret = dp[visit][cur];
		if (ret != -1)
			return ret;
		dp[visit][cur] = 0;
		for (int i = 0; i < n; i++) {
			if ((visit & (1 << i)) == 0) {
				int nMod = (cur * ten[len[i]] + num[i]) % k;
				dp[visit][cur] += dfs(cnt + 1, visit | (1 << i), nMod);
			}
		}
		return dp[visit][cur];
	}

	private static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}
