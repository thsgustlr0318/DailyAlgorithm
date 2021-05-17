package 찾아라_프로그래밍_마에스터;

public class 사칙연산 {
	static int answer = Integer.MIN_VALUE;
	static String[] nums;

	public static int solution(String arr[]) {
		nums = arr.clone();
		answer = recursiveCalc(Integer.parseInt(nums[0]), 0);
		return answer;
	}

	private static int recursiveCalc(int total, int index) {
		if (index >= nums.length)
			return 0;
		if (index + 1 < nums.length && nums[index + 1].equals("+"))
			return total + recursiveCalc(Integer.parseInt(nums[index + 2]), index + 2);
		else if (index + 1 < nums.length && nums[index + 1].equals("-")) {
			return Math.min(total - Integer.parseInt(nums[index + 2]) + recursiveCalc(0, index + 2), total - recursiveCalc(Integer.parseInt(nums[index + 2]), index + 2));
		} else {
			return Integer.parseInt(nums[index]);
		}
	}

	public static void main(String[] args) {
		String[] arr = { "1", "-", "3", "+", "5", "-", "8" };
		System.out.println(solution(arr));
	}
}
