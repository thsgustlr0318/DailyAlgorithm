
public class _210429_로또의초고순위와최저순위 {
	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		int matchCount = 0, blankCount = 0;
		for (int i : lottos) {
			if (i == 0)
				blankCount++;
			else {
				for (int j : win_nums) {
					if (i == j) {
						matchCount++;
						break;
					}
				}
			}
		}
		int maxCount = matchCount + blankCount, minCount = matchCount;
		answer[0] = getGrade(maxCount);
		answer[1] = getGrade(minCount);
		return answer;
	}

	private static int getGrade(int grade) {
		if (grade == 6)
			return 1;
		else if (grade == 5)
			return 2;
		else if (grade == 4)
			return 3;
		else if (grade == 3)
			return 4;
		else if (grade == 2)
			return 5;
		return 6;
	}

	public static void main(String[] args) {
		int[] lottos = { 0, 0, 0, 0, 0 };
		int[] win_nums = { 31, 10, 45, 1, 6, 19 };
		solution(lottos, win_nums);
	}
}
