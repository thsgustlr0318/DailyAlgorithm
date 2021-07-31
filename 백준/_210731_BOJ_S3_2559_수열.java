import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210731_BOJ_S3_2559_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++)
			arr[index] = Integer.parseInt(st.nextToken());
		int start = 0, end = 0, sum = 0;
		while (end != K)
			sum += arr[end++];
		int result = sum;
		for(; end < N;) {
			sum -= arr[start++];
			sum += arr[end++];
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}
}
