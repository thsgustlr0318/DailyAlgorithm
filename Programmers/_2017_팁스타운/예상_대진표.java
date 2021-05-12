package _2017_팁스타운;

public class 예상_대진표 {
	public static int solution(int n, int a, int b) {
		int answer = 0;
		while (a != b) {
			a = (a + 1) / 2;
			b = (b + 1) / 2;
			answer++;
		}
		return answer;
	}

	public static void main(String[] args) {
		int n = 8;
		int a = 2;
		int b = 5;
		System.out.println(solution(n, a, b));
	}
	//8->4->2->1
}
