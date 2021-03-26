class _210325_합승택시요금 {
	static int INF = 10000000;

	public static void main(String[] args) {
		int n = 6, s = 4, a = 6, b = 2;
		int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
		solution(n, s, a, b, fares);
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j)
					map[i][j] = INF;
			}
		}
		int fareSize = fares.length;
		for (int i = 0; i < fareSize; i++) {
			int c = fares[i][0], d = fares[i][1], f = fares[i][2];
			map[c][d] = f;
			map[d][c] = f;
		}
		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				for (int end = 1; end <= n; end++) {
					if (map[start][end] > map[start][mid] + map[mid][end])
						map[start][end] = map[start][mid] + map[mid][end];
				}
			}
		}

		for (int mid = 1; mid <= n; mid++) {
			answer = Math.min(answer, map[s][mid] + map[mid][a] + map[mid][b]);
		}
		return answer;
	}
}