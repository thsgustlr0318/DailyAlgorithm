public class _210326_광고삽입 {
	public static void main(String[] args) {
		String play_time = "99:59:59";
		String adv_time = "25:00:00";
		String[] logs = { "69:59:59-99:59:59", "01:00:00-99:59:59", "79:59:59-99:59:59", "11:00:00-31:00:00" };
		solution(play_time, adv_time, logs);
	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		int playTime = getTime(play_time);
		int advTime = getTime(adv_time);
		int len = logs.length;
		long[] arr = new long[playTime + 1];
		for (int i = 0; i < len; i++) {
			String[] temp = logs[i].split("-");
			arr[getTime(temp[0])]++;
			arr[getTime(temp[1])]--;
		}
		for (int i = 1; i <= playTime; i++) {
			arr[i] += arr[i - 1];
		}
		for (int i = 1; i <= playTime; i++) {
			arr[i] += arr[i - 1];
		}
		long answerTotalTime = arr[advTime];
		long answerStartTime = 0;
		for (int start = 1; start + advTime <= playTime; start++) {
			long totalTime = arr[start + advTime] - arr[start];
			if (answerTotalTime < totalTime) {
				answerTotalTime = totalTime;
				answerStartTime = start + 1;
			}
		}
		answer = convertTimeToString(answerStartTime);
		return answer;
	}

	private static int getTime(String time) {
		int result = 0;
		String[] str = time.split(":");
		result += 3600 * Integer.parseInt(str[0]);
		result += 60 * Integer.parseInt(str[1]);
		result += Integer.parseInt(str[2]);
		return result;
	}

	private static String convertTimeToString(long time) {
		String ans = "";
		long hour = time / 3600;
		time %= 3600;
		long min = time / 60;
		time %= 60;
		long sec = time;
		if (hour < 10)
			ans += "0" + Long.toString(hour);
		else
			ans += Long.toString(hour);
		ans += ":";
		if (min < 10)
			ans += "0" + Long.toString(min);
		else
			ans += Long.toString(min);
		ans += ":";
		if (sec < 10)
			ans += "0" + Long.toString(sec);
		else
			ans += Long.toString(sec);
		return ans;
	}
}
