import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210326_BOJ_G2_17472_다리만들기2 {
	static int N, M;
	static int moveDir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] islandMap = new int[N][M];
		int islandCount = 1;

		//섬 번호 매기기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && islandMap[i][j] == 0) {
					Queue<info> q = new LinkedList<>();
					q.add(new info(i, j));
					islandMap[i][j] = islandCount++;
					while (!q.isEmpty()) {
						info cur = q.poll();
						int y = cur.y, x = cur.x;
						for (int dir = 0; dir < 4; dir++) {
							int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
							if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 0 || islandMap[ny][nx] > 0)
								continue;
							islandMap[ny][nx] = islandMap[y][x];
							q.add(new info(ny, nx));
						}
					}
				}
			}
		}
		PriorityQueue<bridgeInfo> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (islandMap[i][j] > 0) {
					int islandNumber = islandMap[i][j];
					for (int dir = 0; dir < 4; dir++) {
						int y = i, x = j;
						int distance = 0;
						while (true) {
							int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
							if (ny < 0 || nx < 0 || ny >= N || nx >= M || islandMap[ny][nx] == islandNumber)
								break;
							if (islandMap[ny][nx] == 0) {
								distance++;
							} else {
								if (distance >= 2)
									pq.add(new bridgeInfo(islandNumber, islandMap[ny][nx], distance));
								break;
							}
							y = ny;
							x = nx;
						}
					}
				}
			}
		}
		islandCount--;
		int answer = 0;
		parent = new int[islandCount + 1];
		for (int i = 1; i <= islandCount; i++)
			parent[i] = i;
		while (!pq.isEmpty()) {
			bridgeInfo cur = pq.poll();
			if (!find(cur.start, cur.end)) {
				union(cur.start, cur.end);
				answer += cur.distance;
			}
		}
		boolean check = true;
		for (int i = 1; i < islandCount; i++)
			if (getParent(parent[i]) != getParent(parent[i + 1]))
				check = false;
		if (!check)
			System.out.println("-1");
		else
			System.out.println(answer);
	}

	private static class info {
		int y, x;

		info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static class bridgeInfo implements Comparable<bridgeInfo> {
		int start, end, distance;

		public bridgeInfo(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(bridgeInfo o) {
			return this.distance - o.distance;
		}
	}

	private static int getParent(int a) {
		if (a == parent[a])
			return a;
		return parent[a] = getParent(parent[a]);
	}

	private static void union(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	private static boolean find(int a, int b) {
		if (getParent(a) == getParent(b))
			return true;
		return false;
	}
}
