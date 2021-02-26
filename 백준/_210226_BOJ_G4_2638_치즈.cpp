#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int moveDir[4][2] = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
int v[100][100];
int visited[100][100];
int main()
{
	int N, M, res = 0;
	cin >> N >> M;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> v[i][j];

	while (true) {
		int cnt = 0;
		queue<pair<int, int>> q;
		memset(visited, 0, sizeof(visited));
		//0, 0번째 칸부터 bfs 탐색
		q.push({ 0, 0 });
		visited[0][0]++;
		while (!q.empty()) {
			int y = q.front().first;
			int x = q.front().second;
			q.pop();

			for (int dir = 0; dir < 4; dir++) {
				int ny = y + moveDir[dir][0];
				int nx = x + moveDir[dir][1];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || (v[ny][nx] == 0 && visited[ny][nx])) continue;
				visited[ny][nx]++;
				if (v[ny][nx] == 0)
					q.push({ ny, nx });
			}
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				//두 변 이상 공기랑 접촉한 경우
				if (visited[i][j] >= 2) {
					cnt++;
					v[i][j] = 0;
				}
			}
		//녹을 치즈가 없으면, break
		if (cnt == 0)
			break;
		res++;
	}
	cout << res;
}