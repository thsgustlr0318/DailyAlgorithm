import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _210326_카드짝맞추기 {
	static boolean[] character;
	static ArrayList<pos> cards[];
	static int answer = Integer.MAX_VALUE;
	static int[][] moveDir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) {
		int[][] board = { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } };
		int r = 1;
		int c = 0;
		System.out.println(solution(board, r, c));
	}

	private static class pos {
		int y, x;

		pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static int solution(int[][] board, int r, int c) {
		character = new boolean[7];
		cards = new ArrayList[7];
		int cnt = 0;
		for (int i = 1; i <= 6; i++)
			cards[i] = new ArrayList<>();
		int row = board.length;
		int col = board[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				if (board[i][j] != 0) {
					int cur = board[i][j];
					cnt++;
					character[cur] = true;
					cards[cur].add(new pos(i, j));
				}
		}
		cnt /= 2;
		dfs(0, cnt, new boolean[7], new int[cnt], board, r, c);
		return answer;
	}

	private static void dfs(int cnt, int targetNum, boolean[] visited, int[] selected, int[][] board, int r, int c) {
		if (cnt == targetNum) {
			selectCard(new int[targetNum * 2], selected, 0, targetNum, board, r, c);
			return;
		}
		for (int i = 1; i <= 6; i++) {
			if (!character[i] || visited[i])
				continue;
			visited[i] = true;
			selected[cnt] = i;
			dfs(cnt + 1, targetNum, visited, selected, board, r, c);
			visited[i] = false;
		}
	}

	private static void selectCard(int[] order, int[] selected, int cnt, int targetNum, int[][] board, int r, int c) {
		if (cnt == targetNum * 2) {
			ArrayList<pos> orders = new ArrayList<>();
			for (int i = 0, j = 0; i < selected.length; i++) {
				orders.add(cards[selected[i]].get(order[j++]));
				orders.add(cards[selected[i]].get(order[j++]));
			}
			playGame(orders, board, r, c);
			return;
		}
		order[cnt] = 0;
		order[cnt + 1] = 1;
		selectCard(order, selected, cnt + 2, targetNum, board, r, c);
		order[cnt] = 1;
		order[cnt + 1] = 0;
		selectCard(order, selected, cnt + 2, targetNum, board, r, c);
	}

	private static void playGame(ArrayList<pos> orders, int[][] board, int r, int c) {
		int result = 0;
		int[][] temp = new int[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				temp[i][j] = board[i][j];

		int sy = r, sx = c;
		for (int i = 0; i < orders.size(); i++) {
			Queue<pos> q = new LinkedList<>();

			int ey = orders.get(i).y, ex = orders.get(i).x;
			q.add(new pos(sy, sx));
			int[][] visited = new int[4][4];
			visited[sy][sx] = 1;
			while (!q.isEmpty()) {
				int y = q.peek().y, x = q.peek().x;
				q.poll();
				if (y == ey && x == ex) {
					result += visited[y][x] - 1;
					board[ey][ex] = 0;
					break;
				}

				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= 4 | nx >= 4 || visited[ny][nx] > 0)
						continue;
					visited[ny][nx] = visited[y][x] + 1;
					q.add(new pos(ny, nx));
				}

				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= 4 | nx >= 4)
						continue;
					while (true) {
						if ((ny == 0 && nx == 0) || (ny == 0 && nx == 3) || (ny == 3 && nx == 0) || (ny == 3 && nx == 3))
							break;
						if (ny < 0 || nx < 0 || ny >= 4 | nx >= 4) {
							ny -= moveDir[dir][0];
							nx -= moveDir[dir][1];
							break;
						}
						if (board[ny][nx] > 0)
							break;
						ny += moveDir[dir][0];
						nx += moveDir[dir][1];
					}
					if (ny < 0 || nx < 0 || ny >= 4 | nx >= 4 || visited[ny][nx] > 0)
						continue;
					visited[ny][nx] = visited[y][x] + 1;
					q.add(new pos(ny, nx));
				}
			}
			sy = ey;
			sx = ex;
		}
		result += orders.size();
		answer = Math.min(answer, result);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				board[i][j] = temp[i][j];
	}
}
