package 찾아라_프로그래밍_마에스터;

import java.util.HashSet;

public class 포켓몬 {
	public static int solution(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++)
			set.add(nums[i]);
		return set.size() >= nums.length / 2 ? nums.length / 2 : set.size();
	}

	public static void main(String[] args) {
		int[] nums = { 3, 1, 2, 3 };
		System.out.println(solution(nums));
	}
}
