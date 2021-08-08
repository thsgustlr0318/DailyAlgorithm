import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210808_BOJ_G5_2212_센서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		// 오름차순 정렬
		Arrays.sort(arr);
		// 최대 길이
		int maxLength = arr[arr.length - 1] - arr[0];
		// 두 구간 사이의 거리 구하기
		PriorityQueue<Integer> length = new PriorityQueue<>((o1, o2) -> {
			return o2 - o1;
		});
		for (int i = 0; i < N - 1; i++) {
			int diff = arr[i + 1] - arr[i];
			length.add(diff);
		}
		// K-1개를 빼기
		for (int i = 0; i < K - 1; i++) {
			if (!length.isEmpty())
				maxLength -= length.poll();
		}
		System.out.println(maxLength);
	}
}
