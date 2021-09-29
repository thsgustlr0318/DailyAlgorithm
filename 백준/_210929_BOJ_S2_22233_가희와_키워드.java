import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _210929_BOJ_S2_22233_가희와_키워드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> keywords = new HashSet<>();
		for (int i = 0; i < N; i++) {
			keywords.add(br.readLine());
		}
		for (int index = 0; index < M; index++) {
			String[] words = br.readLine().split(",");
			for (String word : words) {
				if (keywords.contains(word))
					keywords.remove(word);
			}
			sb.append(keywords.size() + "\n");
		}
		System.out.println(sb);
	}
}
