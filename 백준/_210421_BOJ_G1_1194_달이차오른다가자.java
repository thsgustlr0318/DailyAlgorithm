import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210421_BOJ_G1_1194_달이차오른다가자 {
	static int[][] moveDir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static class info {
		int y, x, depth, key;

		info(int y, int x, int depth, int key) {
			this.y = y;
			this.x = x;
			this.depth = depth;
			this.key = key;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M, startY = 0, startX = 0, answer = -1;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//[key][y좌표][x좌표]를 방문 했는지 확인
		boolean[][][] visited = new boolean[1 << 'f' - 'a' + 1][N][M];
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		//시작 위치 찾기
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					startY = i;
					startX = j;
				}
			}
		Queue<info> q = new LinkedList<>();
		q.add(new info(startY, startX, 1, 0));
		visited[0][startY][startX] = true;
		//BFS
		while (!q.isEmpty()) {
			info cur = q.poll();
			int y = cur.y, x = cur.x, depth = cur.depth, key = cur.key;
			//출구를 찾으면, break;
			if (map[y][x] == '1') {
				answer = depth - 1;
				break;
			}

			for (int dir = 0; dir < 4; dir++) {
				int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == '#')
					continue;
				//다음 장소가 열쇠인 경우
				if ('a' <= map[ny][nx] && map[ny][nx] <= 'z') {
					//key에 열쇠 추가
					int nextKey = key | (1 << (map[ny][nx] - 'a'));
					if (visited[nextKey][ny][nx])
						continue;
					q.add(new info(ny, nx, depth + 1, nextKey));
					visited[nextKey][ny][nx] = true;
				} 
				//다음 장소가 문인 경우
				else if ('A' <= map[ny][nx] && map[ny][nx] <= 'Z') {
					//현재 key로 문을 열 수 있는지 확인
					if ((key & (1 << (Character.toLowerCase(map[ny][nx]) - 'a'))) != 0) {
						if (visited[key][ny][nx])
							continue;
						q.add(new info(ny, nx, depth + 1, key));
						visited[key][ny][nx] = true;
					}
				} 
				//빈 곳 or 출구
				else {
					if (visited[key][ny][nx])
						continue;
					q.add(new info(ny, nx, depth + 1, key));
					visited[key][ny][nx] = true;
				}
			}
		}
		System.out.println(answer);
	}
}
