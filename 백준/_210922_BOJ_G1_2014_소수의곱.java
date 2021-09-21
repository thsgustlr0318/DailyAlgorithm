import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210922_BOJ_G1_2014_소수의곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int maxValue = Integer.MAX_VALUE;
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long[] primes = new long[K];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < K; index++) {
			primes[index] = Long.parseLong(st.nextToken());
			pq.add(primes[index]);
		}
		
		// 우선순위 큐에는 낮은 값부터 들어감
		// 큐에서 N번째 빼는 값이 N번째 수
		for (int index = 0; index < N - 1; index++) {
			long num = pq.poll();
			for (int i = 0; i < K; i++) {
				long nextValue = num * primes[i];
				// 정답 < 2^31
				if (nextValue > maxValue)
					break;
				pq.add(nextValue);
				// 중복 제거
				if (num % primes[i] == 0)
					break;
			}
		}
		System.out.println(pq.poll());
	}
}
// 중복 확인할 때 HashSet이나 TreeSet을 사용하면 메모리 초과가 남
// 중복 제거를 위해서 if (num % primes[i] == 0)를 사용 
