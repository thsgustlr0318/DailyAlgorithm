
public class _210415_3차_N진수게임 {
	public static String solution(int n, int t, int m, int p) {
		StringBuilder answer = new StringBuilder();
		String converter = "0123456789ABCDEF";
		int num = 0, index = 1;
		while (t > 0) {
			StringBuilder convertedNum = new StringBuilder();
			int curNum = num;
			while (true) {
				convertedNum.append(converter.charAt(curNum % n));
				curNum /= n;
				if (curNum == 0) {
					num++;
					break;
				}
			}
			convertedNum = convertedNum.reverse();
			for (int i = 0; i < convertedNum.length(); i++) {
				if (index == p) {
					answer.append(convertedNum.charAt(i));
					t--;
				}
				index++;
				if (index > m)
					index = 1;
				if (t == 0)
					break;
			}
		}
		return answer.toString();
	}

	public static void main(String[] args) {
		int n = 2, t = 4, m = 2, p = 1;
		System.out.println(solution(n, t, m, p));
	}
}
