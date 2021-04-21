import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class _210420_동굴탐험 {
	public static boolean solution(int n, int[][] path, int[][] order) {
		ArrayList<Integer>[] arr = new ArrayList[n];
		HashMap<Integer, Integer> map = new HashMap<>();
		HashSet<Integer> rooms = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < n; i++)
			arr[i] = new ArrayList<>();
		for (int i = 0; i < path.length; i++) {
			arr[path[i][0]].add(path[i][1]);
			arr[path[i][1]].add(path[i][0]);
		}

		boolean[] canVisit = new boolean[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(canVisit, true);
		for (int i = 0; i < order.length; i++) {
			map.put(order[i][0], order[i][1]);
			canVisit[order[i][1]] = false;
		}
		//0번방 탐색 불가능 할 경우
		if (!canVisit[0])
			return false;
		//Queue에 0번 방 넣기
		q.add(0);
		visited[0] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			visited[cur] = true;
			//현재 방이 먼저 방문해야 되는 방인 경우
			if (map.containsKey(cur)) {
				//나중에 방문해야 되는 방이 set에 있는 경우, queue에 넣고 set에서 삭제
				if (rooms.contains(map.get(cur))) {
					q.add(map.get(cur));
					rooms.remove(map.get(cur));
				}
				canVisit[map.get(cur)] = true;
				map.remove(cur);
			}

			for (int next : arr[cur]) {
				if (visited[next])
					continue;
				if (!canVisit[next]) {
					rooms.add(next);
					continue;
				}
				if (map.containsKey(next)) {
					if (rooms.contains(map.get(next))) {
						q.add(map.get(next));
						rooms.remove(map.get(next));
					}
					canVisit[map.get(next)] = true;
					map.remove(next);
				}
				q.add(next);
			}
		}
		for (int index = 0; index < n; index++)
			if (!visited[index])
				return false;
		return true;
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] path = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] order = { { 8, 5 }, { 6, 7 }, { 4, 1 } };
		System.out.println(solution(n, path, order));
	}
}
