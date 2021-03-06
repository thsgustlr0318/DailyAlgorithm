#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
using namespace std;
char map[1000][1000];
int visited[1000][1000];
int moveDir[4][2] = { {1,0},{-1,0},{0,1},{0,-1} };
queue<pair<int, int>> fire, location;
int main()
{
	int tc, w, h; 
	cin >> tc;
	while (tc--) {
		//�ʱ�ȭ
		memset(visited, false, sizeof(visited));
		while (!fire.empty())
			fire.pop();
		while (!location.empty())
			location.pop();
		//input
		int res = 0;
		cin >> w >> h;
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++) {
				cin >> map[i][j];
				if (map[i][j] == '*')
					fire.push({ i, j });
				else if (map[i][j] == '@') {
					visited[i][j] = 1;
					location.push({ i,j });
				}
			}

		bool check = false;
		//bfs
		while(!location.empty() && !check) {
			//�� ������
			int fireSize = fire.size();
			//���� ���� ũ�⸸ŭ Ž��
			for(int i=0; i< fireSize; i++) {
				pair<int, int> cur = fire.front();
				int y = cur.first, x = cur.second;
				fire.pop();

				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= h || nx >= w || map[ny][nx] == '*' || map[ny][nx] != '.') continue;
					map[ny][nx] = '*';
					fire.push({ ny, nx });
				}
			}
			//����� Ż��
			int locationSize = location.size();
			//�� �ʸ��� ����̰� ���� �� �ִ� ��ġ�� Ž��
			for (int idx = 0; idx < locationSize; idx++) {
				pair<int, int> cur = location.front();
				int y = cur.first, x = cur.second;
				location.pop();
				//����� Ż���� �� �ִ°��
				if (y == 0 || x == 0 || y == h - 1 || x == w - 1) {
					check = true;
					res = visited[y][x];
					break;
				}
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + moveDir[dir][0], nx = x + moveDir[dir][1];
					if (ny < 0 || nx < 0 || ny >= h || nx >= w || visited[ny][nx] || map[ny][nx] != '.') continue;
					visited[ny][nx] = visited[y][x] + 1;
					location.push({ ny, nx });
				}
			}
		}
		if (!check)
			cout << "IMPOSSIBLE\n";
		else
			cout << res << "\n";
	}
}