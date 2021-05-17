import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _210517_BOJ_G2_21611_마법사_상어와_블리자드 {
	static int[][] moveDir = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] moveDirOrder = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

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
		//상어 좌표
		int sy = (N + 1) / 2 - 1, sx = (N + 1) / 2 - 1;
		int[] answer = new int[4];
		boolean[][] visited = new boolean[N][N];
		//order: 구슬 좌표 순서
		pos[] order = new pos[N * N - 1];
		int num = 1, y = 0, x = 0, dir = 0, lastIndex = N * N - 1;
		order[lastIndex - num] = new pos(y, x);
		visited[y][x] = true;
		//구슬 좌표 순서 구하기
		while (true) {
			int count = 0;
			boolean check = true;
			while (true) {
				int ny = y + moveDirOrder[dir][0], nx = x + moveDirOrder[dir][1];
				//모든 방향 탐색한 경우(다음 좌표가 상어 좌표인경우)
				if (++count > 4) {
					check = false;
					break;
				}
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || (ny == sy && nx == sx)) {
					dir = (dir + 1) % 4;
				} else {
					y = ny;
					x = nx;
					visited[y][x] = true;
					num++;
					break;
				}
			}
			//전부 탐색한 경우, break
			if (!check)
				break;
			order[lastIndex - num] = new pos(y, x);
		}
		//M번 탐색
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			//얼음파편  구슬 파괴
			for (int index = 1; index <= s; index++) {
				int ny = sy + moveDir[d][0] * index, nx = sx + moveDir[d][1] * index;
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					break;
				map[ny][nx] = 0;
			}
			while (true) {
				//구슬 이동
				//구슬 순서대로 Queue에 넣기
				Queue<Integer> q = new LinkedList<>();
				for (int index = 0; index < order.length; index++) {
					pos cur = order[index];
					if (map[cur.y][cur.x] != 0)
						q.add(map[cur.y][cur.x]);
				}
				//map 초기화
				for (int i = 0; i < N; i++)
					Arrays.fill(map[i], 0);
				//Queue 순서대로 빼서 map에 넣기
				for (int index = 0; !q.isEmpty(); index++) {
					pos cur = order[index];
					map[cur.y][cur.x] = q.poll();
				}
				//폭발
				boolean checkBoom = false;
				Queue<info> duplicate = new LinkedList<>();
				for (int index = 0; index < order.length; index++) {
					pos cur = order[index];
					if (duplicate.isEmpty()) {
						duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
					} else if (duplicate.peek().num == map[cur.y][cur.x]) {
						duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
					} else {
						if (duplicate.size() >= 4) {
							answer[duplicate.peek().num] += duplicate.size();
							checkBoom = true;
							while (!duplicate.isEmpty()) {
								map[duplicate.peek().y][duplicate.peek().x] = 0;
								duplicate.poll();
							}
							duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
						} else {
							duplicate.clear();
							duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
						}
					}
				}
				if (!checkBoom)
					break;
			}
			//구슬 변화
			Queue<info> duplicate = new LinkedList<>();
			Queue<Integer> temp = new LinkedList<>();
			for (int index = 0; index < order.length; index++) {
				pos cur = order[index];
				if (duplicate.isEmpty()) {
					duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
				} else if (duplicate.peek().num == map[cur.y][cur.x]) {
					duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
				} else {
					temp.add(duplicate.size());
					temp.add(duplicate.peek().num);
					duplicate.clear();
					duplicate.add(new info(cur.y, cur.x, map[cur.y][cur.x]));
				}
			}
			for (int i = 0; i < N; i++)
				Arrays.fill(map[i], 0);
			for (int index = 0; index < order.length && !temp.isEmpty(); index++) {
				pos cur = order[index];
				map[cur.y][cur.x] = temp.poll();
			}
		}
		System.out.println(answer[1] + answer[2] * 2 + answer[3] * 3);
	}

	static class pos {
		int y, x;

		pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class info {
		int y, x, num;

		info(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
}
