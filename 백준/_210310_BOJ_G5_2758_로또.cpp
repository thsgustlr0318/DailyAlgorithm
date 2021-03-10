#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;
long long dp[11][2001];
int main()
{
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	int tc, n, m;
	for (int x = 1; x <= 2000; x++)
		dp[1][x] = 1;
	//dp[y][x]: x번 숫자까지 y개 뽑았을 때의 개수
	for (int y = 2; y <= 10; y++) {
		for (int x = y; x <= 2000; x++) {
			for (int idx = 1; idx <= x; idx++)
				//x개 이전 수보다 적어도 2배 이상일 경우, dp[y][x]에 dp[y-1][idx] 추가
				if (idx * 2 <= x)
					dp[y][x] += dp[y - 1][idx];
		}
	}
	cin >> tc;
	while (tc--) {
		cin >> n >> m;

		long long res = 0;
		for (int i = 1; i <= m; i++)
			res += dp[n][i];
		cout << res << "\n";
	}
}