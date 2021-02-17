import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210217_BOJ_G4_1939_중량제한 {
	static int N, M, A, B, res = 0;
	static int minv = Integer.MAX_VALUE, maxv = Integer.MIN_VALUE;

	static class info {
		int dest, weight;

		info(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<info>[] list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new info(b, c));
			list[b].add(new info(a, c));
			minv = Math.min(minv, c);
			maxv = Math.max(maxv, c);
		}
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		while (minv <= maxv) {
			int mid = (minv + maxv) / 2;

			boolean[] visited = new boolean[N + 1];
			Queue<Integer> q = new LinkedList<>();
			visited[A] = true;
			q.add(A);
			boolean check = false;

			while (!q.isEmpty()) {
				int cur = q.poll();
				if (cur == B) {
					res = Math.max(res, mid);
					check = true;
					break;
				}
				for (info t : list[cur]) {
					if (visited[t.dest] || t.weight < mid)
						continue;
					visited[t.dest] = true;
					q.add(t.dest);
				}
			}
			if (check)
				minv = mid + 1;
			else
				maxv = mid - 1;
		}
		System.out.println(res);
	}
}
