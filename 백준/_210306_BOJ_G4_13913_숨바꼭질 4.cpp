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
		//동생 위치 찾은 경우
		if (cur.first == k) {
			cout << cur.second -1 << "\n";
			int idx = cur.first;
			int cnt = cur.second - 1;
			
			res.push_back(cur);
			//바로 이전에 수빈이가 있었던 자리 역으로 탐색
			while (cnt != 0) {
				//현재 위치가 홀수가 아니고, 바로 이전에 2*X 위치로 움직인 경우
				if (!(idx & 1) && arr[idx / 2] == cnt) {
					idx = idx / 2;
					res.push_back({ idx, cnt-- });
					continue;
				}
				//바로 이전에 X+1 위치로 움직인 경우
				else if (idx + 1 <= MAXLEN && arr[idx + 1] == cnt) {
					idx = idx + 1;
					res.push_back({ idx, cnt-- });
					continue;
				}
				//바로 이전에 X-1 위치로 움직인 경우
				else if (idx - 1 >= 0 && arr[idx - 1] == cnt) {
					idx = idx - 1;
					res.push_back({ idx, cnt-- });
					continue;
				}
			}
			break;
		}
		//다음 위치 탐색
		//2*X 순간이동
		int next = cur.first * 2;
		if (next <= MAXLEN && arr[next] == 0) {
			arr[next] = cur.second + 1;
			q.push({ next,cur.second + 1 });
		}
		//X+1 이동
		next = cur.first + 1;
		if (next <= MAXLEN && arr[next] == 0) {
			arr[next] = cur.second + 1;
			q.push({ next,cur.second + 1 });
		}
		//X-1 이동
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
