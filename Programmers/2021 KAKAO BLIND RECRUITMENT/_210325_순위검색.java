import java.util.ArrayList;
import java.util.Collections;

class _210325_순위검색 {
	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 1000" };
		solution(info, query);
	}

	public static int[] solution(String[] info, String[] query) {
		int[] answer = {};
		answer = new int[query.length];
		ArrayList<Integer> arr[] = new ArrayList[(1 << 9)];
		for (int i = 0; i < (1 << 9); i++)
			arr[i] = new ArrayList<>();
		int infoSize = info.length;
		for (int i = 0; i < infoSize; i++) {
			String[] cur = info[i].split(" ");
			int bitmask = 0;
			//언어
			if (cur[0].equals("cpp")) {
				bitmask |= (1 << 0);
			} else if (cur[0].equals("java")) {
				bitmask |= (1 << 1);
			} else if (cur[0].equals("python")) {
				bitmask |= (1 << 2);
			}
			//지원 직군
			if (cur[1].equals("backend")) {
				bitmask |= (1 << 3);
			} else if (cur[1].equals("frontend")) {
				bitmask |= (1 << 4);
			}
			//지원 경력구분
			if (cur[2].equals("junior")) {
				bitmask |= (1 << 5);
			} else if (cur[2].equals("senior")) {
				bitmask |= (1 << 6);
			}
			//소울푸드
			if (cur[3].equals("chicken")) {
				bitmask |= (1 << 7);
			} else if (cur[3].equals("pizza")) {
				bitmask |= (1 << 8);
			}
			//코딩테스트 점수
			int score = Integer.parseInt(cur[4]);
			//기본
			arr[bitmask].add(score);
			//1개
			int temp1 = bitmask;
			temp1 |= (1 << 0);
			temp1 |= (1 << 1);
			temp1 |= (1 << 2);
			int temp2 = bitmask;
			temp2 |= (1 << 3);
			temp2 |= (1 << 4);
			int temp3 = bitmask;
			temp3 |= (1 << 5);
			temp3 |= (1 << 6);
			int temp4 = bitmask;
			temp4 |= (1 << 7);
			temp4 |= (1 << 8);
			arr[temp1].add(score);
			arr[temp2].add(score);
			arr[temp3].add(score);
			arr[temp4].add(score);
			//2개
			int temp5 = temp1 | temp2;
			int temp6 = temp1 | temp3;
			int temp7 = temp1 | temp4;
			int temp8 = temp2 | temp3;
			int temp9 = temp2 | temp4;
			int temp10 = temp3 | temp4;
			arr[temp5].add(score);
			arr[temp6].add(score);
			arr[temp7].add(score);
			arr[temp8].add(score);
			arr[temp9].add(score);
			arr[temp10].add(score);
			//3개
			int temp11 = temp1 | temp2 | temp3;
			int temp12 = temp1 | temp2 | temp4;
			int temp13 = temp1 | temp3 | temp4;
			int temp14 = temp2 | temp3 | temp4;
			arr[temp11].add(score);
			arr[temp12].add(score);
			arr[temp13].add(score);
			arr[temp14].add(score);
			//4개
			int temp15 = temp1 | temp2 | temp3 | temp4;
			arr[temp15].add(score);
		}
		for (int i = 0; i < (1 << 9); i++)
			Collections.sort(arr[i]);
		int querySize = query.length;
		for (int i = 0; i < querySize; i++) {
			String[] cur = query[i].split(" ");
			int bitmask = 0;
			//언어
			if (cur[0].equals("cpp")) {
				bitmask |= (1 << 0);
			} else if (cur[0].equals("java")) {
				bitmask |= (1 << 1);
			} else if (cur[0].equals("python")) {
				bitmask |= (1 << 2);
			} else {
				bitmask |= (1 << 0);
				bitmask |= (1 << 1);
				bitmask |= (1 << 2);
			}
			//지원 직군
			if (cur[2].equals("backend")) {
				bitmask |= (1 << 3);
			} else if (cur[2].equals("frontend")) {
				bitmask |= (1 << 4);
			} else {
				bitmask |= (1 << 3);
				bitmask |= (1 << 4);
			}
			//지원 경력구분
			if (cur[4].equals("junior")) {
				bitmask |= (1 << 5);
			} else if (cur[4].equals("senior")) {
				bitmask |= (1 << 6);
			} else {
				bitmask |= (1 << 5);
				bitmask |= (1 << 6);
			}
			//소울푸드
			if (cur[6].equals("chicken")) {
				bitmask |= (1 << 7);
			} else if (cur[6].equals("pizza")) {
				bitmask |= (1 << 8);
			} else {
				bitmask |= (1 << 7);
				bitmask |= (1 << 8);
			}
			int score = Integer.parseInt(cur[7]);
			int cnt = lowerBound(arr[bitmask], score);
			if (cnt < 0)
				answer[i] = 0;
			else {
				answer[i] = arr[bitmask].size() - cnt;
			}
		}
		return answer;
	}

	private static int lowerBound(ArrayList<Integer> arr, int score) {
		int low = 0;
		int high = arr.size();

		while (low < high) {
			int mid = (low + high) / 2;

			if (score <= arr.get(mid))
				high = mid;
			else
				low = mid + 1;
		}
		return low;
	}
}