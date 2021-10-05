import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _211005_BOJ_G5_2174_로봇_시뮬레이션 {
	// 0: N, 1: E, 2: S, 3: W
	static int[][] moveDir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A, B, N, M;
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<Robot> robots = new ArrayList<>();
		int[][] map = new int[B + 1][A + 1];
		for (int index = 0; index < N; index++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			y = B - y + 1;
			map[y][x] = index + 1;
			robots.add(new Robot(y, x, convertDir(dir)));
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			int count = Integer.parseInt(st.nextToken());
			// 로봇 움직이기
			Robot robot = robots.get(index - 1);
			if (dir == 'L') {
				count %= 4;
				robot.dir = (robot.dir + 4 - count) % 4;
			} else if (dir == 'R') {
				count %= 4;
				robot.dir = (robot.dir + count) % 4;
			} else {
				int ny = robot.y, nx = robot.x;
				while (count-- > 0) {
					ny += moveDir[robot.dir][0];
					nx += moveDir[robot.dir][1];
					// 벽에 부딪히는 경우
					if (ny < 1 || nx < 1 || ny > B || nx > A) {
						System.out.println("Robot " + index + " crashes into the wall");
						System.exit(0);
					}
					// 로봇끼리 부딪히는 경우
					if (map[ny][nx] != 0) {
						System.out.println("Robot " + index + " crashes into robot " + map[ny][nx]);
						System.exit(0);
					}
				}
				map[robot.y][robot.x] = 0;
				map[ny][nx] = index;
				robot.y = ny;
				robot.x = nx;
			}
		}
		System.out.println("OK");
	}

	public static int convertDir(char c) {
		if (c == 'N')
			return 0;
		else if (c == 'E')
			return 1;
		else if (c == 'S')
			return 2;
		else
			return 3;
	}

	public static class Robot {
		int y, x, dir;

		Robot(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
}
