import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _210709_BOJ_G5_20056_마법사_상어와_파이어볼 {
	static int[][] moveDir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int N, M, K;
	static ArrayList<fireball>[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				arr[i][j] = new ArrayList<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			arr[r][c].add(new fireball(m, s, d, 0));
		}
		for (int time = 0; time < K; time++) {
			// 파이어볼 이동
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					// 비어 있으면, continue
					if (arr[y][x].isEmpty())
						continue;
					for (int index = 0; index < arr[y][x].size(); index++) {
						// 다음 이동할 파이어볼인 경우, continue
						if (arr[y][x].get(index).time != time)
							continue;
						fireball cur = arr[y][x].get(index);
						// 새로운 파이어볼 넣기
						addNewFireball(cur.m, cur.s, cur.d, cur.time, y, x, true);
						// 이전 파이어볼  삭제
						arr[y][x].remove(index);
						index--;
					}
				}
			}
			// 파이어볼 폭발
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					if (arr[y][x].size() >= 2) {
						int mass = 0, speed = 0;
						for (fireball cur : arr[y][x]) {
							mass += cur.m;
							speed += cur.s;
						}
						// 질량이 0인 파이어볼 소멸
						if ((mass /= 5) == 0) {
							arr[y][x].clear();
							continue;
						}
						// 속력: 속력의 합 / 파이어볼 개수
						speed /= arr[y][x].size();
						// 모두 같은 방향인 경우
						if (isSameDir(arr[y][x])) {
							arr[y][x].clear();
							addNewFireball(mass, speed, 0, time, y, x, false);
							addNewFireball(mass, speed, 2, time, y, x, false);
							addNewFireball(mass, speed, 4, time, y, x, false);
							addNewFireball(mass, speed, 6, time, y, x, false);
						} else {
							arr[y][x].clear();
							addNewFireball(mass, speed, 1, time, y, x, false);
							addNewFireball(mass, speed, 3, time, y, x, false);
							addNewFireball(mass, speed, 5, time, y, x, false);
							addNewFireball(mass, speed, 7, time, y, x, false);
						}
					}
				}
			}
		}
		int result = 0;
		for (int y = 1; y <= N; y++)
			for (int x = 1; x <= N; x++) {
				if (!arr[y][x].isEmpty()) {
					for (fireball cur : arr[y][x]) {
						result += cur.m;
					}
				}
			}
		System.out.println(result);
	}

	private static boolean isSameDir(ArrayList<fireball> arrayList) {
		int check = arrayList.get(0).d & 1;
		for (fireball cur : arrayList) {
			if (check != (cur.d & 1))
				return false;
		}
		return true;
	}

	static void addNewFireball(int m, int s, int d, int time, int y, int x, boolean move) {
		// move: true = 이동, false: 이전 그 자리
		int ny = y + (move ? s * moveDir[d][0] : 0);
		int nx = x + (move ? s * moveDir[d][1] : 0);
		if (ny > N)
			ny %= N;
		if (ny <= 0)
			ny = (N + (ny % N)) % (N + 1);
		if (nx > N)
			nx %= N;
		if (nx <= 0)
			nx = (N + (nx % N)) % (N + 1);
		arr[ny][nx].add(new fireball(m, s, d, time + 1));
	}

	static class fireball {
		int m, s, d, time;

		fireball(int m, int s, int d, int time) {
			this.m = m;
			this.s = s;
			this.d = d;
			this.time = time;
		}
	}
}
