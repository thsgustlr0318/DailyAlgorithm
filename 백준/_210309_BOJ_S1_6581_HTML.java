import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210309_BOJ_S1_6581_HTML {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		String str, prev = null;
		int cnt = 0;
		while ((str = br.readLine()) != null) {
			st = new StringTokenizer(str);
			while (st.hasMoreTokens()) {
				String cur = st.nextToken();
				if (cur.equals("<br>")) {
					sb.append("\n");
					cnt = 0;
				} else if (cur.equals("<hr>")) {
					if (!prev.equals("<hr>")&& !prev.equals("<br>"))
						sb.append("\n");
					sb.append("--------------------------------------------------------------------------------");
					sb.append("\n");
					cnt = 0;
				} else {
					if (cnt + cur.length() + 1> 80) {
						sb.append("\n");
						cnt = 0;
					}
					if(cnt != 0) {
						sb.append(" ");
						cnt++;
					}
					sb.append(cur);
					cnt += cur.length();
				}
				prev = cur;
			}
		}
		System.out.println(sb);
	}
}
