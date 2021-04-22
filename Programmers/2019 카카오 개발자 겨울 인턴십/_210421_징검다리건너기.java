import java.util.HashMap;
import java.util.TreeSet;

public class _210421_징검다리건너기 {
	public static int solution(int[] stones, int k) {
		int answer = Integer.MAX_VALUE;
		HashMap<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> tree = new TreeSet<>();
		int left = 0, right = 0;
		while (right < k) {
			map.put(stones[right], map.getOrDefault(stones[right], 0) + 1);
			tree.add(stones[right]);
			right++;
		}
		for (; left + k < stones.length;) {
			//갱신
			answer = Math.min(answer, tree.last());
			//right 넣기
			map.put(stones[right], map.getOrDefault(stones[right], 0) + 1);
			tree.add(stones[right]);
			right++;
			//left 빼기
			map.put(stones[left], map.getOrDefault(stones[left], 0) - 1);
			if (map.get(stones[left]) == 0) {
				map.remove(stones[left]);
				tree.remove(stones[left]);
			}
			left++;
		}
		answer = Math.min(answer, tree.last());
		return answer;
	}

	public static void main(String[] args) {
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int k = 3;
		System.out.println(solution(stones, k));
	}
}
