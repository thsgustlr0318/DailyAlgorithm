import java.util.ArrayList;
import java.util.Collections;

class _210331_기둥과보설치 {
	static int gidung = 1;
	static int bo = 2;
	static int[][] map;
	static int N;

	private static class info implements Comparable<info> {
		int x, y, a;

		info(int x, int y, int a) {
			this.x = x;
			this.y = y;
			this.a = a;
		}

		@Override
		public int compareTo(info o) {
			if (this.y == o.y && this.x == o.x)
				return this.a - o.a;
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}
	}

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		N = n;
		map = new int[n + 1][n + 1];
		for (int i = 0; i < build_frame.length; i++) {
			int x = build_frame[i][0], y = n - build_frame[i][1], a = build_frame[i][2], b = build_frame[i][3];
			//설치
			if (b == 1) {
				//기둥
				if (a == 0) {
					if (canBuildGidung(y, x))
						map[y][x] |= gidung;
				}
				//보
				else {
					if (canBuildBo(y, x))
						map[y][x] |= bo;
				}
			}
			//삭제
			else {
				//기둥
				if (a == 0) {
					if (canDeleteGidung(y, x))
						map[y][x] &= ~gidung;
				}
				//보
				else {
					if (canDeleteBo(y, x))
						map[y][x] &= ~bo;
				}
			}
		}
		ArrayList<info> arr = new ArrayList<>();
		for (int y = 0; y <= N; y++)
			for (int x = 0; x <= N; x++) {
				int cur = map[N - y][x];
				if (cur == gidung) {
					arr.add(new info(x, y, 0));
				} else if (cur == bo) {
					arr.add(new info(x, y, 1));
				} else if (cur == (gidung + bo)) {
					arr.add(new info(x, y, 0));
					arr.add(new info(x, y, 1));
				}

			}
		Collections.sort(arr);
		answer = new int[arr.size()][3];
		for (int index = 0; index < arr.size(); index++) {
			answer[index][0] = arr.get(index).x;
			answer[index][1] = arr.get(index).y;
			answer[index][2] = arr.get(index).a;
		}
		return answer;
	}

	private static boolean canBuildGidung(int y, int x) {
		if (y == N || (map[y + 1][x] & gidung) == gidung)
			return true;
		if (x - 1 >= 0 && (map[y][x - 1] & bo) == bo)
			return true;
		if ((map[y][x] & bo) == bo)
			return true;
		return false;
	}

	private static boolean canBuildBo(int y, int x) {
		if ((map[y + 1][x] & gidung) == gidung)
			return true;
		if (x + 1 <= N && (map[y + 1][x + 1] & gidung) == gidung)
			return true;
		if ((x + 1 <= N && (map[y][x + 1] & bo) == bo) && (x - 1 >= 0 && ((map[y][x - 1] & bo) == bo)))
			return true;
		return false;
	}

	private static boolean canDeleteGidung(int y, int x) {
		map[y][x] &= ~gidung;
		if ((map[y - 1][x] & gidung) == gidung) {
			if (!canBuildGidung(y - 1, x)) {
				map[y][x] |= gidung;
				return false;
			}
		}
		if ((map[y - 1][x] & bo) == bo) {
			if (!canBuildBo(y - 1, x)) {
				map[y][x] |= gidung;
				return false;
			}
		}
		if (x - 1 >= 0 && ((map[y - 1][x - 1] & bo) == bo)) {
			if (!canBuildBo(y - 1, x - 1)) {
				map[y][x] |= gidung;
				return false;
			}
		}
		return true;
	}

	private static boolean canDeleteBo(int y, int x) {
		map[y][x] &= ~bo;
		if ((map[y][x] & gidung) == gidung) {
			if (!canBuildGidung(y, x)) {
				map[y][x] |= bo;
				return false;
			}
		}
		if ((map[y][x + 1] & gidung) == gidung) {
			if (!canBuildGidung(y, x + 1)) {
				map[y][x] |= bo;
				return false;
			}
		}
		if ((x - 1 >= 0 && (map[y][x - 1] & bo) == bo)) {
			if (!canBuildBo(y, x - 1)) {
				map[y][x] |= bo;
				return false;
			}
		}
		if ((x + 1 <= N && (map[y][x + 1] & bo) == bo)) {
			if (!canBuildBo(y, x + 1)) {
				map[y][x] |= bo;
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int n = 5;
		int[][] build_frame = { { 0, 0, 0, 1 }, { 0, 1, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 } };
		solution(n, build_frame);
	}
}