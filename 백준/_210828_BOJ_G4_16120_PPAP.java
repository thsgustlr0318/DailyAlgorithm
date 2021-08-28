import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _210828_BOJ_G4_16120_PPAP {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = br.readLine();
		Stack<Character> stack = new Stack<>();
		int pCount = 0;
		for (int index = 0; index < string.length(); index++) {
			if (string.charAt(index) == 'P') {
				stack.add('P');
				pCount++;
			} else if (string.charAt(index) == 'A') {
				if (index + 1 < string.length() && string.charAt(index + 1) == 'P' && pCount >= 2) {
					stack.pop();
					stack.pop();
					stack.add('P');
					index++;
					pCount--;
				} else {
					pCount = 0;
					stack.add('A');
				}
			}
		}
		if (stack.size() == 1 && stack.peek() == 'P')
			System.out.println("PPAP");
		else
			System.out.println("NP");
	}
}
