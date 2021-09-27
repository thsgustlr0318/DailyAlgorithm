import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210927_BOJ_G5_1011_Fly_me_to_the_Alpha_Centauri {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int diff = y - x, length = 2, result = 0;
			// 1, 1, 2, 2, 3, 3...
			while (diff > 0) {
				diff -= (length++ / 2);
				result++;
			}
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}
