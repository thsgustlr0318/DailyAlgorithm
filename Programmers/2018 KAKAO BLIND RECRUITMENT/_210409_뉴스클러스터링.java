import java.util.HashMap;

public class _210409_뉴스클러스터링 {
	static int VALUE = 65536;

	public static int solution(String str1, String str2) {
		int answer = 0;
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		HashMap<String, Integer> word1 = new HashMap<>();
		HashMap<String, Integer> word2 = new HashMap<>();
		//str1 두글자씩 끊기
		for (int index = 0; index < str1.length() - 1; index++) {
			String temp = str1.substring(index, index + 2);
			//영문자 외 존재
			if (!temp.matches("^[a-z]*$"))
				continue;
			word1.put(temp, word1.getOrDefault(temp, 0) + 1);
		}
		//str2 두글자씩 끊기
		for (int index = 0; index < str2.length() - 1; index++) {
			String temp = str2.substring(index, index + 2);
			//영문자 외 존재
			if (!temp.matches("^[a-z]*$"))
				continue;
			word2.put(temp, word2.getOrDefault(temp, 0) + 1);
		}
		int interCount = 0, unionCount = 0;
		//교집합
		for (String key : word1.keySet()) {
			if (!word2.containsKey(key))
				continue;
			interCount += Math.min(word1.get(key), word2.get(key));
		}
		//합집합
		for (String key : word1.keySet()) {
			if (word2.containsKey(key)) {
				unionCount += Math.max(word1.get(key), word2.get(key));
			} else {
				unionCount += word1.get(key);
			}
		}
		for (String key : word2.keySet()) {
			if (word1.containsKey(key))
				continue;
			unionCount += word2.get(key);
		}
		if (unionCount == 0)
			return VALUE;
		double ans = (double) interCount / unionCount * VALUE;
		answer = (int) ans;
		return answer;
	}
}
