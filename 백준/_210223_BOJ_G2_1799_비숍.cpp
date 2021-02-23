#include <iostream>
#include <algorithm>
using namespace std;

int N, res = 0;
int map[10][10];
bool visited[10][10];
//현재 위치부터 오른쪽 위 대각선 방향에 비숍이 있는지 확인
bool check(int y, int x) {
	while (y >= 0 && x < N) {
		if (visited[y][x])
			return false;
		y--, x++;
	}
	return true;
}
//sy: y시작, sx: x시작, cnt: 비숍 개수
void dfs(int sy, int sx, int cnt) {
	if (sy == N) {
		res = max(res, cnt);
		return;
	}
	//윗줄 탐색
	if (sy == 0 && sx != 0) {
		bool flag = false;
		for (int y = 0, x = sx; y < N && x < N; y++, x++) {
			if (y < 0 || x < 0 || y >= N || x >= N || map[y][x] == 0 || !check(y - 1, x + 1)) continue;
			flag = true;
			visited[y][x] = true;
			dfs(0, sx - 1, cnt + 1);
			visited[y][x] = false;
		}
		if (!flag)
			dfs(0, sx - 1, cnt);
	}
	//왼족줄 탐색
	else {
		bool flag = false;
		for (int y = sy, x = 0; y < N && x < N; y++, x++) {
			if (y < 0 || x < 0 || y >= N || x >= N || map[y][x] == 0 || !check(y - 1, x + 1)) continue;
			flag = true;
			visited[y][x] = true;
			dfs(sy + 1, 0, cnt + 1);
			visited[y][x] = false;
		}
		if (!flag)
			dfs(sy + 1, 0, cnt);
	}
}
int main()
{
	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> map[i][j];
	dfs(0, N - 1, 0);
	cout << res;
}