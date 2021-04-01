
public class _210330_자물쇠와열쇠 {
	static int N, M;

	public static boolean solution(int[][] key, int[][] lock) {
		N = lock.length;
		M = key.length;
		for (int i = -M + 1; i < N; i++) {
			for (int j = -M + 1; j < N; j++) {
				for (int dir = 0; dir < 4; dir++) {
					//키 돌리기
					key = rotate(key);
					int[][] temp = new int[N][N];
					//temp에 복사
					for (int y = 0; y < N; y++)
						for (int x = 0; x < N; x++)
							temp[y][x] = lock[y][x];
					//key 추가
					boolean crash = false;
					for (int y = 0; y < M && !crash; y++)
						for (int x = 0; x < M; x++) {
							if (y + i < 0 || x + j < 0 || y + i >= N || x + j >= N || key[y][x] == 0)
								continue;
							if (temp[y + i][x + j] == 1) {
								crash = true;
								break;
							}
							temp[y + i][x + j] = 1;
						}

					if (!crash && check(temp))
						return true;
				}
			}
		}
		return false;
	}

	private static int[][] rotate(int[][] key) {
		int len = key.length;
		int[][] temp = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++)
				temp[i][j] = key[j][len - 1 - i];
		}
		return temp;
	}

	private static boolean check(int[][] temp) {
		int len = temp.length;
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				if (temp[i][j] == 0)
					return false;
		return true;
	}

	public static void main(String[] args) {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(solution(key, lock));
	}
}
