

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210225_SWE_디저트카페 {
	static int T, N, res;
	static int moveDir[][] = { { 1, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 } };
	static int[][] map;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			res = -1;
			map = new int[N][N];
			check = new boolean[101];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			for (int y = 0; y < N - 2; y++) {
				for (int x = 1; x < N - 1; x++) {
					check[map[y][x]] = true;
					dfs(y, x, y, x, 1, 0);
					check[map[y][x]] = false;
				}
			}
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int sy, int sx, int y, int x, int cnt, int idx) {
		if (idx == 4)
			return;
		int ny = y + moveDir[idx][0], nx = x + moveDir[idx][1];
		if (ny == sy && nx == sx && idx == 3) {
			res = Math.max(res, cnt);
			return;
		}
		if (ny < 0 || nx < 0 || ny >= N || nx >= N || check[map[ny][nx]])
			return;
		check[map[ny][nx]] = true;
		//현재 경로 탐색
		dfs(sy, sx, ny, nx, cnt + 1, idx);
		//방향 꺾어서 탐색
		dfs(sy, sx, ny, nx, cnt + 1, idx + 1);
		check[map[ny][nx]] = false;
	}
}
