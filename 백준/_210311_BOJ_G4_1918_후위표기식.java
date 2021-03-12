import java.util.Scanner;
import java.util.Stack;

public class _210311_BOJ_G4_1918_후위표기식 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		Stack<Character> stack = new Stack<>();
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) == '+' || str.charAt(index) == '-') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.add(str.charAt(index));
			} else if (str.charAt(index) == '*' || str.charAt(index) == '/') {
				if (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
					sb.append(stack.pop());
				}
				stack.add(str.charAt(index));
			} else if (str.charAt(index) == '(') {
				stack.add(str.charAt(index));
			} else if (str.charAt(index) == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					sb.append(stack.pop());
				stack.pop();
			} else {
				sb.append(str.charAt(index));
			}
		}
		while (!stack.isEmpty()) {
			if (stack.peek() != '(')
				sb.append(stack.peek());
			stack.pop();
		}
		System.out.println(sb);
	}
}
