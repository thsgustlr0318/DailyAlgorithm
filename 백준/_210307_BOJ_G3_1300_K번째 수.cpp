#include <iostream>
#include <algorithm>
using namespace std;
int n, k, res;
int binarySearch(int left, int right) {
	int mid = 0;
	while (left <= right) {
		//�������� �����Ͽ��� �� �� mid�� ���� �� �ִ� index ã��
		//minCount(�ּ� �ε���) <= mid <= maxCount(�ִ� �ε���)
		int minCount = 0, maxCount = 0;
		mid = (left + right) / 2;

		for (int i = 1; i <= n; i++) {
			int cnt = min(n, mid / i);
			minCount += cnt;
			maxCount += cnt;
			if (cnt * i == mid)
				minCount--;
		}
		minCount++;

		if (minCount <= k && k <= maxCount)
			break;
		else if (maxCount >= k)
			right = mid - 1;
		else
			left = mid + 1;
	}
	return mid;
}
int main()
{
	cin >> n >> k;
	cout << binarySearch(1, 1000000000);
}