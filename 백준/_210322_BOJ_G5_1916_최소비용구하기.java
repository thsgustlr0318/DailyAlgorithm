import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210322_BOJ_G5_1916_최소비용구하기 {
	static int N, M, start, end;
	static int INF = 1000000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		ArrayList<info> arr[] = new ArrayList[N + 1];
		int[] distance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<info>();
			distance[i] = INF;
		}
		for (int i = 0; i < M; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			arr[a].add(new info(b, c));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		PriorityQueue<info> pq = new PriorityQueue<>();
		pq.add(new info(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			info cur = pq.poll();

			if (distance[cur.dest] < cur.weight)
				continue;
			for (info next : arr[cur.dest]) {
				int nextDest = next.dest;
				int nextWeight = cur.weight + next.weight;
				if (distance[nextDest] > nextWeight) {
					distance[nextDest] = nextWeight;
					pq.add(new info(nextDest, nextWeight));
				}
			}
		}
		System.out.println(distance[end]);
	}

	private static class info implements Comparable<info> {
		int dest, weight;

		info(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(info o) {
			return this.weight - o.weight;
		}
	}
}
