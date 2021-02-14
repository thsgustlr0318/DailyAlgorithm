import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _210214_BOJ_S4_1026_보물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A.add(Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			B.add(Integer.parseInt(st.nextToken()));

		Collections.sort(A);
		Collections.sort(B, Collections.reverseOrder());

		int res = 0;
		for (int i = 0; i < N; i++) 
			res += A.get(i) * B.get(i);
		System.out.println(res);
	}
}
