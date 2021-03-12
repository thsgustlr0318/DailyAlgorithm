import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210312_BOJ_P5_1305_광고 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String word = br.readLine();

		int[] arr = new int[len];
		int j = 0;

		for (int i = 1; i < len; i++) {
			while (j > 0 && word.charAt(i) != word.charAt(j))
				j = arr[j - 1];
			if (word.charAt(i) == word.charAt(j)) {
				arr[i] = ++j;
			}
		}
		System.out.println(len - arr[len - 1]);
	}
}
