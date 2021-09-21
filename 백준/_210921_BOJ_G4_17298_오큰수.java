import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class _210921_BOJ_G4_17298_오큰수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] result = new int[N];
		Stack<Integer> stack = new Stack<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.fill(result, -1);
		// 스택에는 오큰수를 찾지 못한 값들의 index가 들어있음
		for (int index = 0; index < N; index++) {
			// 현재 수(arr[index])보다 스택에 들어있는 값이 작으면, 스택에서 제거하고 현재값을 오큰수로 설정
			while (!stack.isEmpty() && arr[stack.peek()] < arr[index]) {
				result[stack.pop()] = arr[index];
			}
			stack.add(index);
		}
		// 결과값 넣기
		for (int i = 0; i < N; i++)
			sb.append(result[i] + " ");
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
