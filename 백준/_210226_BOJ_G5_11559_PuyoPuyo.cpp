#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int moveDir[4][2] = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
char map[12][6];
bool visited[12][6];
int main()
{
	int res = 0;
	for (int i = 0; i < 12; i++)
		for (int j = 0; j < 6; j++)
			cin >> map[i][j];

	while (true) {
		bool check = false;
		memset(visited, false, sizeof(visited));
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] == '.' || visited[i][j]) continue;
				//q: bfs 할 때 사용
				queue<pair<int, int>> q;
				//q2: 인접한 뿌요가 4개 이상인 경우, 제거하는데 사용
				queue<pair<int, int>> q2;
				q.push({ i, j });
				q2.push({ i, j });
				visited[i][j] = true;
				char cur = map[i][j];
				int cnt = 1;
				while(!q.empty()){
					int y = q.front().first;
					int x = q.front().second;
					q.pop();

					for (int dir = 0; dir < 4; dir++) {
						int ny = y + moveDir[dir][0];
						int nx = x + moveDir[dir][1];
						if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6 || visited[ny][nx] || (map[ny][nx] != cur)) continue;
						cnt++;
						visited[ny][nx] = 1;
						q.push({ ny, nx });
						q2.push({ ny, nx });
					}
				}
				//인접한 뿌요가 4개 이상인경우, q2를 사용해서 .로 바꿔줌
				if (cnt >= 4) {
					check = true;
					while (!q2.empty()) {
						map[q2.front().first][q2.front().second] = '.';
						q2.pop();
					}
				}
			}
		}
		//터질 뿌요가 없으면 탈출
		if (!check) break;
		res++;

		//뿌요 터뜨린 후 아래로 내리기
		for (int x = 0; x < 6; x++) {
			//q3: 뿌요 정렬 위해 사용
			queue<char> q3;
			for (int y = 11; y >= 0; y--) {
				if(map[y][x] != '.')
					q3.push(map[y][x]);
			}
			for (int y = 11; y >= 0; y--) {
				if (!q3.empty()) {
					map[y][x] = q3.front();
					q3.pop();
				}
				else
					map[y][x] = '.';
			}
		}
	}
	cout << res;
}