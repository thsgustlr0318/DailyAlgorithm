import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210216_BOJ_S2_1802_종이접기 {
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String str = br.readLine();
			int s = 0, e = str.length() - 1;
			boolean check = true;
			while (s < e) {
				int left = s;
				int right = e;
				while(left < right) {
					if(str.charAt(left) == str.charAt(right)) {
						check = false;
						break;
					}
					left++;
					right--;
				}
				e = right - 1;
			}
			if (check)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		System.out.println(sb);
	}
}
