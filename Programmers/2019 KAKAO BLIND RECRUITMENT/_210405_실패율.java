import java.util.LinkedList;
import java.util.Queue;

public class _210405_실패율 {
	static int N;
	static int moveDir[][] = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

	//0: 가로방향, 1:세로방향
	static class pos {
		int y1, x1, y2, x2, type, depth;

		pos(int y1, int x1, int y2, int x2, int type, int depth) {
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
			this.type = type;
			this.depth = depth;
		}
	}

	public static int solution(int[][] board) {
		int answer = 0;
		N = board.length;
		boolean[][][] visited = new boolean[N][N][2];
		visited[0][1][0] = true;
		Queue<pos> q = new LinkedList<>();
		q.add(new pos(0, 0, 0, 1, 0, 1));
		while (!q.isEmpty()) {
			pos cur = q.poll();
			if (cur.y2 == N - 1 && cur.x2 == N - 1) {
				answer = cur.depth - 1;
				break;
			}
			//그냥 이동
			for (int dir = 0; dir < 4; dir++) {
				int ny1 = cur.y1 + moveDir[dir][0], nx1 = cur.x1 + moveDir[dir][1];
				int ny2 = cur.y2 + moveDir[dir][0], nx2 = cur.x2 + moveDir[dir][1];
				if (ny1 < 0 || nx1 < 0 || ny2 < 0 || nx2 < 0 || ny1 >= N || nx1 >= N || ny2 >= N || nx2 >= N)
					continue;
				if (ny1 + nx1 > ny2 + nx2) {
					int temp1 = ny1, temp2 = nx1;
					ny1 = ny2;
					nx1 = nx2;
					ny2 = temp1;
					nx2 = temp2;
				}
				if (board[ny2][nx2] == 1 || board[ny1][nx1] == 1 || visited[ny2][nx2][cur.type])
					continue;
				visited[ny2][nx2][cur.type] = true;
				q.add(new pos(ny1, nx1, ny2, nx2, cur.type, cur.depth + 1));
			}
			//회전
			//가로
			if (cur.type == 0) {
				//1
				if (cur.y1 + 1 < N && board[cur.y1 + 1][cur.x1] == 0 && board[cur.y2 + 1][cur.x2] == 0) {
					int ny = cur.y2 + 1, nx = cur.x2;
					if (!visited[ny][nx][1]) {
						visited[ny][nx][1] = true;
						q.add(new pos(cur.y2, cur.x2, ny, nx, 1, cur.depth + 1));
					}
				}
				//2
				if (cur.y1 - 1 >= 0 && board[cur.y1 - 1][cur.x1] == 0 && board[cur.y2 - 1][cur.x2] == 0) {
					int ny = cur.y2 - 1, nx = cur.x2;
					if (!visited[cur.y2][cur.x2][1]) {
						visited[cur.y2][cur.x2][1] = true;
						q.add(new pos(ny, nx, cur.y2, cur.x2, 1, cur.depth + 1));
					}
				}
				//3
				if (cur.y1 + 1 < N && board[cur.y1 + 1][cur.x1] == 0 && board[cur.y2 + 1][cur.x2] == 0) {
					int ny = cur.y1 + 1, nx = cur.x1;
					if (!visited[ny][nx][1]) {
						visited[ny][nx][1] = true;
						q.add(new pos(cur.y1, cur.x1, ny, nx, 1, cur.depth + 1));
					}
				}
				//4
				if (cur.y1 - 1 >= 0 && board[cur.y1 - 1][cur.x1] == 0 && board[cur.y2 - 1][cur.x2] == 0) {
					int ny = cur.y1 - 1, nx = cur.x1;
					if (!visited[cur.y1][cur.x1][1]) {
						visited[cur.y1][cur.x1][1] = true;
						q.add(new pos(ny, nx, cur.y1, cur.x1, 1, cur.depth + 1));
					}
				}
			}
			//세로
			else {
				//1
				if (cur.x2 + 1 < N && board[cur.y1][cur.x1 + 1] == 0 && board[cur.y2][cur.x2 + 1] == 0) {
					int ny = cur.y2, nx = cur.x2 + 1;
					if (!visited[ny][nx][0]) {
						visited[ny][nx][0] = true;
						q.add(new pos(cur.y2, cur.x2, ny, nx, 0, cur.depth + 1));
					}
				}
				//2
				if (cur.x2 + 1 < N && board[cur.y1][cur.x1 + 1] == 0 && board[cur.y2][cur.x2 + 1] == 0) {
					int ny = cur.y1, nx = cur.x1 + 1;
					if (!visited[ny][nx][0]) {
						visited[ny][nx][0] = true;
						q.add(new pos(cur.y1, cur.x1, ny, nx, 0, cur.depth + 1));
					}
				}
				//3
				if (cur.x2 - 1 >= 0 && board[cur.y1][cur.x1 - 1] == 0 && board[cur.y2][cur.x2 - 1] == 0) {
					int ny = cur.y2, nx = cur.x2 - 1;
					if (!visited[cur.y2][cur.x2][0]) {
						visited[cur.y2][cur.x2][0] = true;
						q.add(new pos(ny, nx, cur.y2, cur.x2, 0, cur.depth + 1));
					}
				}
				//4
				if (cur.x2 - 1 >= 0 && board[cur.y1][cur.x1 - 1] == 0 && board[cur.y2][cur.x2 - 1] == 0) {
					int ny = cur.y1, nx = cur.x1 - 1;
					if (!visited[cur.y1][cur.x1][0]) {
						visited[cur.y1][cur.x1][0] = true;
						q.add(new pos(ny, nx, cur.y1, cur.x1, 0, cur.depth + 1));
					}
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } };
		System.out.println(solution(board));
	}
}
