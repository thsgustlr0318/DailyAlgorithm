#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

#define mod 1000000000;
long long dp[101][10];

int main()
{
	int N;
	long long res = 0;
	cin >> N;
	dp[1][0] = 0;
	dp[1][1] = dp[1][2] = dp[1][3] = dp[1][4] = dp[1][5] = dp[1][6] = dp[1][7] = dp[1][8] = dp[1][9] = 1;
	

	for (int i = 2; i <= N; i++) {
		for (int j = 0; j < 10; j++) {
			if (j == 0) {
				dp[i][j] = dp[i - 1][1] % mod;
			}
			else if (j == 9) {
				dp[i][j] = (dp[i - 1][j - 1]) % mod;
			}
			else {
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
			}
		}
	}
	for (int i = 0; i < 10; i++)
		res = (res + dp[N][i]) % mod;
	cout << res;
}

/*
long long dfs(int cnt, int num) {
	if (num < 0 || num >= 10) return 0;
	if (cnt == N)
		return 1;
	long long& ret = dp[cnt][num];
	if (ret != -1)
		return ret;
	ret = 0;

	ret = (ret + dfs(cnt + 1, num + 1)) % mod;
	ret = (ret + dfs(cnt + 1, num - 1)) % mod;
	return ret;
}
int main()
{
	memset(dp, -1, sizeof(dp));
	cin >> N;
	for (int i = 1; i <= 9; i++) {
		res = (res + dfs(1, i)) % mod;
	}
	cout << res;
}
*/