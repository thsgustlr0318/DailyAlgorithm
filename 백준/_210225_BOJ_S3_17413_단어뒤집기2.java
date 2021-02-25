import java.util.Scanner;

public class _210225_BOJ_S3_17413_단어뒤집기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) == '<') {
				while (str.charAt(index) != '>')
					sb.append(str.charAt(index++));
				sb.append('>');
			} else if (str.charAt(index) == ' ')
				sb.append(' ');
			else {
				int wordlen = index;
				for (; wordlen < str.length() && str.charAt(wordlen) != ' ' && str.charAt(wordlen) != '<'; wordlen++)
					;
				for (int temp = wordlen - 1; temp >= index; temp--)
					sb.append(str.charAt(temp));
				index = wordlen - 1;
			}
		}
		System.out.println(sb);
	}
}
