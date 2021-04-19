import java.util.LinkedList;
import java.util.Queue;

public class _210419_경주로건설 {
	//우 하 좌 상
	static int[][] moveDir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static class info {
		int y, x, dir, cost;

		info(int y, int x, int dir, int cost) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cost = cost;
		}
	}

	public static int solution(int[][] board) {
		int N = board.length;
		int[][] visited = new int[N][N];
		Queue<info> q = new LinkedList<>();
		q.add(new info(0, 0, 0, 100));
		q.add(new info(0, 0, 1, 100));
		visited[0][0] = 100;

		while (!q.isEmpty()) {
			info cur = q.poll();
			int y = cur.y, x = cur.x, dir = cur.dir, cost = cur.cost;

			for (int i = 0; i < 4; i++) {
				int ny = y + moveDir[i][0], nx = x + moveDir[i][1];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || board[ny][nx] == 1)
					continue;
				int nextCost = cost + (dir == i ? 100 : 600);
				if (visited[ny][nx] != 0 && visited[ny][nx] < nextCost)
					continue;
				visited[ny][nx] = nextCost;
				q.add(new info(ny, nx, i, nextCost));
			}
		}
		return visited[N - 1][N - 1] - 100;
	}

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 0 }, { 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } };
		System.out.println(solution(board));
	}
}
