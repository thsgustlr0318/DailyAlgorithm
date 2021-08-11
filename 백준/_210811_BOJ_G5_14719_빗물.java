import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210811_BOJ_G5_14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int result = 0;
		int[] rain = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++)
			rain[i] = Integer.parseInt(st.nextToken());
		// 좌측 -> 우측 탐색
		for (int index = 0, sum = 0, height = 0; index < W; index++) {
			// 크거나 같은거 만나면, 전에 빗물 더하고 높이 갱신
			// 최대 높이가 같을 경우 더해줌
			if (rain[index] >= height) {
				height = rain[index];
				result += sum;
				sum = 0;
			} else {
				sum += height - rain[index];
			}
		}
		// 우측 -> 좌측 탐색
		for (int index = W - 1, sum = 0, height = 0; index >= 0; index--) {
			// 최대 높이가 같지 않을 경우
			if (rain[index] > height) {
				height = rain[index];
				result += sum;
				sum = 0;
			} else {
				sum += height - rain[index];
			}
		}
		System.out.println(result);
	}
}
