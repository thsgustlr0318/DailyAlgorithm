import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210318_SWE_D4_3289_서로소집합 {
	static int[] arr;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N + 1];
			for (int idx = 1; idx <= N; idx++)
				arr[idx] = idx;

			for (int i = 0; i < M; i++) {
				int command, a, b;
				st = new StringTokenizer(br.readLine());
				command = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (command == 0) {
					union(a, b);
				} else {
					if (getParent(a) == getParent(b))
						sb.append(1);
					else
						sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int getParent(int a) {
		if (arr[a] == a)
			return a;
		return arr[a] = getParent(arr[a]);
	}

	private static void union(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a == b)
			return;
		arr[a] = b;
	}
}
