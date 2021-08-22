import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210822_BOJ_S2_18352_특정_거리의_도시_찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int A, B;
		ArrayList<Integer>[] arr = new ArrayList[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> result = new ArrayList<>();
		int[] visited = new int[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr[A].add(B);
		}
		queue.add(X);
		visited[X] = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int next : arr[cur]) {
				if (visited[next] != 0)
					continue;
				visited[next] = visited[cur] + 1;
				queue.add(next);
				if (visited[next] == K + 1)
					result.add(next);
			}
		}
		if (result.isEmpty())
			System.out.println(-1);
		else {
			Collections.sort(result);
			for (int num : result)
				sb.append(num + "\n");
			System.out.println(sb);
		}
	}
}
