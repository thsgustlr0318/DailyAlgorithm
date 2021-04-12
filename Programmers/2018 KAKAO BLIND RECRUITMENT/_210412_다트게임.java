
public class _210412_다트게임 {
	public static int solution(String dartResult) {
		int answer = 0;
		int[] score = new int[3];
		int index = 0;
		for (int i = 0; i < 3; i++) {
			String temp = "";
			//점수
			for (; index < dartResult.length(); index++) {
				if ('0' <= dartResult.charAt(index) && dartResult.charAt(index) <= '9')
					temp += dartResult.charAt(index);
				else
					break;
			}
			score[i] = Integer.parseInt(temp);
			//SDT
			if (dartResult.charAt(index) == 'D') {
				score[i] = (int) Math.pow(score[i], 2);
			} else if (dartResult.charAt(index) == 'T') {
				score[i] = (int) Math.pow(score[i], 3);
			}
			index++;
			//스타상, 아차상
			if(index == dartResult.length())
				continue;
			if (dartResult.charAt(index) == '#') {
				score[i] *= -1;
				index++;
			} else if (dartResult.charAt(index) == '*') {
				score[i] *= 2;
				if (i >= 1)
					score[i - 1] *= 2;
				index++;
			}
		}
		return score[0] + score[1] + score[2];
	}

	public static void main(String[] args) {
		String dartResult = "1S2D*3T";
		System.out.println(solution(dartResult));
	}
}
