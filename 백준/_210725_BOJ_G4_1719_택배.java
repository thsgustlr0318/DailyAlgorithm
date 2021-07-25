import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _210725_BOJ_G4_1719_택배 {
	static int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] distance = new int[n + 1][n + 1];
		int[][] path = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(distance[i], INF);
			distance[i][i] = 0;
		}
		for (int index = 0; index < m; index++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int depth = Integer.parseInt(st.nextToken());
			distance[a][b] = distance[b][a] = depth;
			// a에서 b로 가기 위해 처음에 b를 들려야함
			path[a][b] = b;
			// b에서 a로 가기 위해 처음에 a를 들려야함
			path[b][a] = a;
		}
		// 플로이드-와샬
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
						// i에서 j로 가는 최단 경로에 k를 거쳐야 함
						path[i][j] = path[i][k];
					}
				}
			}
		}
		for (int start = 1; start <= n; start++) {
			for (int end = 1; end <= n; end++) {
				if (start == end)
					sb.append("- ");
				else
					sb.append(path[start][end] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}