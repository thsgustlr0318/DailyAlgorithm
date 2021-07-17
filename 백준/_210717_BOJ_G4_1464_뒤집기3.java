import java.util.Scanner;

public class _210717_BOJ_G4_1464_뒤집기3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String str = sc.next();

		// 사전순으로 앞선 것들 -> 맨 뒤에 붙이기
		// 사전순으로 늦은 것들 -> 맨 앞에 붙이기
		sb.append(str.charAt(0));
		for (int index = 1; index < str.length(); index++) {
			if (sb.charAt(index - 1) < str.charAt(index)) {
				sb.insert(0, str.charAt(index));
			} else {
				sb.append(str.charAt(index));
			}
		}
		// 마지막에 뒤집기
		System.out.println(sb.reverse());
	}
}
