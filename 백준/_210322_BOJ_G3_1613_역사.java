import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210322_BOJ_G3_1613_역사 {
	static int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n, k, s;
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n + 1][n + 1];
		for (int i = 0; i < k; i++) {
			int a, b;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
		}
		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				for (int end = 1; end <= n; end++) {
					if (arr[start][mid] == 1 && arr[mid][end] == 1) {
						arr[start][end] = 1;
					}
				}
			}
		}
		s = Integer.parseInt(br.readLine());
		for (int i = 0; i < s; i++) {
			int a, b;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(arr[a][b] == 1) {
				sb.append("-1\n");
			}else if(arr[a][b] == 0) {
				if(arr[b][a] == 1)
					sb.append("1\n");
				else
					sb.append("0\n");
			}
		}
		System.out.println(sb);
	}
}
