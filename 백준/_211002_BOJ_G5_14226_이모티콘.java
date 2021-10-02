import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _211002_BOJ_G5_14226_이모티콘 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();
		// 복사
		// 붙여넣기
		// 지우기
		int[][] dp = new int[S * 2 + 1][S * 2 + 1];
		dp[1][0] = 1;
		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(1, 0));
		while (!queue.isEmpty()) {
			Info cur = queue.poll();
			int count = cur.count, clipboard = cur.clipboard;
			if (count == S) {
				System.out.println(dp[count][clipboard] - 1);
				break;
			}

			// 복사
			// clipboard = count
			if (clipboard + count <= 2 * S && dp[count][count] == 0) {
				dp[count][count] = dp[cur.count][cur.clipboard] + 1;
				queue.add(new Info(count, count));
			}
			// 붙여넣기 (클립보드에 값이 있어야 함)
			// count = count + clipboard
			if (clipboard != 0 && count + clipboard <= 2 * S && dp[count + clipboard][clipboard] == 0) {
				dp[count + clipboard][clipboard] = dp[count][clipboard] + 1;
				queue.add(new Info(count + clipboard, clipboard));
			}
			// 지우기
			// count = count - 1
			if (count - 1 >= 0 && dp[count - 1][clipboard] == 0) {
				dp[count - 1][clipboard] = dp[count][clipboard] + 1;
				queue.add(new Info(count - 1, clipboard));
			}
		}
	}

	public static class Info {
		int count, clipboard;

		Info(int count, int clipboard) {
			this.count = count;
			//			this.time = time;
			this.clipboard = clipboard;
		}
	}
}
