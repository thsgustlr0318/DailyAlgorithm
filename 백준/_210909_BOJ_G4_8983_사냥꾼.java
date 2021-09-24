import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _210909_BOJ_G4_8983_사냥꾼 {
	static int M, N, L, result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int[] shooters = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < M; index++)
			shooters[index] = Integer.parseInt(st.nextToken());
		Arrays.sort(shooters);
		for (int index = 0; index < N; index++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 이분 탐색
			int left = 0, right = M - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				int distance = getDistance(x, y, shooters[mid]);
				// 사정거리 이내일 경우
				if (distance <= L) {
					result++;
					break;
				}
				// 동물이 안맞는 경우, 다음 사대 찾음
				else {
					// 동물이 사대보다 오른쪽에 있으면
					if (x - shooters[mid] > 0) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
			}
		}
		System.out.println(result);
	}

	public static int getDistance(int animalX, int animalY, int shooter) {
		return Math.abs(shooter - animalX) + animalY;
	}
}