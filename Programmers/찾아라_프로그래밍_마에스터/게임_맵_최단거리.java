package 찾아라_프로그래밍_마에스터;

import java.util.LinkedList;
import java.util.Queue;

public class 게임_맵_최단거리 {
	static int[][] moveDir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public int solution(int[][] maps) {
		int answer = -1;
		int n = maps.length, m = maps[0].length;
		Queue<Integer> q = new LinkedList<>();
		int[][] check = new int[n][m];
		check[0][0] = 1;
		q.add(0);
		q.add(0);
		while (!q.isEmpty()) {
			int y = q.poll();
			int x = q.poll();

			if (y == n - 1 && x == m - 1) {
				answer = check[y][x];
				break;
			}
			for (int dir = 0; dir < 4; dir++) {
				int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m || maps[ny][nx] == 0 || check[ny][nx] > 0)
					continue;
				check[ny][nx] = check[y][x] + 1;
				q.add(ny);
				q.add(nx);
			}
		}
		return answer;
	}

	public static void main(String[] args) {

	}
}
