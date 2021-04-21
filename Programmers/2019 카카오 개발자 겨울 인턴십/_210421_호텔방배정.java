import java.util.HashMap;

public class _210421_호텔방배정 {
	static HashMap<Long, Long> map = new HashMap<>();

	public static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		for (int index = 0; index < room_number.length; index++) {
			answer[index] = getParents(room_number[index]);
		}
		return answer;
	}

	private static long getParents(long index) {
		if (!map.containsKey(index)) {
			map.put(index, index + 1);
			return index;
		}
		map.put(index, getParents(map.get(index)));
		return map.get(index);
	}

	public static void main(String[] args) {
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		solution(k, room_number);
	}
}
