import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _211016_BOJ_G4_20040_사이클게임 {
	static int[] parents, counts;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n];
		counts = new int[n];
		for (int index = 0; index < n; index++) {
			parents[index] = index;
			counts[index] = 1;
		}
		for (int index = 0; index < m; index++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			result++;
			union(a, b);
		}
		System.out.println(0);
	}

	public static int getParent(int x) {
		if (x == parents[x])
			return x;
		return parents[x] = getParent(parents[x]);
	}

	public static void union(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a > b) {
			parents[a] = b;
			counts[b] += counts[a];
			counts[a] = 0;
		} else if (a < b) {
			parents[b] = a;
			counts[a] += counts[b];
			counts[b] = 0;
		} 
		// 부모가 같으면, 싸이클이 생긴 것
		else {
			System.out.println(result);
			System.exit(0);
		}
	}
}
