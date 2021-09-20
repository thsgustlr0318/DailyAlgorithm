import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class _210920_BOJ_G5_7662_이중_우선순위큐 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int K = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();
			while (K-- > 0) {
				st = new StringTokenizer(br.readLine());
				char l = st.nextToken().charAt(0);
				int n = Integer.parseInt(st.nextToken());

				if (l == 'I') {
					map.put(n, map.getOrDefault(n, 0) + 1);
				} else {
					int num = 0;
					if (map.isEmpty())
						continue;
					else if (n == 1) {
						num = map.lastKey();

					} else {
						num = map.firstKey();
					}
					map.put(num, map.get(num) - 1);
					if (map.get(num) == 0)
						map.remove(num);
				}
			}
			if (map.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(map.lastKey() + " " + map.firstKey() + "\n");
			}
		}
		System.out.println(sb);
	}
}
