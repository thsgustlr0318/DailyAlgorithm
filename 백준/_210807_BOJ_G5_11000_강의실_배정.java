import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210807_BOJ_G5_11000_강의실_배정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		// 강의를 시작 시간 순으로 정렬
		PriorityQueue<info> classPQ = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			classPQ.add(new info(start, end));
		}
		// using: 사용 중인 강의실의 끝나는 시간
		PriorityQueue<Integer> using = new PriorityQueue<>();
		// 강의 끝나는 시간 하나 넣기
		using.add(classPQ.poll().end);
		// 모든 강의 탐색
		while (!classPQ.isEmpty()) {
			info cur = classPQ.poll();
			// 끝나는 시간보다 시작 시간이 작거나 같으면, 시간 갱신
			if (using.peek() <= cur.start)
				using.poll();
			using.add(cur.end);
		}
		System.out.println(using.size());
	}

	public static class info implements Comparable<info> {
		int start, end;

		info(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(info o) {
			if (this.start == o.start)
				return this.end - o.end;
			return this.start - o.start;
		}
	}
}
