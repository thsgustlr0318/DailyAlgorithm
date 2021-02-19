

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210219_SWE_요리사 {
	static int N, T, res;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			res = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, new boolean[N], 0);
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cnt, boolean[] isSelected, int idx) {
		if (cnt == N / 2) {
			int a = 0, b = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					if (isSelected[i] && isSelected[j])
						a += arr[i][j];
					else if (!isSelected[i] && !isSelected[j])
						b += arr[i][j];
				}
			}
			res = Math.min(res, Math.abs(a - b));
			return;
		}
		for (int i = idx; i < N; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			dfs(cnt + 1, isSelected, i);
			isSelected[i] = false;
		}
	}
}
