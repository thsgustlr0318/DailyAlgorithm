import java.util.LinkedList;

public class _210412_캐시 {
	public static int solution(int cacheSize, String[] cities) {
		if (cacheSize == 0)
			return 5 * cities.length;
		int answer = 0;
		LinkedList<String> cache = new LinkedList<>();
		for (int index = 0; index < cities.length; index++) {
			String cur = cities[index].toLowerCase();

			if (cache.size() < cacheSize || cache.contains(cur)) {
				if (cache.contains(cur)) {
					answer += 1;
					cache.remove(cache.indexOf(cur));
					cache.addFirst(cur);
				} else {
					answer += 5;
					cache.addFirst(cur);
				}

			} else {
				answer += 5;
				cache.removeLast();
				cache.addFirst(cur);
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int cacheSize = 2;
		String[] cities = { "Jeju", "Pangyo", "NewYork", "newyork" };
		System.out.println(solution(cacheSize, cities));
	}
}
