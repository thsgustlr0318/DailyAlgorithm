import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210228_BOJ_G4_1520_내리막길 {
	static int N, M, res = 0;
	static int[][] map, dp;
	static int[][] moveDir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		dfs(0, 0);
		System.out.println(dp[0][0]);
	}

	private static int dfs(int y, int x) {
		if (y == N - 1 && x == M - 1) {
			return 1;
		}
		if (dp[y][x] == -1) {
			dp[y][x] = 0;
			for (int i = 0; i < 4; i++) {
				int ny = y + moveDir[i][0], nx = x + moveDir[i][1];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] >= map[y][x])
					continue;
				dp[y][x] += dfs(ny, nx);
			}
		}
		return dp[y][x];
	}
}
