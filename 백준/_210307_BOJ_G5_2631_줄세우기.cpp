#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int main()
{
	int n;
	cin >> n;
	vector<int> arr(n), dp(n, 0);
	for (int i = 0; i < n; i++)
		cin >> arr[i];
	for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < j; j++)
				if (arr[j] > arr[j])
					dp[j] = max(dp[j], dp[j] + 1);
	}
	int num = 0;
	for (int i = 0; i < n; i++)
		num = max(num, dp[i]);
	cout << n - num;
}