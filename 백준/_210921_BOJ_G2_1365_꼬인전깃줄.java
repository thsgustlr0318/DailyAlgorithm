import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _210921_BOJ_G2_1365_꼬인전깃줄 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		ArrayList<Integer> lis = new ArrayList<>();
		for (int index = 0; index < N; index++) {
			if (lis.isEmpty() || lis.get(lis.size() - 1) < arr[index])
				lis.add(arr[index]);
			else {
				int left = 0, right = lis.size() - 1;
				while (left < right) {
					int mid = (left + right) / 2;
					if (lis.get(mid) > arr[index]) {
						right = mid - 1;
					} else {
						left = mid;
					}
				}
				lis.set(left, arr[index]);
			}
		}
		System.out.println(N - lis.size());
	}
}
