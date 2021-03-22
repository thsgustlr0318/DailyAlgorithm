import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _210322_BOJ_G1_1786_찾기 {
	static String T, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = br.readLine();
		P = br.readLine();

		int pLength = P.length();
		int tLength = T.length();

		int[] failureFunction = new int[pLength];
		int j = 0;
		for (int i = 1; i < pLength; i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j))
				j = failureFunction[j - 1];
			if (P.charAt(i) == P.charAt(j))
				failureFunction[i] = ++j;
		}
		int cnt = 0;
		ArrayList<Integer> index = new ArrayList<>();
		j = 0;
		for (int i = 0; i < tLength; i++) {
			while (j > 0 && T.charAt(i) != P.charAt(j))
				j = failureFunction[j - 1];
			if (T.charAt(i) == P.charAt(j)) {
				j++;
				if (j == pLength) {
					cnt++;
					index.add(i - j + 2);
					j = failureFunction[j - 1];
				}
			}
		}
		sb.append(cnt + "\n");
		for (int i = 0; i < index.size(); i++)
			sb.append(index.get(i) + " ");
		System.out.println(sb);
	}
}
