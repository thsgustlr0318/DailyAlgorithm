import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210322_BOJ_G4_11404_플로이드 {
	static int n, m;
	static int INF = 1000000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) {
				if (i != j)
					arr[i][j] = INF;
			}
		for (int i = 0; i < m; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			arr[a][b] = Math.min(arr[a][b], c);
		}
		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				for (int end = 1; end <= n; end++) {
					if (arr[start][mid] + arr[mid][end] < arr[start][end]) {
						arr[start][end] = arr[start][mid] + arr[mid][end];
					}
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] == INF)
					sb.append("0 ");
				else
					sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
