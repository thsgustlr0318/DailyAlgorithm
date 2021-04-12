
public class _210412_비밀지도 {
	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		char[][] map = new char[n][n];
		for (int y = 0; y < n; y++) {
			for (int index = 0, cur = arr1[y] | arr2[y]; cur != 0; cur /= 2, index++) {
				if ((cur & 1) == 1)
					map[y][index] = '#';
				else
					map[y][index] = ' ';
			}
		}
		for (int y = 0; y < n; y++) {
			answer[y] = "";
			for (int x = n - 1; x >= 0; x--) {
				answer[y] += map[y][x];
			}
			answer[y] = answer[y].replaceAll("\u0000", " ");
		}
		return answer;
	}

	public static void main(String[] args) {
		int n = 6;
		int[] arr1 = { 46, 33, 33, 22, 31, 50 };
		int[] arr2 = { 27, 56, 19, 14, 14, 10 };
		solution(n, arr1, arr2);
	}
}
