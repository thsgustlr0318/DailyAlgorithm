
public class _210429_행렬테두리회전하기 {
	static int[][] board;

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		//행렬 채우기
		board = new int[rows][columns];
		for (int i = 0, index = 1; i < rows; i++)
			for (int x = 0; x < columns; x++)
				board[i][x] = index++;
		//쿼리 조건대로 행렬 회전
		for (int i = 0; i < queries.length; i++)
			answer[i] = rotate(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
		return answer;
	}

	private static int rotate(int sy, int sx, int ey, int ex) {
		int minValue = Integer.MAX_VALUE;
		int[][] temp = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				temp[i][j] = board[i][j];
		for (int y = sy, x = sx + 1; x <= ex; x++) {
			board[y][x] = temp[y][x - 1];
			minValue = Math.min(minValue, board[y][x]);
		}
		for (int y = sy + 1, x = ex; y <= ey; y++) {
			board[y][x] = temp[y - 1][x];
			minValue = Math.min(minValue, board[y][x]);
		}
		for (int y = ey, x = ex - 1; x >= sx; x--) {
			board[y][x] = temp[y][x + 1];
			minValue = Math.min(minValue, board[y][x]);
		}
		for (int y = ey - 1, x = sx; y >= sy; y--) {
			board[y][x] = temp[y + 1][x];
			minValue = Math.min(minValue, board[y][x]);
		}
		return minValue;
	}

	public static void main(String[] args) {
		int rows = 6, columns = 6;
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		solution(rows, columns, queries);
	}
}
