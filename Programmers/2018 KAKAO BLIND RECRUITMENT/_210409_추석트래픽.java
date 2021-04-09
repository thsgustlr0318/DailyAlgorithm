
public class _210409_추석트래픽 {
	public static int solution(String[] lines) {
		int answer = 0;
		int length = lines.length;
		int[] end = new int[length];
		int[] start = new int[length];
		for (int index = 0; index < length; index++) {
			//마지막 's'지우고 공백 기준으로 문자 나누기
			String[] timeinfo = lines[index].replace('s', ' ').split(" ");
			//. or : 기준으로 문자 나누기
			String[] times = timeinfo[1].split("[.:]");
			//끝나는 시간
			end[index] += Integer.parseInt(times[0]) * 3600000;
			end[index] += Integer.parseInt(times[1]) * 60000;
			end[index] += Integer.parseInt(times[2]) * 1000;
			end[index] += Integer.parseInt(times[3]);
			//시작 시간
			start[index] = (int) (end[index] - Double.parseDouble(timeinfo[2]) * 1000) + 1;
		}
		for (int i = 0; i < length; i++) {
			int count = 0;
			//끝나는 시간 기준으로 탐색  
			int endtime = end[i] + 1000;
			//i 이전 값들은 end[i]보다 먼저 끝나므로 제외
			for (int j = i; j < length; j++) {
				//(end[i]) ~ (end[i] + 1)초 안에 시작하는 값들 구하기
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
