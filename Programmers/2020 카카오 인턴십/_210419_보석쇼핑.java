import java.util.HashMap;
import java.util.HashSet;

public class _210419_보석쇼핑 {
	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		HashSet<String> set = new HashSet<>();
		for (int index = 0; index < gems.length; index++)
			set.add(gems[index]);
		int gemType = set.size();
		int left = 0, right = 0;
		HashMap<String, Integer> map = new HashMap<>();
		while (map.size() != gemType) {
			map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
			++right;
		}
		int startIndex = 1;
		int minLength = right - startIndex;
		//앞에 지우기
		while (map.size() == gemType) {
			map.put(gems[left], map.getOrDefault(gems[left], 0) - 1);
			if (map.get(gems[left]) == 0)
				map.remove(gems[left]);
			++left;
			if (minLength > right - left) {
				minLength = right - left;
				startIndex = left;
			}
		}
		while (right < gems.length) {
			//뒤에 채우기
			while (right < gems.length && map.size() != gemType) {
				map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
				++right;
				if (minLength > right - left) {
					minLength = right - left;
					startIndex = left;
				}
			}
			//앞에 지우기
			while (map.size() == gemType) {
				map.put(gems[left], map.getOrDefault(gems[left], 0) - 1);
				if (map.get(gems[left]) == 0)
					map.remove(gems[left]);
				++left;
				if (minLength > right - left) {
					minLength = right - left;
					startIndex = left;
				}
			}
		}
		answer[0] = startIndex;
		answer[1] = startIndex + minLength;
		return answer;
	}

	public static void main(String[] args) {
		String[] gems = { "AA", "AA", "B" };
		solution(gems);
	}
}
