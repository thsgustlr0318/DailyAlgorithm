import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _210313_BOJ_G3_2437_저울 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		//possibleNum - 1까지 저울 추를 조합하여 측정할 수 있음
		int possibleNum = 1;
		for (int i = 0; i < N; i++) {
			if (possibleNum < arr[i])
				break;
			possibleNum += arr[i];
		}
		System.out.println(possibleNum);
	}
}
//그리디
//무게가 낮은 추부터 값을 더해가며 만들 수 있는 최대 정수를 구함
