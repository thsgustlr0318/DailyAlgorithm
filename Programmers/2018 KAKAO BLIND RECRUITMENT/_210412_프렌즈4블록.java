import java.util.LinkedList;
import java.util.Queue;

public class _210412_프렌즈4블록 {
	static int[][] moveDir = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } };

	public static int solution(int m, int n, String[] board) {
		int answer = 0;
		int row = m, col = n;
		char[][] map = new char[row][];
		for (int i = 0; i < row; i++)
			map[i] = board[i].toCharArray();

		while (true) {
			boolean check = false;
			Queue<info> q = new LinkedList<>();
			for (int y = 0; y < row - 1; y++) {
				for (int x = 0; x < col - 1; x++) {
					if (canDelete(y, x, map))
						q.add(new info(y, x));
				}
			}
			while (!q.isEmpty()) {
				check = true;
				info cur = q.poll();
				int y = cur.y, x = cur.x;
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (map[ny][nx] != ' ') {
						answer++;
						map[ny][nx] = ' ';
					}
				}
			}
			for (int x = 0; x < col; x++) {
				Queue<Character> block = new LinkedList<>();
				for (int y = row - 1; y >= 0; y--) {
					if (map[y][x] != ' ')
						block.add(map[y][x]);
				}
				for (int y = row - 1; y >= 0; y--) {
					if (block.isEmpty())
						map[y][x] = ' ';
					else
						map[y][x] = block.poll();
				}
			}
			if (!check)
				break;
		}
		return answer;
	}

	private static boolean canDelete(int y, int x, char[][] board) {
		if (board[y][x] != ' ' && board[y][x] == board[y][x + 1] && board[y][x] == board[y + 1][x] && board[y][x] == board[y + 1][x + 1])
			return true;
		return false;
	}
	
	private static class info {
		int y, x;

		info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		int m = 6;
		int n = 6;
		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };
		System.out.println(solution(m, n, board));
	}
}
