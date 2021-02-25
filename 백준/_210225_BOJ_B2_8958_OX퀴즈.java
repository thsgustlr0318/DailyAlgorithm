import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210225_BOJ_B2_8958_OX퀴즈 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String str = br.readLine();
			int res = 0;
			for (int i = 0, score = 0; i < str.length(); i++) {
				if (str.charAt(i) == 'O') {
					score++;
					res += score;
				} else 
					score = 0;
			}
			sb.append(res + "\n");
		}
		System.out.println(sb);
	}
}
