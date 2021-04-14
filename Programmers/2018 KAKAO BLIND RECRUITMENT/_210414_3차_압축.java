import java.util.ArrayList;
import java.util.HashMap;

public class _210414_3차_압축 {
	public static int[] solution(String msg) {
		int[] answer = {};
		ArrayList<Integer> res = new ArrayList<>();
		int index = 1;
		HashMap<String, Integer> map = new HashMap<>();
		for (char c = 'A'; c <= 'Z'; c++)
			map.put(String.valueOf(c), index++);

		for (int i = 0; i < msg.length();) {
			StringBuilder key = new StringBuilder();
			key.append(msg.charAt(i++));

			while (true) {
				if (i < msg.length())
					key.append(msg.charAt(i++));
				else {
					res.add(map.get(key.toString()));
					break;
				}
				if (!map.containsKey(key.toString())) {
					map.put(key.toString(), index++);
					key = key.deleteCharAt(key.length() - 1);
					res.add(map.get(key.toString()));
					i--;
					break;
				}
			}
		}
		answer = new int[res.size()];
		for (int i = 0; i < res.size(); i++)
			answer[i] = res.get(i);
		return answer;
	}

	public static void main(String[] args) {
		String msg = "KAKAO";
		System.out.println(solution(msg));
	}
}
