
public class _210415_키패드누르기 {
	static int[][] keyPosition = { { 3, 1 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };

	public static String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();
		int ly = 3, lx = 0, ry = 3, rx = 2;
		for (int index = 0; index < numbers.length; index++) {
			int curNum = numbers[index];
			if (curNum == 1 || curNum == 4 || curNum == 7) {
				ly = keyPosition[curNum][0];
				lx = keyPosition[curNum][1];
				answer.append('L');
			} else if (curNum == 3 || curNum == 6 || curNum == 9) {
				ry = keyPosition[curNum][0];
				rx = keyPosition[curNum][1];
				answer.append('R');
			} else {
				int y = keyPosition[curNum][0], x = keyPosition[curNum][1];
				int leftDistance = getDistance(ly, lx, y, x);
				int rightDistance = getDistance(ry, rx, y, x);
				if (leftDistance == rightDistance) {
					if (hand.equals("right")) {
						ry = y;
						rx = x;
						answer.append('R');
					} else {
						ly = y;
						lx = x;
						answer.append('L');
					}
				} else if (leftDistance > rightDistance) {
					ry = y;
					rx = x;
					answer.append('R');
				} else {
					ly = y;
					lx = x;
					answer.append('L');
				}
			}
		}
		return answer.toString();
	}

	private static int getDistance(int ly, int lx, int keyY, int keyX) {
		return Math.abs(ly - keyY) + Math.abs(lx - keyX);
	}

	public static void main(String[] args) {
		int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String hand = "right";
		System.out.println(solution(numbers, hand));
	}
}
