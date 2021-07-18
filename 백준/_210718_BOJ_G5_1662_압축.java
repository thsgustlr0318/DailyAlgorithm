import java.util.Scanner;
import java.util.Stack;

public class _210718_BOJ_G5_1662_압축 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		System.out.println(solve(S));
	}

	private static int solve(String s) {
		Stack<Character> stack = new Stack<>();
		for (int index = 0; index < s.length(); index++) {
			char cur = s.charAt(index);
			// 괄호 만나면, K와 Q 구해서 계산
			if (cur == '(') {
				// stack의 마지막 꺼내서 K 구하기
				int K = Character.digit(stack.pop(), 10);
				StringBuilder Q = new StringBuilder();
				// 괄호 갯수
				int count = 1;
				index++;
				while (count != 0) {
					char next = s.charAt(index++);
					if (next == '(')
						count++;
					else if (next == ')')
						count--;
					Q.append(next);
				}
				// Q 마지막 ')' 빼주기
				Q.setLength(Q.length() - 1);
				// stack에 쌓인 크기 + K * 반복될 문자 수 리턴 + 괄호 뒤 문자 계산
				return stack.size() + K * solve(Q.toString()) + solve(s.substring(index, s.length()));
			}
			// 그 외면, stack에 넣기
			else {
				stack.add(cur);
			}
		}
		// 괄호가 없으면, s의 크기 리턴
		return s.length();
	}
}
