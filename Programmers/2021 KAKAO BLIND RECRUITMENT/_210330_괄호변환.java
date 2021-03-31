import java.util.LinkedList;
import java.util.Queue;

public class _210330_괄호변환 {
	public static void main(String[] args) {
		String p = "()))((()";
		System.out.println(solution(p));
	}

	public static String solution(String p) {
		return solve(p);
	}

	private static String solve(String str) {
		//1
		if (str.isEmpty())
			return "";
		//2
		String u = "", v = "";
		int index = 0, cnt = 0;
		for (index = 0; index < str.length(); index++) {
			if (str.charAt(index) == '(')
				cnt++;
			else
				cnt--;
			u += str.charAt(index);
			if (cnt == 0)
				break;
		}
		v = str.substring(index + 1);
		//3
		if (check(u))
			return u + solve(v);
		//4
		String temp = "(" + solve(v) + ")";
		for (int idx = 1; idx < u.length() - 1; idx++) {
			if (u.charAt(idx) == '(')
				temp += ")";
			else
				temp += "(";
		}
		return temp;
	}

	private static boolean check(String str) {
		int cnt = 0;
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) == '(')
				cnt++;
			else
				cnt--;
			if (cnt < 0)
				return false;
		}
		return true;
	}
}
