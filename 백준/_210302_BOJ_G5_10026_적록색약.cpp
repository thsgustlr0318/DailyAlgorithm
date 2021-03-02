#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

char map[100][100];
bool visited[100][100];
int moveDir[4][2] = { {1,0},{-1,0},{0,1},{0,-1} };
int n;
int bfs() {
	int result = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (visited[i][j]) continue;
			queue<pair<int, int>> q;
			visited[i][j] = true;
			q.push({ i, j });
			result++;
			while (!q.empty()) {
				int y = q.front().first, x = q.front().second;
				q.pop();

				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx] || map[y][x] != map[ny][nx]) continue;
					visited[ny][nx] = true;
					q.push({ ny,nx });
				}
			}
		}
	}
	return result;
}

int main()
{
	int res1 = 0, res2 = 0;
	cin >> n;
	for(int i=0; i<n; i++)
		for (int j = 0; j < n; j++) 
			cin >> map[i][j];
	//적록색약 아닌 사람 탐색
	res1 = bfs();
	//적록색약 탐색 위해 'R'을 'G'로 변경
	memset(visited, false, sizeof(visited));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			if (map[i][j] == 'R')
				map[i][j] = 'G';
	//적록색약인 사람 탐색
	res2 = bfs();
	cout << res1 << " " << res2;
}