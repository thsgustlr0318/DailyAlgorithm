
public class _210324_신규아이디추천 {
	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
	}

	public static String solution(String new_id) {
		//1
		String answer = new_id.toLowerCase();
		//2
		answer = answer.replaceAll("[^0-9a-z-_.]", "");
		//3
		answer = answer.replaceAll("[.]{2,}", ".");
		//4
		answer = answer.replaceAll("^[.]|[.]$", "");
		//5
		if (answer.length() == 0)
			answer = "a";
		//6
		if (answer.length() >= 16)
			answer = answer.substring(0, 15);
		answer = answer.replaceAll("[.]$", "");
		//7
		if (answer.length() <= 2) {
			while (answer.length() != 3)
				answer += answer.charAt(answer.length() - 1);
		}
		return answer;
	}
}
