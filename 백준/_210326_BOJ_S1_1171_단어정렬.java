import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _210326_BOJ_S1_1171_단어정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];
		for (int i = 0; i < n; i++)
			arr[i] = br.readLine();
		Arrays.sort(arr, (o1, o2) -> {
			if (o1.length() == o2.length())
				return o1.compareTo(o2);
			return o1.length() - o2.length();
		});
		sb.append(arr[0]+"\n");
		for (int i = 1; i < n; i++) {
			if(arr[i].compareTo(arr[i-1]) != 0)
				sb.append(arr[i] + "\n");
		}
		System.out.println(sb);
	}
}
