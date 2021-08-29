import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210829_BOJ_G2_16571_알파_틱택토 {
	static char[][] map = new char[3][3];
	static char result = 'L';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int count = 0;
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == '1')
					count++;
				else if (map[i][j] == '2')
					count--;
			}
		}
		// 시작 플레이어 설정
		char startPlayer = count == 0 ? '1' : '2';
		int result = dfs(startPlayer);
		if(result == 1)
			System.out.println('W');
		else if(result == 0)
			System.out.println('D');
		else
			System.out.println('L');
	}

	// 내가 진 경우: -1
	// 비긴 경우: 0
	// 내가 이긴 경우: 1
	private static int dfs(char player) {
		// 다음 플레이어 설정
		char nextPlayer = player == '1' ? '2' : '1';
		// 내 차례에 이미 게임이 끝난 경우, 두지 않고 상대방 승리
		if(finishGame(nextPlayer))
			return -1;

		// min: 내가 최선을 다했을 때 상대방이 주는 결과
		// 초기값: 2
		int min = 2;
		// 완전 탐색
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (map[y][x] == '0') {
					map[y][x] = player;
					// 항상 최선을 다해야 한다: 상대방이 뒀을 때 최대한 지게 만든다
					// 상대방이 진다 -> 비긴다 -> 이긴다 순으로 좋으므로 min값 구함
					min = Math.min(min, dfs(nextPlayer));
					map[y][x] = '0';
				}
			}
		}
		// min == 2 : 9칸을 다 뒀을 때 초기값이 변하지 않으면 무승부
		// min == 0 : 이미 이전에 무승부를 반환한 경우
		if(min == 2 || min == 0)
			return 0;
		// 상대방이 졋으면 나는 이겼고, 상대방이 이겼으면 나는 지므로
		return -min;
	}


	public static boolean finishGame(char player) {
		// 가로
		for (int y = 0; y < 3; y++)
			if (map[y][0] == player && map[y][1] == player && map[y][2] == player)
				return true;
		// 세로
		for (int x = 0; x < 3; x++)
			if (map[0][x] == player && map[1][x] == player && map[2][x] == player)
				return true;
		// 대각선
		if (map[0][0] == player && map[1][1] == player && map[2][2] == player)
			return true;
		if (map[0][2] == player && map[1][1] == player && map[2][0] == player)
			return true;
		return false;
	}
}
