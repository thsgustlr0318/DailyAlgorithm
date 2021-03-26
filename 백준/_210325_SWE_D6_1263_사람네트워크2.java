import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210325_SWE_D6_1263_사람네트워크2 {
	static int INF = 100000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && arr[i][j] == 0)
						arr[i][j] = INF;
				}

			for (int mid = 0; mid < N; mid++) {
				for (int start = 0; start < N; start++) {
					for (int end = 0; end < N; end++) {
						arr[start][end] = Math.min(arr[start][end], arr[start][mid] + arr[mid][end]);
					}
				}
			}
			int res = INF;
			for (int i = 0; i < N; i++) {
				int cnt = 0;
				for (int j = 0; j < N; j++)
					cnt += arr[i][j];
				res = Math.min(res, cnt);
			}
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}
}
