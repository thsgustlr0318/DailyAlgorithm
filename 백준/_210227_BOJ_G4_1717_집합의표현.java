import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210227_BOJ_G4_1717_집합의표현 {
	static int[] arr;

	public static void main(String[] args) throws IOException {
		int n, m;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		for (int i = 0; i <= n; i++)
			arr[i] = i;

		for (int i = 0; i < m; i++) {
			int command, a, b;
			st = new StringTokenizer(br.readLine());
			command = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (command == 0) {
				if (a != b) {
					unionParent(a, b);
				}
			} else {
				if (check(a, b))
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}

	private static int getParent(int child) {
		if (arr[child] == child)
			return child;
		return arr[child] = getParent(arr[child]);
	}

	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a != b)
			arr[a] = b;
	}

	private static boolean check(int dest, int child) {
		dest = getParent(dest);
		child = getParent(child);

		if (dest == child)
			return true;
		else
			return false;
	}
}
