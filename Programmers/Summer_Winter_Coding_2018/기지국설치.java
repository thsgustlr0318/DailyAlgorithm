package Summer_Winter_Coding_2018;

public class 기지국설치 {
	public static int solution(int n, int[] stations, int w) {
		int answer = 0, len = w * 2 + 1;
		int index = 1;
		for (int i = 0; i < stations.length; i++) {
			int left = stations[i] - w - 1, right = stations[i] + w + 1;
			if (index <= left) {
				answer += calc(left - index + 1, len);
			}
			index = right;
			//마지막 기지국
			if (i == stations.length - 1) {
				left = n;
				if (index < left) {
					answer += calc(left - index + 1, len);
				}
			}
		}
		return answer;
	}

	private static int calc(int count, int mod) {
		int total = count / mod;
		if (count % mod != 0)
			++total;
		return total;
	}

	public static void main(String[] args) {
		int n = 11;
		int[] stations = { 3, 4, 6, 11 };
		int w = 1;
		System.out.println(solution(n, stations, w));
	}
}
