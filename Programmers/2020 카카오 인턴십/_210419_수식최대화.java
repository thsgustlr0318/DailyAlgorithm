import java.util.ArrayList;

public class _210419_수식최대화 {
	public static long solution(String expression) {
		long answer = 0;
		String[] nums = expression.split("[^0-9]");
		char[] ops = expression.replaceAll("[0-9]", "").toCharArray();
		ArrayList<Long> numbers = new ArrayList<>();
		ArrayList<Character> operators = new ArrayList<>();
		for (int i = 0; i < nums.length; i++)
			numbers.add(Long.parseLong(nums[i]));
		for (int i = 0; i < ops.length; i++)
			operators.add(ops[i]);

		answer = Math.max(answer, operation(numbers, operators, '+', '*', '-'));
		answer = Math.max(answer, operation(numbers, operators, '+', '-', '*'));
		answer = Math.max(answer, operation(numbers, operators, '*', '+', '-'));
		answer = Math.max(answer, operation(numbers, operators, '*', '-', '+'));
		answer = Math.max(answer, operation(numbers, operators, '-', '*', '+'));
		answer = Math.max(answer, operation(numbers, operators, '-', '+', '*'));

		return answer;
	}

	private static long operation(ArrayList<Long> num, ArrayList<Character> op, char c, char d, char e) {
		ArrayList<Long> numbers = new ArrayList<>();
		ArrayList<Character> operators = new ArrayList<>();
		for (int i = 0; i < num.size(); i++)
			numbers.add(num.get(i));
		for (int i = 0; i < op.size(); i++)
			operators.add(op.get(i));

		calc(numbers, operators, c);
		calc(numbers, operators, d);
		calc(numbers, operators, e);

		return Math.abs(numbers.get(0));
	}

	private static void calc(ArrayList<Long> numbers, ArrayList<Character> operators, char op) {
		for (int index = 0; index < operators.size(); index++) {
			if (operators.get(index) == op) {
				long temp = 0;
				if (operators.get(index) == '+') {
					temp = numbers.get(index) + numbers.get(index + 1);
				} else if (operators.get(index) == '*') {
					temp = numbers.get(index) * numbers.get(index + 1);
				} else {
					temp = numbers.get(index) - numbers.get(index + 1);
				}
				numbers.set(index, temp);
				numbers.remove(index + 1);
				operators.remove(index);
				index--;
			}
		}
	}

	public static void main(String[] args) {
		String expression = "50*6-3*2";
		System.out.println(solution(expression));
	}
}
