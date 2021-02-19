

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210219_SWE_D4_3234_준환이의양팔저울 {
	static int N, T, res;
	static int[] arr, newarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			newarr = new int[N];
			res = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			makePermutation(0, new boolean[N]);
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int left, int right, int idx) {
		if (idx == N) {
			res++;
			return;
		}

		if (left >= right)
			dfs(left + newarr[idx], right, idx + 1);
		if (left >= right + newarr[idx])
			dfs(left, right + newarr[idx], idx + 1);
	}

	private static void makePermutation(int cnt, boolean[] visited) {
		if (cnt == N) {
			dfs(newarr[0], 0, 1);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			newarr[cnt] = arr[i];
			makePermutation(cnt + 1, visited);
			visited[i] = false;
		}
	}
}
