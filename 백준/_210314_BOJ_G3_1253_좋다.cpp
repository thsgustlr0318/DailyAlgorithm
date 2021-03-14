#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	int n, res = 0;
	cin >> n;
	vector<int> arr(n, 0);
	for (int i = 0; i < n; i++)
		cin >> arr[i];
	sort(arr.begin(), arr.end());

	for (int index = 0; index < n; index++) {
		int cur = arr[index];
		int left = 0, right = n - 1;
		while (left < right) {
			int total = arr[left] + arr[right];
			if (total < cur) {
				left++;
			}
			else if (total > cur) {
				right--;
			}
			else {
				if (left != index && right != index) {
					res++;
					break;
				}
				else if (left == index)
					left++;
				else if (right == index)
					right--;
			}
		}
	}
	cout << res;
}