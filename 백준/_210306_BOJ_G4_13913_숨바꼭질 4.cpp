#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;
#define MAXLEN 100000
int arr[100001], n, k;

int main()
{
	cin >> n >> k;
	arr[n] = 1;
	queue<pair<int, int>> q;
	vector<pair<int, int>> res;
	q.push({ n, 1 });
	//bfs
	while (!q.empty()) {
		pair<int, int> cur = q.front();
		q.pop();
		//���� ��ġ ã�� ���
		if (cur.first == k) {
			cout << cur.second -1 << "\n";
			int idx = cur.first;
			int cnt = cur.second - 1;
			
			res.push_back(cur);
			//�ٷ� ������ �����̰� �־��� �ڸ� ������ Ž��
			while (cnt != 0) {
				//���� ��ġ�� Ȧ���� �ƴϰ�, �ٷ� ������ 2*X ��ġ�� ������ ���
				if (!(idx & 1) && arr[idx / 2] == cnt) {
					idx = idx / 2;
					res.push_back({ idx, cnt-- });
					continue;
				}
				//�ٷ� ������ X+1 ��ġ�� ������ ���
				else if (idx + 1 <= MAXLEN && arr[idx + 1] == cnt) {
					idx = idx + 1;
					res.push_back({ idx, cnt-- });
					continue;
				}
				//�ٷ� ������ X-1 ��ġ�� ������ ���
				else if (idx - 1 >= 0 && arr[idx - 1] == cnt) {
					idx = idx - 1;
					res.push_back({ idx, cnt-- });
					continue;
				}
			}
			break;
		}
		//���� ��ġ Ž��
		//2*X �����̵�
		int next = cur.first * 2;
		if (next <= MAXLEN && arr[next] == 0) {
			arr[next] = cur.second + 1;
			q.push({ next,cur.second + 1 });
		}
		//X+1 �̵�
		next = cur.first + 1;
		if (next <= MAXLEN && arr[next] == 0) {
			arr[next] = cur.second + 1;
			q.push({ next,cur.second + 1 });
		}
		//X-1 �̵�
		next = cur.first - 1;
		if (next >= 0 && arr[next] == 0) {
			arr[next] = cur.second + 1;
			q.push({ next,cur.second + 1 });
		}
	}
	for (int i = res.size() - 1; i >= 0; i--) {
		cout << res[i].first << " ";
	}
}
