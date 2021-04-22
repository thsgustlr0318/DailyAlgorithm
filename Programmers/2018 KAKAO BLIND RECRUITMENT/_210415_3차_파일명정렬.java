import java.util.ArrayList;
import java.util.Collections;

public class _210415_3차_파일명정렬 {
	static class info implements Comparable<info> {
		String head, fullName;
		int number, index;

		info(String head, int number, int index, String fullName) {
			this.head = head;
			this.number = number;
			this.index = index;
			this.fullName = fullName;
		}

		@Override
		public int compareTo(info o) {
			if (this.head.toLowerCase().compareTo(o.head.toLowerCase()) == 0 && this.number == o.number)
				return this.index - o.index;
			if (this.head.toLowerCase().compareTo(o.head.toLowerCase()) == 0)
				return this.number - o.number;
			return this.head.toLowerCase().compareTo(o.head.toLowerCase());
		}
	}

	public static String[] solution(String[] files) {
		String[] answer = new String[files.length];
		ArrayList<info> arr = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			String cur = files[i];
			StringBuilder head = new StringBuilder(), number = new StringBuilder();
			int index = 0;
			while (index < cur.length() && !Character.isDigit(cur.charAt(index)))
				head.append(cur.charAt(index++));
			while (index < cur.length() && Character.isDigit(cur.charAt(index)))
				number.append(cur.charAt(index++));
			arr.add(new info(head.toString(), Integer.parseInt(number.toString()), i, cur));
		}
		Collections.sort(arr);
		for (int i = 0; i < files.length; i++)
			answer[i] = arr.get(i).fullName;
		return answer;
	}

	public static void main(String[] args) {
		String[] files = { "id f00002", "foo010bar020.zip", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" };
		solution(files);
	}
}
