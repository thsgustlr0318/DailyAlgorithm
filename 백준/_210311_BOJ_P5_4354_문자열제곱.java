import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210311_BOJ_P5_4354_문자열제곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String word;
		while (!(word = br.readLine()).equals(".")) {
			int len = word.length();
			int[] arr = new int[len];
			int j = 0;
			for (int i = 1; i < len; i++) {
				while (j > 0 && word.charAt(i) != word.charAt(j))
					j = arr[j - 1];
				if (word.charAt(i) == word.charAt(j)) {
					arr[i] = ++j;
				}
			}
			if (arr[len - 1] == 0 || len % (len-(arr[len - 1])) != 0)
				sb.append("1\n");
			else
				sb.append(len / (len - arr[len - 1])).append("\n");
		}
		System.out.println(sb);
	}
}
//KMP
