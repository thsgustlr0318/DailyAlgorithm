import java.util.ArrayList;
import java.util.HashSet;

public class _210408_블록게임 {
	static int[][][] block = { { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 1 }, { 1, 1 }, { 2, 0 }, { 2, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } }, { { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 } } };
	static int[][][] block_left = { { { 0, 1 }, { 0, 2 } }, { { 0, 0 }, { 1, 0 } }, { { 0, 1 }, { 1, 1 } }, { { 0, 0 }, { 0, 1 } }, { { 0, 0 }, { 0, 2 } } };
	static int N;

	public static int solution(int[][] board) {
		int answer = 0;
		N = board.length;
		ArrayList<info> arr = new ArrayList<>();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				for (int type = 0; type < 5; type++) {
					if (canCandidateBlock(board, y, x, type)) {
						arr.add(new info(y, x, type));
						break;
					}
				}
			}
		}
		boolean[] check = new boolean[arr.size()];
		for (int index = 0; index < arr.size(); index++) {
			if (canDeleteBlock(board, arr.get(index))) {
				int y = arr.get(index).y, x = arr.get(index).x, type = arr.get(index).type;
				if (check[index])
					continue;
				for (int blockidx = 0; blockidx < 4; blockidx++) {
					int ny = y + block[type][blockidx][0], nx = x + block[type][blockidx][1];
					board[ny][nx] = 0;
				}
				answer++;
				check[index] = true;
				index = 0;
			}
		}
		return answer;
	}

	private static class info {
		int y, x, type;

		info(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}

	private static boolean canCandidateBlock(int[][] board, int y, int x, int type) {
		HashSet<Integer> set = new HashSet<>();
		for (int idx = 0; idx < 4; idx++) {
			int ny = y + block[type][idx][0], nx = x + block[type][idx][1];
			if (ny >= N || nx >= N || board[ny][nx] == 0)
				return false;
			set.add(board[ny][nx]);
		}
		return set.size() == 1 ? true : false;
	}

	private static boolean canDeleteBlock(int[][] board, info blockinfo) {
		int y = blockinfo.y, x = blockinfo.x, type = blockinfo.type;
		for (int idx = 0; idx < 2; idx++) {
			int ny = y + block_left[type][idx][0], nx = x + block_left[type][idx][1];
			while (ny >= 0) {
				if (board[ny][nx] != 0)
					return false;
				ny--;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 }, { 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 },
				{ 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } };
		System.out.println(solution(board));
	}
}
