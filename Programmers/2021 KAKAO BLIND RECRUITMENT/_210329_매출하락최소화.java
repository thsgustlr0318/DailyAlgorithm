import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class _210329_매출하락최소화 {
	static int[][] dp;
	static int[] salesInfo;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) {
		int[] sales = { 5, 6, 5, 1, 4 };
		int[][] links = { { 2, 3 }, { 1, 4 }, { 2, 5 }, { 1, 2 } };
		solution(sales, links);
	}

	public static int solution(int[] sales, int[][] links) {
		int salesLength = sales.length;
		int linksLength = salesLength - 1;
		dp = new int[salesLength + 1][2];
		adj = new ArrayList[salesLength + 1];
		salesInfo = sales;

		for (int i = 1; i <= salesLength; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < linksLength; i++)
			adj[links[i][0]].add(links[i][1]);
		for (int i = 1; i <= salesLength; i++)
			dp[i][0] = dp[i][1] = -1;

		return Math.min(dfs(1, 0), dfs(1, 1));
	}

	private static int dfs(int num, int include) {
		//기저 조건
		if (dp[num][include] != -1)
			return dp[num][include];
		dp[num][include] = 0;

		//현재 num을 포함하는 경우
		int sumChild = 0;
		if (include == 1) {
			for (int next : adj[num]) {
				sumChild += Math.min(dfs(next, 1), dfs(next, 0));
			}
			return dp[num][include] = (sumChild + salesInfo[num - 1]);
		}
		//현재 num을 포함하지 않는 경우
		else {
			boolean flag = false;
			int minValue = adj[num].size() > 0 ? Integer.MAX_VALUE : 0;
			for (int next : adj[num]) {
				int a = dfs(next, 1);
				int b = dfs(next, 0);
				if (a < b)
					flag = true;
				else
					minValue = Math.min(minValue, Math.abs(a-b));
				sumChild += Math.min(a, b);
			}
			return dp[num][include] = flag ? sumChild : sumChild + minValue;
		}
	}
}
