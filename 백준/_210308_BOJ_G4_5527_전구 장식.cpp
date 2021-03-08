#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	int n;
	cin >> n;
	vector<int> arr(n + 1), dp(n + 1, 0), candidate;
	for (int i = 1; i <= n; i++)
		cin >> arr[i];
	//각 전구마다 최대 교대 패턴 수 구하기
	int prevValue = arr[1];
	dp[1] = 1;
	for (int i = 2; i <= n; i++) {
		if (prevValue != arr[i]) {
			dp[i] = dp[i - 1] + 1;
		}
		else {
			dp[i] = 1;
		}
		prevValue = arr[i];
	}
	//최대 교대 패턴에서 최대 수만 따로 구하기
	int prev = dp[1];
	for (int i = 2; i <= n; i++) {
		if (dp[i] == 1) {
			candidate.push_back(prev);
			prev = 1;
		}
		else
			prev++;
		if (i == n)
			candidate.push_back(prev);
	}
	int res = 0;
	//최대 교대 패턴이 3개 이상인경우, 연속하는 3개의 교대 패턴 값 더하기
	if (candidate.size() == 1)
		res = candidate[0];
	else if(candidate.size() == 2)
		res = candidate[0] + candidate[1];
	else {
		for (int i = 2; i < candidate.size(); i++) {
			res = max(res, candidate[i] + candidate[i - 1] + candidate[i - 2]);
		}
	}
	cout << res;
}