
public class _210409_추석트래픽 {
	public static int solution(String[] lines) {
		int answer = 0;
		int length = lines.length;
		int[] end = new int[length];
		int[] start = new int[length];
		for (int index = 0; index < length; index++) {
			String[] timeinfo = lines[index].replace('s', ' ').split(" ");
			String[] times = timeinfo[1].split("[.:]");
			end[index] += Integer.parseInt(times[0]) * 3600000;
			end[index] += Integer.parseInt(times[1]) * 60000;
			end[index] += Integer.parseInt(times[2]) * 1000;
			end[index] += Integer.parseInt(times[3]);
			start[index] = (int) (end[index] - Double.parseDouble(timeinfo[2]) * 1000) + 1;
		}
		for (int i = 0; i < length; i++) {
			int count = 0;
			int endtime = end[i] + 1000;
			for (int j = i; j < length; j++) {
				if (endtime > start[j])
					count++;
			}
			answer = Math.max(answer, count);
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] lines = { "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" };
		solution(lines);
	}
}
