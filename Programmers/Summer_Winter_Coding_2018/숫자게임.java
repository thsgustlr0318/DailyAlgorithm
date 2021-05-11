package Summer_Winter_Coding_2018;

import java.util.Collections;
import java.util.PriorityQueue;

public class 숫자게임 {
	public static int solution(int[] A, int[] B) {
		int answer = 0;

		PriorityQueue<Integer> pqA = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> pqB = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < A.length; i++) {
			pqA.add(A[i]);
			pqB.add(B[i]);
		}
		while(!pqA.isEmpty()) {
			if(pqA.peek() < pqB.peek()) {
				answer++;
				pqB.poll();
			}
			pqA.poll();
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] A = { 5, 1, 3, 7 };
		int[] B = { 2, 2, 6, 8 };
		System.out.println(solution(A,B));
	}
}
