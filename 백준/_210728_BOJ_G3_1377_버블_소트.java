import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _210728_BOJ_G3_1377_버블_소트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		info[] arr = new info[N];
		for (int index = 0; index < N; index++) {
			int num = Integer.parseInt(br.readLine());
			arr[index] = new info(num, index);
		}
		// 버블 정렬 한번 수행할 때마다 최대 1칸 앞으로 올 수 있음
		// 정렬 후에 index가 가장 앞으로 온 값 + 1이 정답	
		Arrays.sort(arr, (o1, o2) -> {
			return o1.num - o2.num;
		});
		int result = 0;
		for (int index = 0; index < N; index++) {
			int diff = arr[index].index - index;
			if (diff > 0)
				result = Math.max(result, diff);
		}
		System.out.println(result + 1);
	}

	public static class info {
		int num, index;

		info(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}
}
