import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _210310_BOJ_G5_1013_Contact {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			String str = br.readLine();
			System.out.println(solve(str, 0) ? "YES" : "NO");
		}
	}

	private static boolean solve(String str, int start) {
		int len = str.length();
		//문자열 처음부터 끝까지 탐색
		for (int idx = 0; idx < len; ) {
			//처음에 0으로 시작하면, 1이 바로 뒤에 나와야함(01)
			if (str.charAt(idx) == '0') {
				//문자열이 0으로 끝나면, flase
				if(idx + 1== len)
					return false;
				//0뒤에 바로 1이 없으면, false
				if (str.charAt(idx + 1) != '1')
					return false;
				//01 다음 탐색
				else
					idx += 2;
			//처음에 1로 시작하면
			} else {
				//100으로 시작해야하므로
				//연속되는 0의 개수 찾기
				int cntZero = 0;
				idx++;
				for (; idx < len && str.charAt(idx) == '0'; idx++, cntZero++);
				//문자열이 0으로 끝나거나, 0이 2번 이상 안나온경우 false
				if (cntZero < 2 || idx == len)
					return false;
				//100 뒤에 1 나옴
				//연속하는 1의 개수 찾기
				int cntOne = 0;
				for (; idx < len && str.charAt(idx) == '1'; idx++, cntOne++);
				//1의 개수가 0인 경우(0으로 끝나는경우), false
				if(cntOne == 0)
					return false;
				//문자열이 1로 끝나는경우, 패턴이 되므로 true
				if(idx == len)
					return true;
				//1뒤에 0이 있는경우, 다음 탐색
				if(cntOne==1) {
					continue;
				}
				//1뒤에 00인 경우, 1부터 다시 탐색
				else {
					if(idx+1<len && str.charAt(idx) == '0' && str.charAt(idx+1) == '0') {
						idx--;
					}
				}
			}
		}
		return true;
	}
}
