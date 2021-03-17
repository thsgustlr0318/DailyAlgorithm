import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210317_BOJ_S3_14889_스타트와링크 {
	static int n, res = Integer.MAX_VALUE;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0, new boolean[n], 0);
		System.out.println(res);
	}

	private static void dfs(int cnt, boolean[] selected, int idx) {
		if (cnt == n / 2) {
			int start = 0, link = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (selected[i] && selected[j])
						start += arr[i][j];
					if (!selected[i] && !selected[j])
						link += arr[i][j];
				}
			}
			res = Math.min(res, Math.abs(start - link));
			return;
		}
		for (int i = idx; i < n; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			dfs(cnt + 1, selected, i + 1);
			selected[i] = false;
		}
	}
}