import java.util.Stack;

public class _210420_크레인인형뽑기게임 {
	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		int N = board.length;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < moves.length; i++) {
			int x = moves[i] - 1;
			int y = 0;
			while (y < N && board[y][x] == 0) {
				y++;
			}
			if (y == N)
				continue;
			if (!s.isEmpty() && s.peek() == board[y][x]) {
				answer += 2;
				s.pop();
			} else
				s.add(board[y][x]);
			board[y][x] = 0;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}
}
