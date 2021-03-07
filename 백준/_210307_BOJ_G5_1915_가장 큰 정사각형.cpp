#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
using namespace std;
int dp[1001][1001], arr[1001][1001];
int main()
{
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	int n, m, res = 0;
	cin >> n >> m;

	string str;
	for (int i = 0; i < n; i++) {
		cin >> str;
		for (int j = 0; j <= m; j++)
			arr[i + 1][j + 1] = str[j] - '0';
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			if (arr[i][j] == 0) continue;
			//왼쪽 위쪽 모두 1인경우, dp[i][j] = dp[왼쪽 위쪽 대각선] 중 최소 + 1
			if (arr[i - 1][j] == 1 && arr[i][j - 1] == 1)
				dp[i][j] = min(dp[i][j - 1], min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
			else {
				dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + 1;
			}
		}
	}
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++)
			res = max(res, dp[i][j]);
	cout << res * res;
}