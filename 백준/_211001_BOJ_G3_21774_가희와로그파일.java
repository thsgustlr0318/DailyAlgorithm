import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _211001_BOJ_G3_21774_가희와로그파일 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		ArrayList<String> logs[] = new ArrayList[7];
		for (int index = 1; index <= 6; index++)
			logs[index] = new ArrayList<>();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		// N
		for (int index = 0; index < N; index++) {
			String str[] = br.readLine().split("#");
			int level = Integer.parseInt(str[1]);
			// 1 ~ level까지 시간 추가
			for (int curLevel = 1; curLevel <= level; curLevel++)
				logs[curLevel].add(str[0]);
		}
		// String 오름 차순 정렬
		for (int index = 1; index <= 6; index++)
			Collections.sort(logs[index]);
		for (int index = 0; index < Q; index++) {
			String str[] = br.readLine().split("#");
			int level = Integer.parseInt(str[str.length - 1]);
			String startTime = str[0], endTime = str[1];
			// upperBound - lowerBound
			int diff = upperBound(logs[level], endTime) - lowerBound(logs[level], startTime);
			sb.append(diff + "\n");
		}
		System.out.println(sb);
	}

	public static int lowerBound(ArrayList<String> arr, String time) {
		int low = 0, high = arr.size();
		while (low < high) {
			int mid = (low + high) / 2;
			String curTime = arr.get(mid);
			if (time.compareTo(curTime) <= 0) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return high;
	}

	public static int upperBound(ArrayList<String> arr, String time) {
		int low = 0, high = arr.size();
		while (low < high) {
			int mid = (low + high) / 2;
			String curTime = arr.get(mid);
			if (time.compareTo(curTime) < 0) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return high;
	}
}
