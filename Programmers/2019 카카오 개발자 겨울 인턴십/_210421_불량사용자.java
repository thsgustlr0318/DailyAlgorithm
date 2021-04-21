import java.util.ArrayList;
import java.util.HashSet;

public class _210421_불량사용자 {
	static int answer = 0;
	static HashSet<Integer> set = new HashSet<>();

	public static int solution(String[] user_id, String[] banned_id) {
		ArrayList<Integer>[] candidates = new ArrayList[banned_id.length];
		for (int i = 0; i < banned_id.length; i++)
			candidates[i] = new ArrayList<>();
		for (int i = 0; i < banned_id.length; i++) {
			String banID = banned_id[i];
			for (int j = 0; j < user_id.length; j++) {
				String userID = user_id[j];
				if (check(userID, banID)) {
					candidates[i].add(j);
				}
			}
		}
		dfs(candidates, 0, new boolean[user_id.length], new int[banned_id.length]);
		return set.size();
	}

	private static void dfs(ArrayList<Integer>[] candidates, int count, boolean[] visited, int[] selected) {
		if (count == candidates.length) {
			int bits = 0;
			for (int i : selected) {
				bits |= (1 << i);
			}
			set.add(bits);
			return;
		}
		for (int index : candidates[count]) {
			if (visited[index])
				continue;
			visited[index] = true;
			selected[count] = index;
			dfs(candidates, count + 1, visited, selected);
			visited[index] = false;
		}
	}

	private static boolean check(String a, String b) {
		if (a.length() != b.length())
			return false;
		for (int i = 0; i < a.length(); i++)
			if (b.charAt(i) != '*' && (a.charAt(i) != b.charAt(i)))
				return false;
		return true;
	}

	public static void main(String[] args) {
		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] banned_id = { "fr*d*", "abc1**" };
		System.out.println(solution(user_id, banned_id));
	}
}
