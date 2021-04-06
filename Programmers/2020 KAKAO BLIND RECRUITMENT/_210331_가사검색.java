import java.util.HashMap;

public class _210331_가사검색 {
	private static class Trie {
		private HashMap<Character, Trie> child = new HashMap<>();
		private int terminalCount;

		Trie() {
			this.child = new HashMap<>();
			this.terminalCount = 0;
		}

		public HashMap<Character, Trie> getChildren() {
			return this.child;
		}

		public int getTerminalCount() {
			return this.terminalCount;
		}

		public void addTerminalCount() {
			this.terminalCount++;
		}
	}

	static Trie root[] = new Trie[10001];
	static Trie rootReverse[] = new Trie[10001];

	static void insert(String key, Trie cur) {
		Trie node = cur;
		for (int level = 0; level < key.length(); level++) {
			char c = key.charAt(level);
			if (!node.getChildren().containsKey(c)) {
				node.getChildren().put(c, new Trie());
			}
			node.addTerminalCount();
			node = node.getChildren().get(c);
		}
	}

	static int search(String key, Trie cur) {
		Trie node = cur;
		for (int level = 0; level < key.length(); level++) {
			if (key.charAt(level) == '?') {
				return node.getTerminalCount();
			}
			char index = key.charAt(level);
			if (!node.getChildren().containsKey(index)) {
				return 0;
			}
			node = node.getChildren().get(index);
		}
		return node.terminalCount;
	}

	private static String reverseString(String s) {
		return (new StringBuffer(s)).reverse().toString();
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		for (int i = 1; i <= 10000; i++) {
			root[i] = new Trie();
			rootReverse[i] = new Trie();
		}
		for (int i = 0; i < words.length; i++) {
			String cur = words[i];
			insert(cur, root[cur.length()]);
			insert(reverseString(cur), rootReverse[cur.length()]);
		}
		for (int i = 0; i < queries.length; i++) {
			String cur = queries[i];
			if (cur.charAt(0) != '?')
				answer[i] = search(cur, root[cur.length()]);
			else
				answer[i] = search(reverseString(cur), rootReverse[cur.length()]);
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queris = { "?????", "????o", "fr???", "fro???", "pro?" };
		solution(words, queris);
	}
}

//public class _210331_가사검색 {
//	private static class Trie {
//		Trie[] child;
//		int terminalCount;
//
//		Trie() {
//			this.child = new Trie[26];
//			this.terminalCount = 0;
//		}
//	}
//
//	static Trie root[] = new Trie[10001];
//	static Trie rootReverse[] = new Trie[10001];
//
//	static void insert(String key, Trie cur) {
//		Trie node = cur;
//		for (int level = 0; level < key.length(); level++) {
//			int index = key.charAt(level) - 'a';
//			if (node.child[index] == null)
//				node.child[index] = new Trie();
//			node.terminalCount++;
//			node = node.child[index];
//		}
//	}
//
//	static int search(String key, Trie cur) {
//		Trie node = cur;
//		for (int level = 0; level < key.length(); level++) {
//			if (key.charAt(level) == '?') {
//				return node.terminalCount;
//			}
//			int index = key.charAt(level) - 'a';
//			if (node.child[index] == null)
//				return 0;
//			node = node.child[index];
//		}
//		return node.terminalCount;
//
//	}
//
//	private static String reverseString(String s) {
//		return (new StringBuffer(s)).reverse().toString();
//	}
//
//	public static int[] solution(String[] words, String[] queries) {
//		int[] answer = new int[queries.length];
//		for (int i = 1; i <= 10000; i++) {
//			root[i] = new Trie();
//			rootReverse[i] = new Trie();
//		}
//		for (int i = 0; i < words.length; i++) {
//			String cur = words[i];
//			insert(cur, root[cur.length()]);
//			insert(reverseString(cur), rootReverse[cur.length()]);
//		}
//		for (int i = 0; i < queries.length; i++) {
//			String cur = queries[i];
//			if (cur.charAt(0) != '?')
//				answer[i] = search(cur, root[cur.length()]);
//			else
//				answer[i] = search(reverseString(cur), rootReverse[cur.length()]);
//		}
//		return answer;
//	}
//
//	public static void main(String[] args) {
//		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
//		String[] queris = { "zzz??", "????o", "fr???", "fro???", "pro?" };
//		solution(words, queris);
//	}
//}
