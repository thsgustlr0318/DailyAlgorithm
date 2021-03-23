import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210323_BOJ_G3_1238_파티 {
	static int N, M, X;
	static ArrayList<info> arr[];
	static int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = new ArrayList<info>();
		for (int i = 0; i < M; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			arr[a].add(new info(b, c));
		}
		int res = 0;
		//index마을에서 X마을로, X마을에서 index마을로 돌아오는 최소 경로 구하기
		for (int index = 1; index <= N; index++) {
			if (index == X)
				continue;
			res = Math.max(res, dijkstra(index, X) + dijkstra(X, index));
		}
		System.out.println(res);
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

	private static int dijkstra(int start, int end) {
		PriorityQueue<info> pq = new PriorityQueue<>();
		int[] distance = new int[N + 1];
		for (int i = 1; i <= N; i++)
			distance[i] = INF;
		distance[start] = 0;
		pq.add(new info(start, 0));
		while (!pq.isEmpty()) {
			info cur = pq.poll();
			//최단거리가 아니면, continue
			if (cur.weight > distance[cur.dest])
				continue;

			for (info next : arr[cur.dest]) {
				int nextDest = next.dest;
				int nextDistance = next.weight + cur.weight;

				if (distance[nextDest] > nextDistance) {
					distance[nextDest] = nextDistance;
					pq.add(new info(nextDest, nextDistance));
				}
			}
		}
		return distance[end];
	}
}
