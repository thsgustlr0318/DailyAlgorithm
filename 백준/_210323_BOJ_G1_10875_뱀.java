import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _210323_BOJ_G1_10875_뱀 {
	static long L, N;
	static long res = 0;
	static long INF = 200000000;
	//우, 상, 좌, 하
	static int moveDir[][] = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		//처음 방향: 우
		//처음 위치: (0, 0)
		long curY = 0, curX = 0;
		int curDir = 0;
		//arr: 뱀이 지나간 경로들
		ArrayList<info> arr = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			long t, nextY, nextX, tempY, tempX;
			char dir = 0;
			//뱀이 N번 회전했는데도 살아있는 경우, 그 상태로 직진하다 격자판 밖으로 나가서 죽게됨
			//뱀이 격자판을 나갈 정도로 큰 숫자인 INF만큼 움직임
			if (i == N) {
				nextY = curY + INF * moveDir[curDir][0];
				nextX = curX + INF * moveDir[curDir][1];
				tempY = nextY;
				tempX = nextX;
				t = INF;
			} else {
				st = new StringTokenizer(br.readLine());
				t = Integer.parseInt(st.nextToken());
				dir = st.nextToken().charAt(0);
				nextY = curY + t * moveDir[curDir][0];
				nextX = curX + t * moveDir[curDir][1];
				tempY = nextY;
				tempX = nextX;
			}
			//항상 curX < nextX, curY < nextY가 될 수 있도록 값을 변경해줌 
			if (curY > nextY) {
				long temp = curY;
				curY = nextY;
				nextY = temp;
			}
			if (curX > nextX) {
				long temp = curX;
				curX = nextX;
				nextX = temp;
			}
			//minv: 뱀이 회전하다가 자신을 물어서 죽는 경우 중 가장 작은 수
			long minv = Long.MAX_VALUE;
			for (int index = 0; index < arr.size(); index++) {
				info curCheck = arr.get(index);
				//뱀이 우측으로 이동하다가 죽는 경우
				if (curDir == 0) {
					if ((curCheck.sy <= curY && curY <= curCheck.ey) && (curX <= curCheck.sx && curCheck.sx <= nextX)) {
						if (index == arr.size() - 1) {
							if (curX == curCheck.ex && (curY == curCheck.sy || curY == curCheck.ey))
								continue;
						}
						minv = Math.min(minv, Math.abs(curCheck.sx - curX));
					}
				} 
				//뱀이 위로 이동하다가 죽는 경우
				else if (curDir == 1) {
					if ((curY <= curCheck.sy && curCheck.sy <= nextY) && (curCheck.sx <= curX && curX <= curCheck.ex)) {
						if (index == arr.size() - 1) {
							if (nextY == curCheck.ey && (nextX == curCheck.ex || nextX == curCheck.sx))
								continue;
						}
						minv = Math.min(minv, Math.abs(nextY - curCheck.sy));
					}
				} 
				//뱀이 좌측으로 이동하다가 죽는 경우
				else if (curDir == 2) {
					if ((curCheck.sy <= curY && curY <= curCheck.ey) && (curX <= curCheck.sx && curCheck.sx <= nextX)) {
						if (index == arr.size() - 1) {
							if (nextX == curCheck.ex && (nextY == curCheck.sy || nextY == curCheck.ey))
								continue;
						}
						minv = Math.min(minv, Math.abs(nextX - curCheck.ex));
					}

				} 
				//뱀이 아래로 이동하다가 죽는 경우
				else {
					if ((curY <= curCheck.sy && curCheck.sy <= nextY) && (curCheck.sx <= curX && curX <= curCheck.ex)) {
						if (index == arr.size() - 1) {
							if (curY == curCheck.ey && (curX == curCheck.ex || curX == curCheck.sx))
								continue;
						}
						minv = Math.min(minv, Math.abs(curCheck.sy - curY));
					}
				}
			}
			//만약 뱀이 죽은 경우
			if (minv != Long.MAX_VALUE) {
				res += minv;
				System.out.println(res);
				System.exit(0);
			}
			
			//뱀이 산 경우
			res += t;
			
			//뱀이 격자판 벗어난 경우
			if (nextY > L) {
				res -= (nextY - L) - 1;
				System.out.println(res);
				System.exit(0);
			}
			if (curY < -L) {
				res -= (-L - curY) - 1;
				System.out.println(res);
				System.exit(0);
			}
			if (nextX > L) {
				res -= (nextX - L) - 1;
				System.out.println(res);
				System.exit(0);
			}
			if (curX < -L) {
				res -= (-L - curX) - 1;
				System.out.println(res);
				System.exit(0);
			}

			//arr에 집어넣기
			arr.add(new info(curY, curX, nextY, nextX));
			curY = tempY;
			curX = tempX;
			//뱀 다음 방향 결정
			if (dir == 'L')
				curDir = (curDir + 1) % 4;
			else
				curDir = (curDir + 3) % 4;
		}
	}

	private static class info {
		long sy, sx, ey, ex;

		info(long sy, long sx, long ey, long ex) {
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
		}
	}
}
