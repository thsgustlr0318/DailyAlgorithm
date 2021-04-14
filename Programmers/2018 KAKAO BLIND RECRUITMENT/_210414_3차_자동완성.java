import java.util.Arrays;

public class _210414_3차_자동완성 {
	public static int solution(String[] words) {
		int answer = 0;
		Arrays.sort(words);
		for (int index = 0; index < words.length; index++) {
			StringBuilder prevWord = new StringBuilder();
			StringBuilder nextWord = new StringBuilder();
			String curWord = words[index];
			if (index >= 1)
				for (int i = 0; i < words[index].length() && i < words[index - 1].length(); i++) {
					if (curWord.charAt(i) != words[index - 1].charAt(i))
						break;
					prevWord.append(curWord.charAt(i));
				}
			if (index < words.length - 1)
				for (int i = 0; i < words[index].length() && i < words[index + 1].length(); i++) {
					if (curWord.charAt(i) != words[index + 1].charAt(i))
						break;
					nextWord.append(curWord.charAt(i));
				}
			int len = Math.max(prevWord.length(), nextWord.length());
			if(prevWord.toString().equals(curWord) || nextWord.toString().equals(curWord))
				answer += len;
			else
				answer += len + 1;
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] words = { "go", "gone", "guild" };
		System.out.println(solution(words));
	}
}
