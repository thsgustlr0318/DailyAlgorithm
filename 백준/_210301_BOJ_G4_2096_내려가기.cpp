#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
	int  N;
	cin >> N;
	int arr[3];
	int dpMin[2][3], dpMax[2][3];
	for (int i = 0; i < 3; i++) {
		cin >> arr[i];
		//N = 1인 경우를 위해 두번째 칸도 초기화
		dpMin[0][i] = dpMin[1][i] = dpMax[1][i] = dpMax[0][i] = arr[i];
	}
	for (int idx = 1; idx < N; idx++) {
		for (int j = 0; j < 3; j++)
			cin >> arr[j];
		dpMin[1][0] = min(dpMin[0][0], dpMin[0][1]) + arr[0];
		dpMin[1][1] = min(dpMin[0][0], min(dpMin[0][1], dpMin[0][2])) + arr[1];
		dpMin[1][2] = min(dpMin[0][1], dpMin[0][2]) + arr[2];

		dpMax[1][0] = max(dpMax[0][0], dpMax[0][1]) + arr[0];
		dpMax[1][1] = max(dpMax[0][0], max(dpMax[0][1], dpMax[0][2])) + arr[1];
		dpMax[1][2] = max(dpMax[0][1], dpMax[0][2]) + arr[2];

		for (int i = 0; i < 3; i++ ) {
			dpMin[0][i] = dpMin[1][i];
			dpMax[0][i] = dpMax[1][i];
		}
	}
	int minv = min(dpMin[1][0], min(dpMin[1][1], dpMin[1][2]));
	int maxv = max(dpMax[1][0], max(dpMax[1][1], dpMax[1][2]));

	cout << maxv << " " << minv;
}