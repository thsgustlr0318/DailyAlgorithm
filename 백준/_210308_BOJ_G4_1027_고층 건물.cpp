#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
double building[50];
int res[50];
double calc(int start, int mid, int end)
{
	double temp = (building[end]-building[start]) * ((double)mid - start);
	return temp / ((double)end - start);
}
int main()
{
	int n, result = 0;
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> building[i];
	for (int start = 0; start < n; start++) {
		for (int end = 0; end < n; end++) {
			//자기 자신 제외
			if (start == end) continue;
			//양 옆 건물은 볼 수 있음
			if (abs(start - end) == 1)
				res[start]++;
			else if (start < end) {
				bool check = true;
				//start와 end 사이에 있는 모든 mid건물에 대해서 확인
				for (int mid = start + 1; check && mid < end; mid++) {
					if (calc(start, mid, end) + building[start] <= building[mid]) {
						check = false;
					}
				}
				if (check)
					res[start]++;
			}
			else {
				bool check = true;
				for (int mid = end + 1; check && mid < start; mid++) {
					if (calc(end, mid, start) + building[end] <= building[mid]) {
						check = false;
					}
				}
				if (check)
					res[start]++;
			}
		}
	}
	for (int i = 0; i < n; i++)
		result = max(result, res[i]);
	cout << result;
}