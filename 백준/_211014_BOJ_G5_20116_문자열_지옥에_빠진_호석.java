import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _211014_BOJ_G5_20116_문자열_지옥에_빠진_호석 {
	static int[][] moveDir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N, M, K;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		for (int y = 0; y < N; y++) {
			map[y] = br.readLine().toCharArray();
		}
		while (K-- > 0) {
			String string = br.readLine();
			// dp[index][y][x]: map[y][x]의 좌표가 index번 알파벳인 경우의 수
			int[][][] dp = new int[string.length()][N][M];
			for (int index = 0; index < string.length(); index++) {
				// 현재 알파벳
				char c = string.charAt(index);
				// 초기 설정 (첫 글자 같은 것 찾기)
				if (index == 0) {
					for (int y = 0; y < N; y++) {
						for (int x = 0; x < M; x++) {
							if (map[y][x] == c) {
								dp[0][y][x] = 1;
							}
						}
					}
				} 
				// 한 칸 이동하였을 때 현재 알파벳인 경우 찾기
				else {
					for (int y = 0; y < N; y++) {
						for (int x = 0; x < M; x++) {
							// 8방
							for (int dir = 0; dir < 8; dir++) {
								int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
								// 환형
								if (ny < 0)
									ny = N - 1;
								else if (ny == N)
									ny = 0;
								if (nx < 0)
									nx = M - 1;
								else if (nx == M)
									nx = 0;
								// 내가 찾는 알파벳인 경우
								if (map[ny][nx] == c)
									dp[index][ny][nx] += dp[index - 1][y][x];
							}
						}
					}
				}
			}
			int count = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					// 
					if (dp[string.length() - 1][y][x] != 0)
						count += dp[string.length() - 1][y][x];
				}
			}
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}
}
