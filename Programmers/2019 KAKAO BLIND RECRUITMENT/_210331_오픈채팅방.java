import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _210331_오픈채팅방 {
	public static String[] solution(String[] record) {
		int recordLength = record.length;
		String[] answer;
		Queue<String> q = new LinkedList<>();
		//HashMap<이름, 닉네임>
		HashMap<String, String> name = new HashMap<>();
		for (int i = 0; i < recordLength; i++) {
			String[] words = record[i].split(" ");
			//Enter 혹은 Change인 경우, 해당 아이디의 닉네임 갱신 
			if (words.length == 3) {
				name.put(words[1], words[2]);
			}
		}
		for (int i = 0; i < recordLength; i++) {
			String[] words = record[i].split(" ");
			//Leave
			if (words.length == 2) {
				q.add(name.get(words[1]) + "님이 나갔습니다.");
			} else {
				//Enter
				if (words[0].equals("Enter")) {
					q.add(name.get(words[1]) + "님이 들어왔습니다.");
				}
			}
		}
		answer = new String[q.size()];
		int index = 0;
		while (!q.isEmpty())
			answer[index++] = q.poll();
		return answer;
	}

	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan" };
		solution(record);
	}
}
