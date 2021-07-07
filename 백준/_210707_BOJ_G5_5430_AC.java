import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class _210707_BOJ_G5_5430_AC {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			// 버릴 숫자의 개수가 n보다 많은 경우, error 
			if (p.replaceAll("R", "").length() > n) {
				sb.append("error\n");
				continue;
			}
			// 버릴 숫자의 개수가 n인 경우, 빈 배열이 됨
			if (p.replaceAll("R", "").length() == n) {
				sb.append("[]\n");
				continue;
			}
			String[] tmpNumbers = str.substring(1, str.length() - 1).split(",");
			Deque<Integer> numbers = new ArrayDeque<>();

			// String -> int 변환
			for (int index = 0; index < tmpNumbers.length; index++)
				numbers.add(Integer.parseInt(tmpNumbers[index]));
			// dir: true = 정방향, false = 역방향
			boolean dir = true;
			// R, D 실행
			for (int index = 0; index < p.length(); index++) {
				char cur = p.charAt(index);
				if (cur == 'R') {
					dir = !dir;
				} else {
					if (dir)
						numbers.pollFirst();
					else
						numbers.pollLast();
				}
			}
			sb.append('[');
			// 정방향
			if (dir) {
				while (!numbers.isEmpty())
					sb.append(numbers.pollFirst() + ",");
			}
			// 역방향
			else {
				while (!numbers.isEmpty())
					sb.append(numbers.pollLast() + ",");
			}
			if (sb.length() != 1)
				sb.setLength(sb.length() - 1);
			sb.append("]\n");
		}
		System.out.println(sb);
	}
}
