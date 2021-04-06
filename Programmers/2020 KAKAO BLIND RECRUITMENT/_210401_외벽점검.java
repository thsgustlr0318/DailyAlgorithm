import java.util.Arrays;

public class _210401_외벽점검 {
	static int N, distLength, weakLength;
	static int[] distance, wall;
	static int answer = Integer.MAX_VALUE;

	public static int solution(int n, int[] weak, int[] dist) {
		weakLength = weak.length;
		distLength = dist.length;
		wall = new int[weakLength];
		distance = new int[distLength];
		N = n;
		for (int i = 0; i < distLength; i++)
			distance[i] = dist[i];
		for (int i = 0; i < weakLength; i++)
			wall[i] = weak[i];
		selectWeak(0, new int[distLength], new boolean[distLength]);
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		return answer;
	}

	private static void selectWeak(int cnt, int[] newDistanceIndexs, boolean[] selected) {
		if (cnt == distLength) {
			for (int startIndex = 0; startIndex < weakLength; startIndex++) {
				int count = 0;
				int weakIndex = startIndex;
				boolean flag = true;
				for (int distIndex = 0; distIndex < distLength && flag; distIndex++) {
					int start = wall[weakIndex];
					int end = start + distance[newDistanceIndexs[distIndex]];
					while (end >= wall[weakIndex]) {
						count++;
						if (count == weakLength) {
							answer = Math.min(answer, distIndex + 1);
							flag = false;
							break;
						}
						if (weakIndex == weakLength) {
							weakIndex = 0;
							if (end >= N)
								end %= N;
							else
								break;
						}
					}
				}
			}
		}
		for (int i = 0; i < distLength; i++) {
			if (selected[i])
				continue;
			newDistanceIndexs[cnt] = i;
			selected[i] = true;
			selectWeak(cnt + 1, newDistanceIndexs, selected);
			selected[i] = false;
		}
	}

	public static void main(String[] args) {
		int n = 12;
		int[] weak = { 1, 5, 6, 10 };
		int[] dist = { 3, 5, 7 };
		solution(n, weak, dist);
	}
}
