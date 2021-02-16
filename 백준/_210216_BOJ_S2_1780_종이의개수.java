import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210216_BOJ_S2_1780_종이의개수 {
	static int N, a = 0, b = 0, c = 0;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursive(N, 0, 0);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}

	private static void recursive(int N, int y, int x) {
		//모두 같은 수로 채워졌는지 확인
		boolean flag = true;
		int start = arr[y][x];
		for (int i = y; flag && i < y + N; i++) {
			for (int j = x; flag && j < x + N; j++) {
				if (start != arr[i][j]) {
					flag = false;
				}
			}
		}
		//모두 같으면, 종이 개수 추가
		if (flag) {
			if (start == -1)	a++;
			else if (start == 0)	b++;
			else	c++;
			return;
		}
		//아니면, 9분할
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				recursive(N / 3, y + i * N / 3, x + j * N / 3);
	}
}
