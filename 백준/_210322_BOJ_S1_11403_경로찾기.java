import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210322_BOJ_S1_11403_경로찾기 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int mid = 0; mid < N; mid++) {
			for (int start = 0; start < N; start++) {
				for (int end = 0; end < N; end++) {
					if (arr[start][mid]==1 && arr[mid][end] == 1)
						arr[start][end] = 1;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append(arr[i][j] + " ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
