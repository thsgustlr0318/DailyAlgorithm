import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _210717_BOJ_G5_1501_영어_읽기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> hashmap = new HashMap<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			// 단어의 길이가 2보다 큰 경우
			if (word.length() > 2) {
				char[] temp = word.toCharArray();
				// 단어의 첫 번째, 마지막 알파벳을 제외한 부분 정렬
				Arrays.sort(temp, 1, temp.length - 1);
				word = new String(temp);
			}
			hashmap.put(word, hashmap.getOrDefault(word, 0) + 1);
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] words = br.readLine().split(" ");
			long sum = 1;
			for (String word : words) {
				// 단어의 길이가 2보다 큰 경우
				if (word.length() > 2) {
					char[] temp = word.toCharArray();
					// 단어의 첫 번째, 마지막 알파벳을 제외한 부분 정렬
					Arrays.sort(temp, 1, temp.length - 1);
					String converted = new String(temp);
					// 단어가 존재하면 hashmap에서 개수 가져오고, 없으면 0
					int num = hashmap.getOrDefault(converted, 0);
					sum *= num;
				} else {
					// 단어가 존재하면 hashmap에서 개수 가져오고, 없으면 0
					sum *= hashmap.getOrDefault(word, 0);
				}
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);
	}
}
