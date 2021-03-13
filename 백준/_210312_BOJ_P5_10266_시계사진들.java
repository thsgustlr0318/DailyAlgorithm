import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _210312_BOJ_P5_10266_시계사진들 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		//시계 값 입력 후 정렬
		int[] clock1 = new int[n];
		int[] clock2 = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			clock1[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			clock2[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(clock1);
		Arrays.sort(clock2);
		
		//arr1: 첫번째 시계 값 사이의 차이를 2개 이어 붙인것
		//arr2: 두번째 시계 값 사이의 차이
		int[] arr1 = new int[n * 2];
		int[] arr2 = new int[n];
		//1~2, 2~3, 3~4....n-1~n번째 값 사이의 차이
		for (int index = 0; index < n - 1; index++) 
			arr1[index] = arr1[index + n] = clock1[index + 1] - clock1[index];
		//n번째와 1번째 값 사이의 차이
		arr1[n - 1] = arr1[2 * n - 1] = clock1[0] - clock1[n - 1] + 360000;
		//1~2, 2~3, 3~4....n-1~n번째 값 사이의 차이
		for (int index = 0; index < n - 1; index++)
			arr2[index] = clock2[index + 1] - clock2[index];
		//n번째와 1번째 값 사이의 차이
		arr2[n - 1] = clock2[0] - clock2[n - 1] + 360000;
		
		//Failure Function
		int[] failureFunction = new int[n];
		int i = 1, j = 0;
		for (i = 1; i < n; i++) {
			while (j > 0 && arr2[i] != arr2[j])
				j = failureFunction[j - 1];
			if (arr2[i] == arr2[j])
				failureFunction[i] = ++j;
		}
		//KMP
		j = 0;
		boolean check = false;
		for (i = 0, j = 0; i < 2 * n; i++) {
			while (j > 0 && arr1[i] != arr2[j])
				j = failureFunction[j - 1];
			if (arr1[i] == arr2[j]) {
				if (j == n - 1) {
					check = true;
					break;
				} else
					j++;
			}
		}
		if (check)
			System.out.println("possible");
		else
			System.out.println("impossible");
	}
}
