import java.util.Arrays;

public class _210406_무지의먹방라이브 {
	public static int solution(int[] food_times, long k) {
		int answer = -1;
		int len = food_times.length;
		int[] foods = new int[food_times.length];
		for (int i = 0; i < len; i++)
			foods[i] = food_times[i];
		Arrays.sort(foods);
		int index = 0, cnt = 0, start = 0;
		while (len > 0 && k >= 0) {
			int time = foods[index] - start;
			start = foods[index++];
			if (time == 0) {
				len--;
				continue;
			}
			k -= (long)time * len;
			cnt += time;

			if (k <= 0) {
				while (k < 0) {
					cnt--;
					k += len;
				}
				break;
			}
			len--;
		}
		for (int i = 0; i < food_times.length; i++)
			food_times[i] -= cnt;
		for (int i = 0; i < food_times.length; i++) {
			if (food_times[i] <= 0)
				continue;
			k--;
			if (k < 0) {
				answer = i + 1;
				break;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] food_times = { 2, 2, 2, 2 };
		int k = 7;
		System.out.println(solution(food_times, k));
	}
}
