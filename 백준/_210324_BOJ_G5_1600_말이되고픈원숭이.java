import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210324_BOJ_G5_1600_말이되고픈원숭이 {
	static int K, W, H, res = -1;
	static int[][] map;
	static boolean[][][] visited;
	static int moveDir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int moveHorse[][] = { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		Queue<info> q = new LinkedList<>();
		visited[0][0][K] = true;
		q.add(new info(0, 0, K, 0));

		while (!q.isEmpty()) {
			info cur = q.poll();
			int y = cur.y, x = cur.x;
			if (cur.y == H - 1 && cur.x == W - 1) {
				res = cur.cnt;
				break;
			}
			if (cur.horse > 0) {
				for (int dir = 0; dir < 8; dir++) {
					int ny = y + moveHorse[dir][0];
					int nx = x + moveHorse[dir][1];
					if (ny < 0 || nx < 0 || ny >= H || nx >= W || visited[ny][nx][cur.horse - 1] || map[ny][nx] == 1)
						continue;
					visited[ny][nx][cur.horse - 1] = true;
					q.add(new info(ny, nx, cur.horse - 1, cur.cnt + 1));
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				int ny = y + moveDir[dir][0];
				int nx = x + moveDir[dir][1];
				if (ny < 0 || nx < 0 || ny >= H || nx >= W || visited[ny][nx][cur.horse] || map[ny][nx] == 1)
					continue;
				visited[ny][nx][cur.horse] = true;
				q.add(new info(ny, nx, cur.horse, cur.cnt + 1));
			}
		}
		System.out.println(res);
	}

	private static class info {
		int y, x, horse, cnt;

		info(int y, int x, int horse, int cnt) {
			this.y = y;
			this.x = x;
			this.horse = horse;
			this.cnt = cnt;
		}
	}
}
