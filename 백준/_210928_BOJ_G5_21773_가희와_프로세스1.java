import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210928_BOJ_G5_21773_가희와_프로세스1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Process> process = new PriorityQueue<>();
		for (int index = 1; index <= N; index++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			process.add(new Process(A, B, C));
		}
		for (int time = 0; time < T; time++) {
			Process cur = process.poll();
			sb.append(cur.id + "\n");
			// 프로세스의 실행 시간이 남아있으면, 실행 시간 -1, 우선순위 -1
			// 사용한 프로세스의 우선순위를 -1 함으로써 나머지 프로세스들의 우선 순위가 상승한 효과를 냄
			if (cur.time > 1) {
				cur.priority--;
				cur.time--;
				process.add(cur);
			}
		}
		System.out.println(sb);
	}

	static class Process implements Comparable<Process> {
		int id, priority, time;

		public Process(int id, int time, int priority) {
			this.id = id;
			this.priority = priority;
			this.time = time;
		}

		@Override
		public int compareTo(Process o) {
			if (this.priority == o.priority)
				return this.id - o.id;
			return o.priority - this.priority;
		}
	}
}
