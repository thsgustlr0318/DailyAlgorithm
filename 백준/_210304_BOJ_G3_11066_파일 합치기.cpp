#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

#define INF 1e9
int dp[501][501];
int sum[501];
int main()
{
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	int tc, n, num;
	cin >> tc;
	while (tc--) {
		memset(dp, 0, sizeof(dp));
		memset(sum, 0, sizeof(sum));
		cin >> n;
		for (int i = 1; i <= n; i++) {
			cin >> num;
			sum[i] = sum[i - 1] + num;
		}
		for (int j = 2; j <= n; j++) {
			for (int i = j - 1; i > 0; i--) {
				dp[i][j] = INF;
				for (int k = i; k <= j; k++) {
					dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
				}
			}
		}
		cout << dp[1][n] << "\n";
	}
}