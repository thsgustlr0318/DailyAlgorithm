import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _210517_BOJ_G5_21610_마법사_상어와_비바라기 {
	static int[][] moveDir = { {}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		ArrayList<pos> clouds = new ArrayList<>();
		//초기 구름
		clouds.add(new pos(N - 2, 0, map[N - 2][0]));
		clouds.add(new pos(N - 2, 1, map[N - 2][1]));
		clouds.add(new pos(N - 1, 0, map[N - 1][0]));
		clouds.add(new pos(N - 1, 1, map[N - 1][1]));
		//M번 탐색
		while (M-- > 0) {
			int d, s;
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			//구름 이동
			for (int index = 0; index < clouds.size(); index++) {
				pos cur = clouds.get(index);
				int ny = (cur.y + moveDir[d][0] * s) % N, nx = (cur.x + moveDir[d][1] * s) % N;
				if (ny < 0)
					ny = N + ny;
				if (nx < 0)
					nx = N + nx;
				map[ny][nx]++;
				clouds.set(index, new pos(ny, nx, 0));
			}
			//대각선 확인
			//2,4,6,8 방향
			for (int index = 0; index < clouds.size(); index++) {
				int count = 0;
				pos cur = clouds.get(index);
				for (int dir = 2; dir <= 8; dir += 2) {
					int ny = cur.y + moveDir[dir][0], nx = cur.x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 0)
						continue;
					count++;
				}
				map[cur.y][cur.x] += count;
			}
			//구름이 있던 칸 제외
			boolean[][] check = new boolean[N][N];
			for (pos cur : clouds) {
				check[cur.y][cur.x] = true;
			}
			clouds.clear();
			//물의 양이 2 이상인 칸 확인
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] >= 2 && !check[i][j]) {
						clouds.add(new pos(i, j, map[i][j] - 2));
						map[i][j] -= 2;
					}
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				answer += map[i][j];
		System.out.println(answer);
	}

	static class pos {
		int y, x, size;

		pos(int y, int x, int size) {
			this.y = y;
			this.x = x;
			this.size = size;
		}
	}
}
