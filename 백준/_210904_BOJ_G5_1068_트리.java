import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _210904_BOJ_G5_1068_트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] tree = new ArrayList[N];
		for (int index = 0; index < N; index++)
			tree[index] = new ArrayList<>();
		// 부모 노드
		int[] parent = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			parent[index] = Integer.parseInt(st.nextToken());
			if (parent[index] != -1)
				tree[parent[index]].add(index);
		}
		int targetNode = Integer.parseInt(br.readLine());
		int result = 0;
		for (int curNode = 0; curNode < N; curNode++) {
			// 자식 노드가 없어야 하고, root로 갈 수 있는 경로가 있어야함
			if (!hasChildNode(tree, curNode, targetNode) && canGoRoot(parent, curNode, targetNode))
				result++;
		}
		System.out.println(result);
	}

	public static boolean hasChildNode(ArrayList<Integer>[] tree, int curNode, int targetNode) {
		for (int node : tree[curNode]) {
			if (node != targetNode)
				return true;
		}
		return false;
	}

	public static boolean canGoRoot(int[] parent, int curNode, int targetNode) {
		// 지운 노드(targetNode) 아래에 속하면 리프노드가 될 수 없음
		if (curNode == targetNode)
			return false;
		// root에 도달한 경우, 리프노드가 될 수 있음
		if (parent[curNode] == -1)
			return true;
		return canGoRoot(parent, parent[curNode], targetNode);
	}
}
