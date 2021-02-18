import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _210218_BOJ_G4_1062_가르침 {
	static int N, K, res = 0;
	static boolean[] selected = new boolean[26];
	static boolean[][] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		alphabet = new boolean[N][26];
		//a, n, t, i, c는 무조건 사용됨
		selected[0] = selected['n' - 'a'] = selected['t' - 'a'] = selected['i' - 'a'] = selected['c' - 'a'] = true;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int idx = 4; idx < str.length() - 4; idx++) {
				alphabet[i][str.charAt(idx) - 'a'] = true;
			}
		}
		dfs(5, 0);
		System.out.println(res);
	}

	private static void dfs(int cnt, int idx) {
		if (cnt == K) {
			int total = 0;
			//가능한 단어 수 세기
			for (int i = 0; i < N; i++) {
				boolean check = true;
				for (int j = 0; j < 26; j++) {
					//단어에는 필요하지만, 선택된 알파벳에는 없는 경우
					if (!selected[j] && alphabet[i][j]) {
						check = false;
						break;
					}
				}
				if (check)
					total++;
			}
			res = Math.max(res, total);
			return;
		}
		//알파벳 고르기
		for (int i = idx; i < 26; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			dfs(cnt + 1, i + 1);
			selected[i] = false;
		}
	}
}
