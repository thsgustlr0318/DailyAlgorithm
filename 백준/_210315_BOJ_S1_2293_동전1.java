import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210315_BOJ_S1_2293_동전1 {
	static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + 1];
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i <= n; i++)
			dp[i][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (arr[i] <= j) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - arr[i]];
				} else
					dp[i][j] = dp[i - 1][j];
			}
		}
		System.out.println(dp[n][k]);
	}
}
