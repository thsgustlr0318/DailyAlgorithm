import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _210717_BOJ_G5_12851_숨바꼭질2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		if (N == K) {
			System.out.println(0);
			System.out.println(1);
			System.exit(0);
		}
		int[] visited = new int[100001];
		int time = 0;
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		visited[N] = 1;
		queue.add(N);
		// 가장 빠른 시간 찾기
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			if (cur == K) {
				time = visited[K];
				break;
			}
			if (cur - 1 >= 0 && visited[cur - 1] == 0) {
				queue.add(cur - 1);
				visited[cur - 1] = visited[cur] + 1;
			}
			if (cur + 1 <= 100000 && visited[cur + 1] == 0) {
				queue.add(cur + 1);
				visited[cur + 1] = visited[cur] + 1;
			}
			if (cur * 2 <= 100000 && visited[cur * 2] == 0) {
				queue.add(cur * 2);
				visited[cur * 2] = visited[cur] + 1;
			}
		}
		queue.clear();
		// 가장 빠른 시간으로 찾는 방법 개수 구하기
		// 거꾸로 탐색하기
		queue.add(K);
		queue.add(time);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int t = queue.poll();
		
			if (t == 1) {
				count++;
				continue;
			}
			if (cur - 1 >= 0 && visited[cur - 1] == t - 1) {
				queue.add(cur - 1);
				queue.add(t - 1);
			}
			if (cur + 1 <= 100000 && visited[cur + 1] == t - 1) {
				queue.add(cur + 1);
				queue.add(t - 1);
			}
			if (cur % 2 == 0 && cur / 2 != 0 && visited[cur / 2] == t - 1) {
				queue.add(cur / 2);
				queue.add(t - 1);
			}
		}
		System.out.println(time - 1);
		System.out.println(count);
	}
}
