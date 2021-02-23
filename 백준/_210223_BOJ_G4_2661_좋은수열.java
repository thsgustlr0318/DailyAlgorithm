import java.util.Scanner;

public class _210223_BOJ_G4_2661_좋은수열 {
	static int N;
	static String res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dfs("");
	}

	private static void dfs(String str) {
		if (str.length() == N) {
			System.out.println(str);
			System.exit(0);
		}
		for (int i = 1; i <= 3; i++) {
			String nextStr = str + Integer.toString(i);
			boolean check = true;
			for (int j = 0; check && j < nextStr.length(); j++) {
				//temp: nextStr을 j번째 인덱스부터 끝까지 자른 문자열
				String temp = nextStr.substring(j);
				//temp의 길이가 짝수인 경우, 반 나눠서 비교
				if ((temp.length() & 1) == 0) {
					String temp1 = temp.substring(0, temp.length() / 2);
					String temp2 = temp.substring(temp.length() / 2, temp.length());
					//인접한 부분 수열이 동일한 경우, 나쁜 수열
					if (temp1.equals(temp2)) {
						check = false;
						continue;
					}
				}
			}
			//다음 좋은 수열 탐색
			if (check)
				dfs(nextStr);
		}
	}
}