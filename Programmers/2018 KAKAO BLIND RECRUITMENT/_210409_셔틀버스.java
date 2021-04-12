import java.util.Arrays;

public class _210409_셔틀버스 {
	public static String solution(int n, int t, int m, String[] timetable) {
		int length = timetable.length;
		int[] crueTime = new int[length];
		for (int index = 0; index < length; index++) {
			String curtime = timetable[index];
			String[] times = curtime.split(":");
			crueTime[index] = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
		}
		Arrays.sort(crueTime);
		int startTime = 540;
		int crueCount = 0, crueIndex = 0;
		for (int i = 0; i < n; i++, startTime += t) {
			crueCount = 0;
			while (crueCount < m && crueIndex < length && crueTime[crueIndex] <= startTime) {
				crueIndex++;
				crueCount++;
			}
		}
		if (crueCount < m)
			return convertTime(startTime - t);
		else
			return convertTime(crueTime[crueIndex - 1] - 1);
	}

	private static String convertTime(int time) {
		String ans = "";
		int hour = time / 60, min = time % 60;
		if (hour < 10)
			ans += "0";
		ans += hour + ":";
		if (min < 10)
			ans += "0";
		ans += min;
		return ans;
	}

	public static void main(String[] args) {
		int n = 10;
		int t = 1;
		int m = 5;
		String[] timetable = { "09:00", "09:00", "09:00", "09:00", "09:00" };
		System.out.println(solution(n, t, m, timetable));
	}
}
