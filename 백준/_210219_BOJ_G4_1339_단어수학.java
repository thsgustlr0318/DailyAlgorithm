import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _210219_BOJ_G4_1339_단어수학 {
	static int N, res = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] alphabetValue = new int[26];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int value = 1;
			//value: AAA->100, ABCD->1000, AAAAA->10000
			for (int idx = 1; idx < str.length(); idx++)
				value *= 10;
			//alphabetValue[A]: A알파벳이 가지는 가치
			//단어의 모든 알파벳을 통해 가치 구하기
			for (int idx = 0; idx < str.length(); idx++, value /= 10) {
				alphabetValue[str.charAt(idx) - 'A'] += value;
			}
		}
		//정렬
		Arrays.sort(alphabetValue);
		//가치가 가장 큰 알파벳부터 점수 주기
		for (int idx = alphabetValue.length - 1, value = 9; idx >= 0; idx--, value--) {
			if (alphabetValue[idx] == 0)
				continue;
			res += value * alphabetValue[idx];
		}
		System.out.println(res);
	}
}
