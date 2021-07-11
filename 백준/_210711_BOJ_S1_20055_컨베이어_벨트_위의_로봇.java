import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210711_BOJ_S1_20055_컨베이어_벨트_위의_로봇 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] arr = new int[2 * N];
		boolean[] hasRobot = new boolean[2 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		// start: 올리는 위치, end: 내리는 위치
		int start = 0, end = 0;
		int MOD = 2 * N;
		int result = 1;
		while (true) {
			// step 1
			// 벨트 회전한 후 올리는 위치, 내리는 위치 구하기
			start = (start - 1 + 2 * N) % MOD;
			end = (start + N - 1) % MOD;
			// 내리는 위치에 로봇이 있으면, 내리기
			if (hasRobot[end]) {
				hasRobot[end] = false;
			}
			// step 2
			for (int i = N - 2; i >= 0; i--) {
				// targetIndex: 살펴볼 로봇의 위치, nextIndex: 다음 칸 위치 
				int targetIndex = (start + i) % MOD;
				int nextIndex = (start + i + 1) % MOD;
				if (!hasRobot[nextIndex] && hasRobot[targetIndex] && arr[nextIndex] > 0) {
					hasRobot[nextIndex] = true;
					hasRobot[targetIndex] = false;
					arr[nextIndex]--;
					// 내리는 위치에 로봇이 있으면, 내리기
					if (hasRobot[end])
						hasRobot[end] = false;
				}
			}
			// step 3
			if (arr[start] > 0) {
				hasRobot[start] = true;
				arr[start]--;
			}
			// step 4
			int count = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (arr[i] == 0)
					count++;
			}
			if (count >= K)
				break;
			result++;
		}
		System.out.println(result);
	}
}
