import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _211004_BOJ_G2_10775_공항 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		int result = 0;
		int[] airplanes = new int[P];
		int[] airports = new int[G + 1];
		for (int index = 0; index < P; index++) {
			airplanes[index] = Integer.parseInt(br.readLine());
		}
		for (int index = 1; index <= G; index++)
			airports[index] = index;
		for (int airplane : airplanes) {
			int parent = getParent(airports, airplane);
			if (parent == 0)
				break;
			airports[parent] = parent - 1;
			result++;
		}
		System.out.println(result);
	}

	public static int getParent(int[] parents, int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = getParent(parents, parents[x]);
	}
}
