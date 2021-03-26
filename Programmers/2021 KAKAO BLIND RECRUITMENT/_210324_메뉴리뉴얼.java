import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class _210324_메뉴리뉴얼 {
	static HashMap<String, Integer> map;
	static int maxLen = 0;

	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};

		ArrayList<String> candidates = new ArrayList<>();
		for (int i = 0; i < course.length; i++) {
			map = new HashMap<>();
			maxLen = 0;
			for (int j = 0; j < orders.length; j++) {
				//orders[j]메뉴에 대해서 course[i]크기 만큼 만들 수 있는 코스 요리 개수 구하기
				getMenu(0, 0, "", orders[j], course[i]);
			}
			if(maxLen < 2)
				continue;
			//모든 map 후보 확인
			for (String cur : map.keySet()) {
				if(map.get(cur) == maxLen)
					candidates.add(cur);
			}
		}
		answer = new String[candidates.size()];
		Collections.sort(candidates);
		for(int index=0; index<candidates.size(); index++) {
			answer[index] = candidates.get(index);
		}
		return answer;
	}

	private static void getMenu(int cnt, int index, String newMenu, String menu, int targetNum) {
		if (cnt == targetNum) {
			//메뉴 이름 오름차순으로 만들기
			char[] temp = newMenu.toCharArray();
			Arrays.sort(temp);
			newMenu = new String(temp);
			//map에 메뉴 이름에 대한 value 1 더하기
			map.put(newMenu, map.getOrDefault(newMenu, 0) + 1);
			//가장 많이 나온 코스요리의 크기 구하기
			maxLen = Math.max(maxLen, map.get(newMenu));
			return;
		}
		for (int i = index; i < menu.length(); i++) {
			getMenu(cnt + 1, i + 1, newMenu + menu.charAt(i), menu, targetNum);
		}
	}
}