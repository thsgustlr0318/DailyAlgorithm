package _2017_팁스타운;

import java.util.Arrays;

public class 단어_퍼즐 {
	public static int solution(String[] strs, String t) {
		int[] dp = new int[20001];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		for(String str:strs) {
			if(str.length() > t.length())
				continue;
			if(t.substring(0, str.length()).equals(str))
				dp[str.length()] = 1;
		}
		for (int index = 0; index <= t.length(); index++) {
			for (String str : strs) {
				if (index - str.length() < 0)
					continue;
				String cur = t.substring(index - str.length(), index);
				if (cur.equals(str) && dp[index - str.length()] != -1) {
					if (dp[index] == -1)
						dp[index] = dp[index - str.length()] + 1;
					else
						dp[index] = Math.min(dp[index], dp[index - str.length()] + 1);
				}
			}
		}
		return dp[t.length()] == -1 ? -1 : dp[t.length()];
	}

	public static void main(String[] args) {
		String[] strs = { "a" };
		String t = "aa";
		System.out.println(solution(strs, t));
	}
}
