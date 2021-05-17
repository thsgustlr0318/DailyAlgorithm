import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class _210513_BOJ_G2_21609_상어_중학교 {
	static int[][] moveDir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			boolean[][] visited = new boolean[N][N];
			PriorityQueue<info> pq = new PriorityQueue<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > 0) {
						if (visited[i][j])
							continue;
						int count = 1, rainbow = 0, num = arr[i][j];
						PriorityQueue<minBlock> blockpq = new PriorityQueue<>();
						blockpq.add(new minBlock(i, j));
						Queue<Integer> q = new LinkedList<>();
						Queue<Integer> ytemp = new LinkedList<>();
						Queue<Integer> xtemp = new LinkedList<>();
						Queue<Integer> yzero = new LinkedList<>();
						Queue<Integer> xzero = new LinkedList<>();
						visited[i][j] = true;
						q.add(i);
						q.add(j);
						while (!q.isEmpty()) {
							int y = q.poll();
							int x = q.poll();
							for (int dir = 0; dir < 4; dir++) {
								int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
								if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || arr[ny][nx] == -1 || arr[ny][nx] == -2)
									continue;
								if (!(arr[ny][nx] == 0 || arr[ny][nx] == num))
									continue;
								if (arr[ny][nx] == 0) {
									yzero.add(ny);
									xzero.add(nx);
									rainbow++;
								}
								if (arr[ny][nx] == num) {
									blockpq.add(new minBlock(ny, nx));
								}
								count++;
								visited[ny][nx] = true;
								q.add(ny);
								q.add(nx);
								ytemp.add(ny);
								xtemp.add(nx);
							}
						}
						if (count == 1) {
							while (!ytemp.isEmpty()) {
								int yy = ytemp.poll(), xx = xtemp.poll();
								visited[yy][xx] = false;
							}
						} else {
							pq.add(new info(blockpq.peek().y, blockpq.peek().x, count, rainbow, num));
							while (!yzero.isEmpty()) {
								visited[yzero.poll()][xzero.poll()] = false;
							}
						}
					}
				}
			}
			if (pq.isEmpty())
				break;
			info cur = pq.poll();
			answer += Math.pow(cur.count, 2);
			for (int i = 0; i < N; i++)
				Arrays.fill(visited[i], false);
			Queue<Integer> q = new LinkedList<>();
			q.add(cur.y);
			q.add(cur.x);
			visited[cur.y][cur.x] = true;
			arr[cur.y][cur.x] = -2;
			while (!q.isEmpty()) {
				int y = q.poll();
				int x = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || arr[ny][nx] == -1 || arr[ny][nx] == -2)
						continue;
					if (arr[ny][nx] == cur.num || arr[ny][nx] == 0) {
						visited[ny][nx] = true;
						arr[ny][nx] = -2;
						q.add(ny);
						q.add(nx);
					}
				}
			}
			//중력
			arr = gravity(N, arr);
			//회전
			arr = rotate(N, arr);
			//중력
			arr = gravity(N, arr);
		}
		System.out.println(answer);
	}

	static int[][] rotate(int N, int[][] arr) {
		//복사
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				temp[i][j] = arr[i][j];
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = arr[j][N - i - 1];
			}
		}
		return temp;
	}

	static int[][] gravity(int N, int[][] arr) {
		for (int x = 0; x < N; x++) {
			Stack<Integer> stack = new Stack<>();
			for (int y = 0; y < N; y++) {
				if (arr[y][x] == -1) {
					for (int yy = y - 1; yy >= 0 && arr[yy][x] != -1; yy--) {
						if (!stack.isEmpty())
							arr[yy][x] = stack.pop();
					}
				} else if (arr[y][x] != -2) {
					stack.add(arr[y][x]);
					arr[y][x] = -2;
				}
			}
			int y = N - 1;
			while (!stack.isEmpty()) {
				arr[y][x] = stack.pop();
				y--;
			}
		}
		return arr;
	}

	static class info implements Comparable<info> {
		int y, x, count, rainbow, num;

		info(int y, int x, int count, int rainbow, int num) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.rainbow = rainbow;
			this.num = num;
		}

		@Override
		public int compareTo(info o) {
			if (this.count == o.count) {
				if (this.rainbow == o.rainbow) {
					if (this.y == o.y) {
						return o.x - this.x;
					}
					return o.y - this.y;
				}
				return o.rainbow - this.rainbow;
			}
			return o.count - this.count;
		}
	}

	static class minBlock implements Comparable<minBlock> {
		int y, x;

		minBlock(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(minBlock o) {
			if (this.y == o.y)
				return this.x - o.x;
			return this.y - o.y;
		}
	}
}
