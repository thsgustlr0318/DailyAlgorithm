import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210724_BOJ_G4_16197_두_동전 {
	static int[][] moveDir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		// visited[y1][x1][y2][x2] : 동전 두 개(y1, x1), (y2, x2)의 방문 여부 처리
		int[][][][] visited = new int[N][M][N][M];
		// 두 동전 위치 찾기
		int y1 = -1, x1 = -1, y2 = -1, x2 = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'o') {
					if (y1 == -1) {
						y1 = i;
						x1 = j;
					} else {
						y2 = i;
						x2 = j;
					}
				}
			}
		}
		// BFS
		Queue<pos> queue = new LinkedList<>();
		queue.add(new pos(y1, x1));
		queue.add(new pos(y2, x2));
		visited[y1][x1][y2][x2] = 1;
		while (!queue.isEmpty()) {
			pos coin1 = queue.poll();
			pos coin2 = queue.poll();

			// 버튼 10번보다 많이 누르면, -1
			if (visited[coin1.y][coin1.x][coin2.y][coin2.x] > 10)
				break;
			for (int dir = 0; dir < 4; dir++) {
				// 두 동전의 다음 위치 찾기
				int ny1 = coin1.y + moveDir[dir][0], nx1 = coin1.x + moveDir[dir][1];
				int ny2 = coin2.y + moveDir[dir][0], nx2 = coin2.x + moveDir[dir][1];
				// 동전이 밖으로 떨어지는 개수 구하기
				int outCount = 0;
				if (isCoinOut(ny1, nx1)) {
					outCount++;
				} else {
					// 벽인 경우
					if (map[ny1][nx1] == '#') {
						ny1 = coin1.y;
						nx1 = coin1.x;
					}
				}
				if (isCoinOut(ny2, nx2)) {
					outCount++;
				} else {
					// 벽인 경우
					if (map[ny2][nx2] == '#') {
						ny2 = coin2.y;
						nx2 = coin2.x;
					}
				}
				// 동전이 모두 나가는 경우
				if (outCount == 2)
					continue;
				// 동전이 하나만 나가는 경우, 정답
				if (outCount == 1) {
					System.out.println(visited[coin1.y][coin1.x][coin2.y][coin2.x]);
					System.exit(0);
				}
				// coin1이 coin2보다 왼쪽 위에 있도록 설정
				if ((ny1 > ny2) || (ny1 == ny2 && nx1 > nx2)) {
					int tempy = ny1, tempx = nx1;
					ny1 = ny2;
					nx1 = nx2;
					ny2 = tempy;
					nx2 = tempx;
				}
				if (visited[ny1][nx1][ny2][nx2] != 0)
					continue;
				visited[ny1][nx1][ny2][nx2] = visited[coin1.y][coin1.x][coin2.y][coin2.x] + 1;
				queue.add(new pos(ny1, nx1));
				queue.add(new pos(ny2, nx2));
			}
		}
		System.out.println(-1);
	}

	static boolean isCoinOut(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= M)
			return true;
		return false;
	}

	static class pos {
		int y, x;

		pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
