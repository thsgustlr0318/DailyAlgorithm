package Summer_Winter_Coding_2018;

public class 스티커모으기_2 {
	public static int solution(int sticker[]) {
		//한 칸인경우, 첫 번째 값 리턴
		if (sticker.length == 1)
			return sticker[0];
		int answer = 0, N = sticker.length;
		answer = calc(new int[N + 2], sticker, N);
		//배열 한 칸씩 당기기
		int temp = sticker[0];
		for (int i = 0; i < sticker.length - 1; i++)
			sticker[i] = sticker[i + 1];
		sticker[sticker.length - 1] = temp;
		//첫 번째 스티커 사용
		answer = Math.max(answer, calc(new int[N + 2], sticker, N));

		return answer;
	}

	private static int calc(int[] dp, int[] sticker, int N) {
		//첫 번째 스티커 사용
		dp[2] = sticker[0];
		for (int index = 1; index < N - 1; index++) {
			dp[index + 2] = Math.max(dp[index], dp[index - 1]) + sticker[index];
		}
		//마지막 스티커 확인
		//첫 번째 스티커를 사용했으므로, 마짐가 스티커는 사용 못함
		return Math.max(dp[N], dp[N - 1]);
	}

	public static void main(String[] args) {
		int[] sticker = { 14, 6, 5, 11, 3, 9, 2, 10 };
		System.out.println(solution(sticker));
	}
}
