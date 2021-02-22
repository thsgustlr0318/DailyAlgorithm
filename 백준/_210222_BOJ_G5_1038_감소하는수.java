import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _210222_BOJ_G5_1038_감소하는수 {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Queue<Long> q = new LinkedList<>();
		ArrayList<Long> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			q.add((long) i);
			list.add((long) i);
		}

		int cnt = 10;
		while (!q.isEmpty()) {
			long cur = q.poll();
			//last: 마지막 자리 수
			long last = cur % 10;
			//현재 수(cur)의 마지막 자리 수보다 작은 값 붙여서 queue에 삽입
			for (int i = 0; i < last; i++) {
				q.add(cur * 10 + i);
				list.add(cur * 10 + i);
				cnt++;
			}
		}
		Collections.sort(list);
		if (N >= list.size())
			System.out.println("-1");
		else
			System.out.println(list.get(N));
	}
}
