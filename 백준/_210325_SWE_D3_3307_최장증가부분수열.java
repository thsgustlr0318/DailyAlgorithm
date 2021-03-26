import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210325_SWE_D3_3307_최장증가부분수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N + 1];
			int[] dp = new int[N + 1];
			for (int i = 1; i <= N; i++)
				dp[i] = 1;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			int maxLen = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j < i; j++) {
					if (arr[i] > arr[j])
						dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				maxLen = Math.max(maxLen, dp[i]);
			}
			sb.append("#" + tc + " " + maxLen + "\n");
		}
		System.out.println(sb);
	}
}
