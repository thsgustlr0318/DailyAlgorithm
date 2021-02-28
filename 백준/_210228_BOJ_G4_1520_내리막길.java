import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210228_BOJ_G4_1520_내리막길 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M, res = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] visited = new int[N][M];
		int[][] moveDir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//check: false->빙산이 다 녹을때 까지 분리되지 않음
		boolean check;
		while (true) {
			//빙산 녹이기
			int cnt = 0;
			check = false;
			//visited배열 초기화
			for (int i = 1; i < N - 1; i++)
				for (int j = 1; j < M - 1; j++)
					visited[i][j] = 0;
			//가장자리 제외 && 빙산이 두덩이 이상이면 탐색 종료
			for (int i = 1; i < N - 1 && cnt < 2; i++) {
				for (int j = 1; j < M - 1 && cnt < 2; j++) {
					if (map[i][j] == 0 || visited[i][j] >= 1)
						continue;
					//빙산이 있으면 탐색
					Queue<int[]> q = new LinkedList<int[]>();
					q.add(new int[] { i, j });
					//cnt: 빙산 덩어리 개수
					cnt++;
					visited[i][j] = cnt;
					check = true;
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int y = cur[0], x = cur[1];
						int seacnt = 0;
						for (int dir = 0; dir < 4; dir++) {
							int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
							if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] >= 1)
								continue;
							//바다와 맞다은 개수 탐색
							if (map[ny][nx] == 0) {
								seacnt++;
								continue;
							}
							visited[ny][nx] = cnt;
							q.add(new int[] { ny, nx });
						}
						map[y][x] -= seacnt;
						//빙산은 0보다 줄어들지 않음
						if (map[y][x] < 0)
							map[y][x] = 0;
					}
				}
			}
			//덩어리가 두개 이상인 경우
			if (cnt >= 2)
				break;
			//빙산이 다 녹을때까지 두개 이상 분리되지 않은 경우
			if(!check)
				break;
			res++;
		}
		if (!check)
			System.out.println("0");
		else
			System.out.println(res);
	}
}
