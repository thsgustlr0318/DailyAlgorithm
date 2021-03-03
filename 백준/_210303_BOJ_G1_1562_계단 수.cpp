#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

#define mod 1000000000;
long long dp[101][10][1024];

int main()
{
	int N;
	long long res = 0;
	cin >> N;
	for (int i = 1; i <= 9; i++)
		dp[1][i][1 << i] = 1;
	dp[1][0][0] = 0;

	for (int i = 2; i <= N; i++) {
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 1024; k++) {
				if (j == 0) {
					dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][1][k]) % mod;
				}
				else if (j == 9) {
					dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][8][k]) % mod;
				}
				else {
					dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % mod;
				}
			}
		}
	}
	for (int i = 0; i < 10; i++)
		res = (res + dp[N][i][1023]) % mod;
	cout << res;
}

/*
long long dfs(int cnt, int num, int visit) {
	if (num < 0 || num >= 10) return 0;
	if (cnt == N) {
		if (visit == 1023)
			return 1;
		else
			return 0;
	}
	long long& ret = dp[cnt][num][visit];
	if (ret != -1)
		return ret;
	ret = 0;

	ret = (ret + dfs(cnt + 1, num + 1, visit | 1 << (num + 1))) % mod;
	ret = (ret + dfs(cnt + 1, num - 1, visit | 1 << (num - 1))) % mod;
	return ret;
}
int main()
{
	memset(dp, -1, sizeof(dp));
	cin >> N;
	for (int i = 1; i <= 9; i++) {
		res = (res + dfs(1, i, 1 << i)) % mod;
	}
	cout << res;
}
*/