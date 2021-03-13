import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _210313_BOJ_G4_1715_카드정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		int res = 0;
		while (pq.size() != 1) {
			int temp = pq.poll() + pq.poll();
			res += temp;
			pq.add(temp);
		}
		System.out.println(res);
	}
}
//그리디
//우선순위큐에서 두개씩 꺼내서 최소값 구함