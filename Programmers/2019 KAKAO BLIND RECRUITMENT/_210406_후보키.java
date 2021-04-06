import java.util.ArrayList;
import java.util.HashSet;

public class _210406_후보키 {
	static int row, col;
	static ArrayList<Integer> key;

	public static int solution(String[][] relation) {
		int answer = 0;
		row = relation.length;
		col = relation[0].length;
		key = new ArrayList<>();
		int[] candidates = new int[col];
		for (int i = 0; i < col; i++)
			candidates[i] = i;
		//유일성 찾기
		dfs(candidates, new ArrayList<>(), 0, relation);
		//최소성 찾기
		for (int i = 0; i < key.size(); i++) {
			boolean check = true;
			for (int j = 0; j < key.size(); j++) {
				if (i == j)
					continue;
				if ((key.get(i) & key.get(j)) == key.get(j)) {
					check = false;
					break;
				}
			}
			if (check)
				answer++;
		}
		return answer;
	}

	private static void dfs(int[] arr, ArrayList<Integer> selected, int index, String[][] relation) {
		if (!selected.isEmpty()) {
			int size = selected.size();
			HashSet<String> set = new HashSet<>();
			boolean check = true;
			for (int i = 0; i < row; i++) {
				//튜플 생성
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < size; j++) 
					sb.append(relation[i][selected.get(j)]);
				//만약 튜플이 이미 존재한다면, 유일성 만족 시키지 못함
				if (set.contains(sb.toString())) {
					check = false;
					break;
				}
				//set에 새로 만든 튜플 저장
				set.add(sb.toString());
			}
			if (check) {
				//유일성을 만족하는 속성에 대한 bitmasking
				int bits = 0;
				for (int i = 0; i < size; i++) 
					bits |= (1 << selected.get(i));
				key.add(bits);
			}
		}

		for (int i = index; i < col; i++) {
			selected.add(arr[i]);
			dfs(arr, selected, i + 1, relation);
			selected.remove(selected.size() - 1);
		}
	}

	public static void main(String[] args) {
		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" }, { "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" }, { "600", "apeach", "music", "2" } };
		solution(relation);
	}
}
