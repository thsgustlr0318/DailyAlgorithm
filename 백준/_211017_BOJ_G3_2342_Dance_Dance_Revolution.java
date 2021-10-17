import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _211017_BOJ_G3_2342_Dance_Dance_Revolution {
	public static int[][][] dp;
	public static ArrayList<Integer> arr;
	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new ArrayList<>();
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			if (num == 0)
				break;
			arr.add(num);
		}
		N = arr.size();
		dp = new int[N + 1][5][5];
		int result = ddr(0, 0, 0);
		System.out.println(result);
	}

	public static int ddr(int index, int left, int right) {
		if (index == N)
			return 0;
		if (dp[index][left][right] != 0)
			return dp[index][left][right];
		int next = arr.get(index);
		int leftScore = ddr(index + 1, next, right) + getWeight(left, next);
		int rightScore = ddr(index + 1, left, next) + getWeight(right, next);

		return dp[index][left][right] = Math.min(leftScore, rightScore);
	}

	public static int getWeight(int start, int end) {
		if (start == 0)
			return 2;
		if (start == end)
			return 1;
		if (Math.abs(end - start) == 2)
			return 4;
		return 3;
	}
}
