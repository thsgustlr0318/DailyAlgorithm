import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210225_BOJ_B1_3985_롤케이크 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int maxv = 0, maxvIdx = 0, maxcnt = 0, maxcntIdx = 0;
		int[] arr = new int[L + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (b - a > maxv) {
				maxvIdx = i;
				maxv = b - a;
			}
			int cnt = 0;
			for (int j = a; j <= b; j++) {
				if (arr[j] == 0) {
					arr[j] = i;
					cnt++;
				}
				if(maxcnt < cnt) {
					maxcnt = cnt;
					maxcntIdx = i;
				}
			}
		}
		System.out.println(maxvIdx);
		System.out.println(maxcntIdx);
	}
}
