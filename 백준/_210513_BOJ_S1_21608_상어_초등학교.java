import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _210513_BOJ_S1_21608_상어_초등학교 {
	static int[][] moveDir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		int[][] arr = new int[N][N];
		HashMap<Integer, student> students = new HashMap<>();
		for (int index = 0; index < N * N; index++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			students.put(num, new student(a, b, c, d));
			if (index == 0) {
				arr[1][1] = num;
			} else {
				//모든 칸 탐색해서 좋아하는 학생의 수, 빈 칸의 개수 구하기
				PriorityQueue<info> pq = new PriorityQueue<>();
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < N; x++) {
						int like = 0, blank = 0;
						for (int dir = 0; dir < 4; dir++) {
							int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
							if (ny < 0 || nx < 0 || ny >= N || nx >= N)
								continue;
							if (arr[ny][nx] == 0)
								blank++;
							else if (arr[ny][nx] == a || arr[ny][nx] == b || arr[ny][nx] == c || arr[ny][nx] == d)
								like++;
						}
						pq.add(new info(y, x, blank, like));
					}
				}
				//pq조건에  맞는 것 빈 칸 찾아서 채우기
				while (true) {
					info cur = pq.poll();
					if (arr[cur.y][cur.x] == 0) {
						arr[cur.y][cur.x] = num;
						break;
					}
				}
			}
		}
		//모든 칸 탐색
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				//s: (y, x)좌표에 앉은 학생 정보 
				student s = students.get(arr[y][x]);
				int count = 0;
				//인접한 칸 학생들 탐색
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N)
						continue;
					//인접한 학생이 좋아하는 학생이면, count++
					if (arr[ny][nx] == s.a || arr[ny][nx] == s.b || arr[ny][nx] == s.c || arr[ny][nx] == s.d)
						count++;
				}
				if (count == 0)
					answer += 0;
				else if (count == 1)
					answer += 1;
				else if (count == 2)
					answer += 10;
				else if (count == 3)
					answer += 100;
				else
					answer += 1000;
			}
		}
		System.out.println(answer);
	}

	static class student {
		int a, b, c, d;

		student(int a, int b, int c, int d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
	}

	static class info implements Comparable<info> {
		int y, x, blank, like;

		info(int y, int x, int blank, int like) {
			this.y = y;
			this.x = x;
			this.blank = blank;
			this.like = like;
		}

		@Override
		public int compareTo(info o) {
			if (this.like == o.like) {
				if (this.blank == o.blank) {
					if (this.y == o.y) {
						return this.x - o.x;
					}
					return this.y - o.y;
				}
				return o.blank - this.blank;
			}
			return o.like - this.like;
		}
	}
}
