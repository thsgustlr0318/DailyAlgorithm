import java.util.Arrays;

public class _210405_블록이동하기 {
	private static class info implements Comparable<info> {
		int index;
		double count;

		info(int index, double count) {
			this.index = index;
			this.count = count;
		}

		@Override
		public int compareTo(info o) {
			if (this.count == o.count)
				return this.index - o.index;
			return Double.compare(o.count, this.count);
		}
	}

	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int[] count = new int[N + 2];
		for (int i = 0; i < stages.length; i++)
			count[stages[i]]++;
		int passCount = stages.length;
		info[] arr = new info[N];
		for (int index = 1; index <= N; index++) {
			if (count[index] == 0)
				arr[index - 1] = new info(index, 0);
			else {
				arr[index - 1] = new info(index, (double)count[index] / passCount);
				passCount -= count[index];
			}
		}
		Arrays.sort(arr);
		for(int i=0; i<N; i++)
			answer[i] = arr[i].index;
		return answer;
	}

	public static void main(String[] args) {
		int N = 5;
		int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };
		solution(N, stages);
	}
}
