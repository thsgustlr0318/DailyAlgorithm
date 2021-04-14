import java.util.PriorityQueue;

public class _210414_3차_방금그곡 {
	public static String solution(String m, String[] musicinfos) {
		PriorityQueue<info> candidates = new PriorityQueue<>();
		StringBuilder str = new StringBuilder();

		for (int index = 0; index < m.length(); index++) {
			str.append(m.charAt(index));
			if (index + 1 < m.length() && m.charAt(index + 1) == '#') {
				str.setCharAt(str.length() - 1, (char) (str.charAt(str.length() - 1) + 32));
				index++;
			}
		}
		m = str.toString();
		for (int i = 0; i < musicinfos.length; i++) {
			String curMusic = musicinfos[i];
			String[] infos = curMusic.split(",");
			int playTime = getPlayTime(infos[0], infos[1]);
			String songMelody = infos[3];
			StringBuilder playMelody = new StringBuilder();
			for (int index = 0, len = 0; len < playTime; len++, index++) {
				if (index == songMelody.length())
					index = 0;
				playMelody.append(songMelody.charAt(index));
				if (index + 1 < songMelody.length() && songMelody.charAt(index + 1) == '#') {
					playMelody.setCharAt(playMelody.length() - 1, (char) (playMelody.charAt(playMelody.length() - 1) + 32));
					index++;
				}

			}
			if (playMelody.toString().contains(m))
				candidates.add(new info(infos[2], playMelody.toString(), i));
		}
		if (candidates.isEmpty())
			return "(None)";
		return candidates.poll().name;
	}

	static class info implements Comparable<info> {
		String name, melody;
		int index;

		info(String name, String melody, int index) {
			this.name = name;
			this.melody = melody;
			this.index = index;
		}

		public int compareTo(info o) {
			if (this.melody.length() == o.melody.length())
				return this.index - o.index;
			return o.melody.length() - this.melody.length();
		}
	}

	private static int getPlayTime(String startTime, String endTime) {
		int time = Integer.parseInt(endTime.substring(0, 2)) * 60 + Integer.parseInt(endTime.substring(3, 5));
		time -= Integer.parseInt(startTime.substring(0, 2)) * 60 + Integer.parseInt(startTime.substring(3, 5));
		return time;
	}

	public static void main(String[] args) {
		String m = "A#";
		String[] musicinfos = { "13:00,13:02,HAPPY,B#A#" };
		System.out.println(solution(m, musicinfos));
	}
}
