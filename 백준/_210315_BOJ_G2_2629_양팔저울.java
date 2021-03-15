import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class _210315_BOJ_G2_2629_양팔저울 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int n, k;
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> s = new HashSet<>();
		s.add(arr[0]);
		
		for (int i = 1; i < n; i++) {
			int cur = arr[i];
			HashSet<Integer> s2 = new HashSet<>();
			s2.add(cur);
			
			Iterator<Integer> iter = s.iterator();
			while (iter.hasNext()) {
				Integer num = (Integer) iter.next();
				s2.add(Math.abs(cur - num));
				s2.add(cur + num);
			}
			iter = s2.iterator();
			while (iter.hasNext()) {
				s.add((Integer) iter.next());
			}
		}
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (s.contains(num))
				sb.append("Y ");
			else
				sb.append("N ");
		}
		System.out.println(sb);
	}
}
