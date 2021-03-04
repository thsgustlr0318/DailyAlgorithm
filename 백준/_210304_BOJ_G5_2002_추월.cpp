#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, res = 0;
	cin >> N;
	string car;
	vector<string> start(N), end(N);
	for (int i = 0; i < N; i++) {
		cin >> car;
		start[i] = car;
	}
	for (int i = 0; i < N; i++) {
		cin >> car;
		end[i] = car;
	}
	for (int i = 0, endIdx = 0; i < N; i++) {
		string cur = end[endIdx];
		int startIdx;
		for (startIdx = 0; cur != start[startIdx]; startIdx++);
		if (endIdx < startIdx) {
			res++;
			end.erase(end.begin() + endIdx);
			start.erase(start.begin() + startIdx);
		}
		else {
			endIdx++;
		}
	}

	cout << res;
}