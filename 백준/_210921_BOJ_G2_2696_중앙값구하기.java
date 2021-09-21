import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210921_BOJ_G2_2696_중앙값구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append((N + 1) / 2 + "\n");
			PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> right = new PriorityQueue<>();
			for (int index = 1; index <= N; index++) {
				// input 숫자가 10개씩 들어옴
				if ((index % 10) == 1)
					st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				// 홀수: 숫자 넣고, 정렬 되어 있지 않으면 바꾸고, 출력
				if ((index & 1) == 1) {
					left.add(num);
					if (!right.isEmpty() && left.peek() > right.peek()) {
						int temp = left.poll();
						left.add(right.poll());
						right.add(temp);
					}
					sb.append(left.peek() + " ");
				}
				// 짝수
				else {
					right.add(num);
				}
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
