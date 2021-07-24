import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _210724_BOJ_G4_6443_애너그램 {
	static StringBuilder sb;
	static int[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[] word = br.readLine().toCharArray();
			Arrays.sort(word);
			sb = new StringBuilder();
			// 알파벳마다 개수 구하기
			alphabet = new int[26];
			for (char alpha : word)
				alphabet[alpha - 'a']++;
			// DFS로 애너그램 만들기
			getAnagram(word, 0, 0, new char[word.length]);
			System.out.print(sb);
		}
	}

	private static void getAnagram(char[] word, int index, int count, char[] newWord) {
		if (count == word.length) {
			sb.append(new String(newWord) + "\n");
			return;
		}
		// 알파벳 순서로 모두 탐색
		for (int i = 0; i < 26; i++) {
			if (alphabet[i] == 0)
				continue;
			alphabet[i]--;
			newWord[count] = (char) ('a' + i);
			getAnagram(word, i, count + 1, newWord);
			alphabet[i]++;
		}
	}
}
