#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int moveDir[4][2] = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
int v[100][100];
bool visited[100][100];
int main()
{
	int N, M, prev = 0, res = 0;
	cin >> N >> M;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> v[i][j];

	while (true) {
		int cnt = 0;
		queue<pair<int, int>> q;
		memset(visited, false, sizeof(visited));
		//0, 0번째 칸부터 bfs 탐색
		q.push({ 0, 0 });
		visited[0][0] = true;
		while (!q.empty()) {
			int y = q.front().first;
			int x = q.front().second;
			q.pop();

			for (int dir = 0; dir < 4; dir++) {
				int ny = y + moveDir[dir][0];
				int nx = x + moveDir[dir][1];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx]) continue;
				visited[ny][nx] = true;
				//녹게되는 치즈
				if (v[ny][nx] == 1) {
					cnt++;
					v[ny][nx] = 0;
				}
				//공기
				else
					q.push({ ny, nx });
			}
		}
		//녹을 치즈가 없으면, break
		if (cnt == 0)
			break;
		res++;
		prev = cnt;
	}
	cout << res << "\n" << prev;
}