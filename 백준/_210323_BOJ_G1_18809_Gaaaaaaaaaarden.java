import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210323_BOJ_G1_18809_Gaaaaaaaaaarden {
	private static class info {
		int y, x;

		info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	//INF: 꽃이 생긴 자리
	static int INF = 1000000000;
	static int N, M, G, R, fuelCnt = 0, res = 0;
	//candidates: 배양액을 놓을 수 있는 후보
	static info[] candidates;
	static int[][] map;
	//selected[i]: 0 = 배양액 X, 1 = 빨간 배양액 선택, 2 = 초록 배양액 선택
	static int[] selected;
	static int moveDir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		candidates = new info[10];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//배양액 놓을 수 있는 자리 후보
				if (map[i][j] == 2) {
					candidates[fuelCnt++] = new info(i, j);
				}
			}
		}
		selected = new int[fuelCnt];
		//빨간 배양액 고르기
		selectRed(0, 0);
		System.out.println(res);
	}

	private static void selectRed(int cnt, int idx) {
		if (cnt == R) {
			//초록 배양액 고르기
			selectGreen(0, 0);
			return;
		}
		for (int i = idx; i < fuelCnt; i++) {
			if (selected[i] > 0)
				continue;
			selected[i] = 1;
			selectRed(cnt + 1, i + 1);
			selected[i] = 0;
		}
	}

	private static void selectGreen(int cnt, int idx) {
		if (cnt == G) {
			Queue<info> q = new LinkedList<>();
			int[][] visited = new int[N][M];
			int flowerCnt = 0;
			for (int i = 0; i < selected.length; i++) {
				//빨간 배양액
				if (selected[i] == 1) {
					q.add(candidates[i]);
					visited[candidates[i].y][candidates[i].x] = 1;
				}
				//초록 배양액
				else if (selected[i] == 2) {
					q.add(candidates[i]);
					visited[candidates[i].y][candidates[i].x] = -1;
				}
			}
			//BFS
			while (!q.isEmpty()) {
				info cur = q.poll();
				int y = cur.y;
				int x = cur.x;
				//꽃이 생긴 자리면, continue
				if (visited[y][x] == INF)
					continue;

				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0];
					int nx = x + moveDir[dir][1];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 0)
						continue;
					//꽃이 생긴 자리 or 같은 색의 배양액이 존재한다면, continue
					if (visited[ny][nx] == INF || (visited[ny][nx] > 0 && visited[y][x] > 0) || (visited[ny][nx] < 0 && visited[y][x] < 0))
						continue;

					//배양액이 처음으로 들어가는 경우
					if (visited[ny][nx] == 0) {
						//빨간 배양액
						if (visited[y][x] > 0)
							visited[ny][nx] = visited[y][x] + 1;
						//초록 배양액
						else
							visited[ny][nx] = visited[y][x] - 1;
						q.add(new info(ny, nx));
					}
					//이미 배양액이 존재하는 경우(서로 색이 다른 배양액)
					else {
						//초록색과 빨간색 배양액이 동일한 시간에 도달한 경우
						if (Math.abs(visited[y][x]) + 1 == Math.abs(visited[ny][nx])) {
							//꽃 생김
							visited[ny][nx] = INF;
							flowerCnt++;
						}
					}
				}
			}
			res = Math.max(res, flowerCnt);
			return;
		}
		for (int i = idx; i < fuelCnt; i++) {
			if (selected[i] > 0)
				continue;
			selected[i] = 2;
			selectGreen(cnt + 1, i + 1);
			selected[i] = 0;
		}
	}
}
