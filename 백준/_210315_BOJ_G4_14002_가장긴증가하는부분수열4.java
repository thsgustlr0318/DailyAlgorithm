import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _210315_BOJ_G4_14002_가장긴증가하는부분수열4 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] dp = new int[N + 1];
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			int maxv = 0;
			for (int j = 1; j < i; j++) {
				if (arr[i] > arr[j] && dp[j] > maxv)
					maxv = dp[j];
			}
			dp[i] = maxv + 1;
		}
		int res = 0, index = 0;
		for(int i=1; i<=N;i++) {
			if(res < dp[i]){
				index = i;
				res = dp[i];
			}
		}
		sb.append(res+"\n");
		Stack<Integer> s = new Stack<>();
		s.add(arr[index]);
		for(int i = index; i>=1 && res > 0; i--) {
			if(dp[i] == res-1) {
				res--;
				s.add(arr[i]);
			}
		}
		while(!s.isEmpty()) {
			sb.append(s.pop() + " ");
		}
		System.out.println(sb);
			
	}
}
