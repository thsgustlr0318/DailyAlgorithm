import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210225_BOJ_S2_2567_색종이2 {
	static int moveDir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		boolean[][] board = new boolean[102][102];
		int res = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int y = 100 - b; y > 100 - b - 10; y--) {
				for (int x = a; x < a + 10; x++) {
					board[y][x] = true;
				}
			}
		}
		for (int y = 1; y <= 100; y++) {
			for (int x = 1; x <= 100; x++) {
				if (board[y][x]) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int ny = y + moveDir[k][0];
						int nx = x + moveDir[k][1];
						if (!board[ny][nx])
							cnt++;
					}
					res += cnt;
				}
			}
		}
		System.out.println(res);
	}
}
