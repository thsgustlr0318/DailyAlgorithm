package _2017_팁스타운;

import java.util.Stack;

public class 짝지어_제거하기 {
	public static int solution(String s) {
		Stack<Character> stack = new Stack<>();
		s = s.replaceAll("^[a-z]{2}", "");
		for (int index = 0; index < s.length(); index++) {
			if (!stack.isEmpty() && stack.peek() == s.charAt(index))
				stack.pop();
			else
				stack.add(s.charAt(index));
		}
		return stack.isEmpty() ? 1 : 0;
	}

	public static void main(String[] args) {
		String s = "baabaa";
		System.out.println(solution(s));
	}
}
