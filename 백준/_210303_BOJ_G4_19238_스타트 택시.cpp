#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <cstring>
using namespace std;


struct customer {
	int sy, sx, ey, ex;
};
struct pos {
	int y, x, depth;
};
bool cmp(pos& a, pos& b) {
	if (a.y == b.y) return a.x < b.x;
	return a.y < b.y;
}

int visit[20][20];
int board[20][20];
int moveDir[4][2] = { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };
customer customers[401];

int main()
{
	int n, m, fuel, start_x, start_y;
	cin >> n >> m >> fuel;

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) {
			cin >> board[i][j];
			if (board[i][j] == 1)
				board[i][j] = -1;
		}
	cin >> start_y >> start_x;
	start_x--, start_y--;
	for (int i = 1; i <= m; i++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;
		a--, b--, c--, d--;
		customers[i] = { a,b,c,d };
		board[a][b] = i;
	}

	while (m--) {
		memset(visit, 0, sizeof(visit));
		queue<pos> q;
		q.push({ start_y, start_x, 0 });
		visit[start_y][start_x] = true;

		//candidate: �ִܰŸ��� ���� ª�� �°� �ĺ�
		vector<pos> candidate;
		//distance: �°����� �ִܰŸ�
		int distance = -1;
		while (!q.empty()) {
			pos cur = q.front();
			q.pop();
			//�°��� �ִ� ���
			if (board[cur.y][cur.x] >= 1) {
				//�ִܰŸ� �°��� ã�� ���
				if (distance == -1) 
					distance = cur.depth;
				//��� �ִܰŸ� �ĺ��� candidate�� �ֱ�
				if (distance == cur.depth)
					candidate.push_back(cur);
				continue;
			}
			//�ִܰŸ� �̻��� Ž�� ����
			if (distance != -1 && cur.depth > distance) continue;

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + moveDir[i][0], nx = cur.x + moveDir[i][1];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == -1) continue;
				visit[ny][nx] = true;
				q.push({ ny, nx, cur.depth + 1 });
			}
		}
		//�°��� ��ã�� ���, ����ó��
		if (candidate.empty()) {
			cout << -1;
			return 0;
		}
		//���ȣ, ����ȣ�� ���� ���� �°� ã��
		sort(candidate.begin(), candidate.end(), cmp);
		//s: �°� ����
		pos s = candidate[0];
		//passenger: �°� ��ȣ
		int passenger = board[s.y][s.x];
		board[s.y][s.x] = 0;

		memset(visit, 0, sizeof(visit));
		q.push({ s.y, s.x, 0 });
		visit[s.y][s.x] = true;
		//e: ���� ����
		pos e = { -1, -1, -1 };
		while (!q.empty()) {
			pos cur = q.front();
			q.pop();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + moveDir[i][0], nx = cur.x + moveDir[i][1];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == -1) continue;
				visit[ny][nx] = true;
				q.push({ ny, nx, cur.depth + 1 });
				//�°��� ������
				if (ny == customers[passenger].ey && nx == customers[passenger].ex) {
					e = { ny, nx, cur.depth + 1 };
					break;
				}
			}
			if (e.depth != -1) break;
		}
		//�������� ã�� ���� ���, ����ó��
		if (e.depth == -1) {
			cout << -1;
			return 0;
		}
		//���ΰ� �����ؼ� ���������� ���� ���ϴ� ���
		if (fuel < s.depth + e.depth) {
			cout << -1;
			return 0;
		}
		fuel = fuel - s.depth + e.depth;
		start_y = e.y, start_x = e.x;
	}
	cout << fuel;
}