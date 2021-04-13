import java.util.ArrayList;
import java.util.Arrays;

public class _210407_길찾기게임 {
	private static class Node implements Comparable<Node> {
		int x, y, num;
		Node left, right;

		Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.left = null;
			this.right = null;
		}

		Node(Node a) {
			this.x = a.x;
			this.y = a.y;
			this.num = a.num;
			this.left = null;
			this.right = null;
		}

		@Override
		public int compareTo(Node o) {
			if (this.y == o.y)
				return this.x - o.x;
			return o.y - this.y;
		}
	}

	private static class tree {
		private Node root;
		
		public Node getRoot() {
			return this.root;
		}

		public void insert(Node newNode) {
			if (root == null) {
				root = new Node(newNode);
			} else {
				insert(root, newNode);
			}
		}

		private void insert(Node curNode, Node insertNode) {
			if (insertNode.x < curNode.x) {
				if (curNode.left == null)
					curNode.left = new Node(insertNode);
				else
					insert(curNode.left, insertNode);
			} else {
				if (curNode.right == null)
					curNode.right = new Node(insertNode);
				else
					insert(curNode.right, insertNode);
			}
		}
	}

	private static void preorder(Node root, ArrayList<Integer> order) {
		if (root == null)
			return;
		order.add(root.num);
		preorder(root.left, order);
		preorder(root.right, order);
	}

	private static void postorder(Node root, ArrayList<Integer> order) {
		if (root == null)
			return;
		postorder(root.left, order);
		postorder(root.right, order);
		order.add(root.num);
	}

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];
		Node[] nodes = new Node[nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++) {
			nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
		}
		Arrays.sort(nodes);
		tree root = new tree();

		for (int i = 0; i < nodes.length; i++) {
			root.insert(nodes[i]);
		}
		ArrayList<Integer> preOrder = new ArrayList<>();
		ArrayList<Integer> postOrder = new ArrayList<>();
		preorder(root.getRoot(), preOrder);
		postorder(root.getRoot(), postOrder);
		for (int index = 0; index < preOrder.size(); index++)
			answer[0][index] = preOrder.get(index);
		for (int index = 0; index < postOrder.size(); index++)
			answer[1][index] = postOrder.get(index);

		return answer;
	}

	public static void main(String[] args) {
		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 }, { 2, 2 } };
		solution(nodeinfo);
	}
}
