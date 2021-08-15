import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210815_BOJ_S5_12933_오리 {
	static int Q = 0, U = 1, A = 2, C = 3, K = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] duck = new int[5];
		boolean wrong = false;
		for (int index = 0; index < str.length() && !wrong; index++) {
			char cur = str.charAt(index);
			// q: 새로운 오리 or 기존에 있는 오리
			// u, a, c, k: 알파벳 이전에 오리가 있으면 진행, 없으면 올바른 오리 울음소리가 아님
			if (cur == 'q') {
				if (duck[K] == 0)
					duck[Q]++;
				else {
					duck[K]--;
					duck[Q]++;
				}
			} else if (cur == 'u') {
				if (duck[Q] == 0) {
					wrong = true;
					break;
				} else {
					duck[Q]--;
					duck[U]++;
				}
			} else if (cur == 'a') {
				if (duck[U] == 0) {
					wrong = true;
					break;
				} else {
					duck[U]--;
					duck[A]++;
				}
			} else if (cur == 'c') {
				if (duck[A] == 0) {
					wrong = true;
					break;
				} else {
					duck[A]--;
					duck[C]++;
				}
			} else if (cur == 'k') {
				if (duck[C] == 0) {
					wrong = true;
					break;
				} else {
					duck[C]--;
					duck[K]++;
				}
			}
		}
		if (wrong || duck[Q] != 0 || duck[U] != 0 || duck[A] != 0 || duck[C] != 0)
			System.out.println(-1);
		else
			System.out.println(duck[K]);
	}
}
