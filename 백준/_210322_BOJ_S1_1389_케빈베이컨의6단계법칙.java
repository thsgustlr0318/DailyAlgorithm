import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210322_BOJ_S1_1389_케빈베이컨의6단계법칙 {
	static int N, M;
	static int resultIndex, result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> arr[] = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
		for (int start = 1; start <= N; start++) {
			int[] visited = new int[N + 1];
			visited[start] = 1;
			Queue<info> q = new LinkedList<info>();
			q.add(new info(start, 1));
			while (!q.isEmpty()) {
				info cur = q.poll();

				for (Integer next : arr[cur.dest]) {
					if (visited[next] != 0)
						continue;
					visited[next] = cur.weight + 1;
					q.add(new info(next, cur.weight + 1));
				}
			}
			int cnt = 0;
			for (int i = 1; i <= N; i++)
				cnt += visited[i];
			if (result > cnt) {
				result = cnt;
				resultIndex = start;
			}
		}
		System.out.println(resultIndex);
	}

	private static class info {
		int dest, weight;

		info(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
}
