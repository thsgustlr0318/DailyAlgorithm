import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _211006_BOJ_G4_21776_가희와읽기쓰기놀이 {
	static int N, C;
	static ArrayList<Integer>[] cards;
	static String[] commands;
	static TreeSet<String> result = new TreeSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// Input
		cards = new ArrayList[N + 1];
		// counts[index]: index번 사람의 카드 낸 횟수
		int[] counts = new int[N + 1];
		// 카드 순서
		for (int index = 1; index <= N; index++) {
			cards[index] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			counts[index] = count;
			for (int i = 0; i < count; i++)
				cards[index].add(Integer.parseInt(st.nextToken()));
		}
		commands = new String[C + 1];
		// 연산
		for (int index = 1; index <= C; index++)
			commands[index] = br.readLine();
		// 백트래킹
		dfs(counts, new int[C], 0);
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (String string : result) {
			sb.append(string + "\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int[] counts, int[] selected, int depth) {
		if (depth == C) {
			StringBuilder sb = new StringBuilder();
			// indexs[num]: num번 사람이 내야 하는 연산 번호
			int[] indexs = new int[N + 1];
			for (int num : selected) {
				String command = commands[cards[num].get(indexs[num]++)];
				String[] operations = command.split(",");
				for (String operation : operations) {
					String[] str = operation.split(" ");
					// ADD
					if (str[0].equals("ADD")) {
						sb.append(str[1]);
					} 
					// DEL
					else {
						int target = Integer.parseInt(str[1]);
						// target번 글자 지울 수 없을 때
						if (sb.length() < target + 1) {
							result.add("ERROR");
							return;
						} else {
							// target번 글자 지우기
							sb.delete(target, target + 1);
						}
					}
				}
			}
			if (sb.length() == 0)
				result.add("EMPTY");
			else
				result.add(sb.toString());
			return;
		}
		// 순열 구하기
		for (int index = 1; index <= N; index++) {
			if (counts[index] == 0)
				continue;
			// index번 사람의 카드 낸 횟수 -1
			counts[index]--;
			selected[depth] = index;
			dfs(counts, selected, depth + 1);
			// index번 사람의 카드 낸 횟수 +1
			counts[index]++;
		}
	}
}
