import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _211003_BOJ_G3_11812_K진트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			// 1진 트리이면, 노드의 차이가 거리임
			if (K == 1) {
				sb.append(Math.abs(a - b) + "\n");
			} else {
				int result = 0;
				// 두 수가 다르면, 큰 것의 부모를 찾음
				while (a != b) {
					if (a > b) {
						// 부모 찾기
						a = (a + K - 2) / K;
						// 1이 부모인 경우
						if (a == 0)
							a = 1;
					} else {
						b = (b + K - 2) / K;
						if (b == 0)
							b = 1;
					}
					result++;
				}
				sb.append(result + "\n");
			}
		}
		System.out.println(sb);
	}
}
