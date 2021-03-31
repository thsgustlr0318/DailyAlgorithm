import java.util.LinkedList;
import java.util.Queue;

public class _210330_문자열압축 {
	public static void main(String[] args) {
		String s = "abcabcdede";
		System.out.println(solution(s));
	}

	public static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		int strLength = s.length();
		for (int curLen = 1; curLen <= strLength; curLen++) {
			Queue<String> q = new LinkedList<>();
			int idx = 0, res = 0;
			while (idx != strLength) {
				StringBuilder temp = new StringBuilder();
				for (int i = 0; i < curLen && idx < strLength; i++) {
					temp.append(s.charAt(idx++));
				}
				q.add(temp.toString());
			}

			while (!q.isEmpty()) {
				String cur = q.poll();
				int cnt = 1;
				while (!q.isEmpty() && q.peek().equals(cur)) {
					cnt++;
					q.poll();
				}
				if (cnt != 1)
					for (; cnt != 0; cnt /= 10)
						res++;
				res += cur.length();
			}
			answer = Math.min(answer, res);
		}
		return answer;
	}
}
