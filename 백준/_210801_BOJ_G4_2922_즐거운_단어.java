import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _210801_BOJ_G4_2922_즐거운_단어 {
	static long result = 0;
	static int mo = 5, ja = 21;
	static boolean hasL = false;
	static ArrayList<Integer> blankIndex = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		for (int index = 0; index < word.length; index++) {
			char c = word[index];
			if (c == '_') {
				blankIndex.add(index);
			} else if (c == 'L')
				hasL = true;
		}
		dfs(word, blankIndex.size(), 0, 0, 0);
		System.out.println(result);
	}

	public static void dfs(char[] word, int count, int index, int jaum, int moum) {
		if (count == index) {
			// 개수 구하기
			long sum = 1;
			// L이 있는 경우
			if (hasL) {
				if (moum != 0)
					sum *= Math.pow(mo, moum);
				if (jaum != 0)
					sum *= Math.pow(ja, jaum);
				result += sum;
			}
			// L이 없는 경우
			else {
				// L을 추가할 수 없는 경우
				if (jaum == 0)
					return;
				// 자음이 한개인 경우: L만 들어갈 수 있음
				// 자음이 두개 이상인 경우: L포함해서 만들 수 있는 개수 - L없이 만들 수 있는 개수
				if (jaum >= 2)
					sum *= (Math.pow(21, jaum) - Math.pow(20, jaum));
				// 모음 개수 곱하기
				if (moum != 0)
					sum *= Math.pow(mo, moum);
				result += sum;
			}
			return;
		}
		// 자음이 들어갈 수 있는 경우
		word[blankIndex.get(index)] = 'B';
		if (canGo(word, blankIndex.get(index)))
			dfs(word, count, index + 1, jaum + 1, moum);
		word[blankIndex.get(index)] = '_';

		// 모음이 들어갈 수 있는 경우
		word[blankIndex.get(index)] = 'A';
		if (canGo(word, blankIndex.get(index)))
			dfs(word, count, index + 1, jaum, moum + 1);
		word[blankIndex.get(index)] = '_';
	}

	// 즐거운 단어를 만들 수 있는지 확인
	public static boolean canGo(char[] word, int index) {
		// XX_
		if (index - 2 >= 0) {
			if (isExcitingWord(word, index - 2))
				return false;
		}
		// X_X
		if (index - 1 >= 0 && index + 1 < word.length && word[index + 1] != '_') {
			if (isExcitingWord(word, index - 1))
				return false;
		}
		// _XX
		if (index + 2 < word.length && word[index + 1] != '_' && word[index + 2] != '_') {
			if (isExcitingWord(word, index))
				return false;
		}
		return true;
	}

	// 연속하는 세 알파벳 종류가 같으면 true, 다르면 false
	public static boolean isExcitingWord(char[] word, int index) {
		if (isVowel(word[index]) == isVowel(word[index + 1]) && isVowel(word[index + 1]) == isVowel(word[index + 2]))
			return true;
		return false;
	}

	// 현재 문자가 모음인지 확인
	public static boolean isVowel(char c) {
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			return true;
		return false;
	}
}
