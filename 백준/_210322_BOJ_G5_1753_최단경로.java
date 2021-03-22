import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210322_BOJ_G5_1753_최단경로 {
	static int V, E, K;
	static int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		ArrayList<info> arr[] = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++)
			arr[i] = new ArrayList<>();
		for (int i = 1; i <= E; i++) {
			int u, v, w;
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			arr[u].add(new info(v, w));
		}
		
		int[] distance = new int[V + 1];
		for (int i = 0; i <= V; i++)
			distance[i] = INF;
		distance[K] = 0;
		
		PriorityQueue<info> pq = new PriorityQueue<>();
		pq.add(new info(K, 0));

		while (!pq.isEmpty()) {
			info current = pq.poll();
			if (distance[current.dest] < current.weight)
				continue;
			for (info next : arr[current.dest]) {
				int nextDest = next.dest;
				int nextDistance = current.weight + next.weight;
				if (nextDistance < distance[nextDest]) {
					distance[nextDest] = nextDistance;
					pq.add(new info(nextDest, nextDistance));
				}
			}
		}
		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF)
				sb.append("INF\n");
			else
				sb.append(distance[i] + "\n");
		}
		System.out.println(sb);
	}

	private static class info implements Comparable<info> {
		int dest, weight;

		info(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}

		info() {

		}

		@Override
		public int compareTo(info o) {
			return this.weight - o.weight;
		}

	}
}
