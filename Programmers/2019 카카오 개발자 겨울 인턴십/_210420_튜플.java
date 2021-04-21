import java.util.ArrayList;
import java.util.HashSet;

public class _210420_튜플 {
	public static int[] solution(String s) {
		int[] answer = {};
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		//원소 구하기
		String[] temp = s.substring(2, s.length() - 2).split("\\},\\{");
		answer = new int[temp.length];
		//각 원소에서 숫자 추출
		for (int i = 0; i < temp.length; i++) {
			String[] nums = temp[i].split(",");
			ArrayList<Integer> numArr = new ArrayList<>();
			for (int j = 0; j < nums.length; j++)
				numArr.add(Integer.parseInt(nums[j]));
			arr.add(numArr);
		}
		arr.sort((o1, o2) -> {
			return o1.size() - o2.size();
		});
		ArrayList<Integer> result = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.size(); i++) {
			ArrayList<Integer> cur = arr.get(i);
			for (int index = 0; index < cur.size(); index++) {
				if (!set.contains(cur.get(index))) {
					set.add(cur.get(index));
					result.add(cur.get(index));
					break;
				}
			}
		}
		for (int index = 0; index < result.size(); index++)
			answer[index] = result.get(index);
		return answer;
	}

	public static void main(String[] args) {
		String s = 	"{{20,111},{111}}";
		solution(s);
	}
}
