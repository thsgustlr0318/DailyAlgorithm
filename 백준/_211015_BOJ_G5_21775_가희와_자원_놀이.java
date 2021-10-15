import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class _211015_BOJ_G5_21775_가희와_자원_놀이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N, T;
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] turns = new int[T];
		for (int index = 0; index < T; index++)
			turns[index] = Integer.parseInt(st.nextToken());
		Queue<String> operations = new LinkedList<>();
		for (int index = 0; index < T; index++)
			operations.add(br.readLine());
		// 유저의 연산 카드 목록
		Stack<String>[] users = new Stack[N + 1];
		// 자원 카드
		HashSet<Integer> cards = new HashSet<>();
		HashSet<Integer> first = new HashSet<>();
		for (int index = 1; index <= N; index++) {
			users[index] = new Stack<>();
		}
		// 턴 수행
		for (int index = 0; index < T; index++) {
			int turn = turns[index];
			String string = null;
			String[] operation = null;
			// 연산 카드를 들고 있지 않은 경우
			if (users[turn].isEmpty()) {
				// 공용 연산 카드 더미에서 가져옴
				string = operations.poll();
			}
			// 연산 카드를 들고 있는 경우
			else {
				// 유저의 연산 카드 목록에서 가져옴
				string = users[turn].pop();
			}
			operation = string.split(" ");
			// 카드 id 입력
			sb.append(operation[0] + "\n");
			// next: 아무 것도 안하고 카드 버림
			if (operation[1].equals("next")) {

			} 
			// acquire: 자원 카드 얻기
			else if (operation[1].equals("acquire")) {
				int card = Integer.parseInt(operation[2]);
				// 카드 등장 처리
				if (!first.contains(card)) {
					first.add(card);
				} else {
					// 자원 카드가 있으면
					if (cards.contains(card)) {
						cards.remove(card);
					}
					// 자원 카드가 없으면
					else {
						users[turn].add(string);
					}
				}
			} 
			// release: 자원 카드 반납
			else {
				int card = Integer.parseInt(operation[2]);
				cards.add(card);
			}
		}
		System.out.println(sb);
	}
}
