import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210322_BOJ_G2_2610_회의준비 {
	static int N, M, K = 0;
	static int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[][] arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				arr[i][j] = INF;
		for (int i = 0; i < M; i++) {
			int a, b;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = 1;
		}
		for (int mid = 1; mid <= N; mid++) {
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
					arr[start][end] = Math.min(arr[start][end], arr[start][mid] + arr[mid][end]);
				}
			}
		}
		ArrayList<Integer> candidate = new ArrayList<>();
		boolean[] visited = new boolean[N + 1];
		for (int index = 1; index <= N; index++) {
			if (visited[index])
				continue;
			PriorityQueue<info> pq = new PriorityQueue<>();
			//자기 자신 처리
			visited[index] = true;
			int maxValue = 0;
			//의사전달 시간 중 최대값 구하기
			for (int i = 1; i <= N; i++)
				if (index != i && arr[index][i] != INF)
					maxValue = Math.max(maxValue, arr[index][i]);
			pq.add(new info(index, maxValue));

			//같은 위원회에 속한 사람들 처리
			for (int select = 1; select <= N; select++) {
				//같은 위원회에 속한 사람 고르기
				if (arr[index][select] != INF && !visited[select]) {
					maxValue = 0;
					visited[select] = true;
					//의사전달 시간 중 최대값 구하기
					for (int i = 1; i <= N; i++)
						if (select != i && arr[select][i] != INF)
							maxValue = Math.max(maxValue, arr[select][i]);
					pq.add(new info(select, maxValue));
				}
			}
			//K: 위원회 갯수, candidate: 각 위원회의 대표 리스트
			K++;
			candidate.add(pq.poll().index);
		}
		Collections.sort(candidate);
		sb.append(K + "\n");
		for (int i = 0; i < candidate.size(); i++)
			sb.append(candidate.get(i) + "\n");
		System.out.println(sb);
	}

	private static class info implements Comparable<info> {
		int index, value;

		info(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(info o) {
			return this.value - o.value;
		}
	}
}
