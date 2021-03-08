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
	//�� �������� �ִ� ���� ���� �� ���ϱ�
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
	//�ִ� ���� ���Ͽ��� �ִ� ���� ���� ���ϱ�
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
	//�ִ� ���� ������ 3�� �̻��ΰ��, �����ϴ� 3���� ���� ���� �� ���ϱ�
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