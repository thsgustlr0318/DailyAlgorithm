#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
struct info{
	int num, cost;
};
//비용은 작은순, 숫자는 큰순
bool cmp(info a, info b) {
	if (a.cost == b.cost) return a.num > b.num;
	return a.cost < b.cost;
}
//숫자 큰순
bool cmp2(info a, info b) {
	return a.num > b.num;
}

int main()
{
	int N, money;
	cin >> N;

	if (N == 1) {
		cout << 0;
		return 0;
	}

	vector<info> arr(N);
	for (int i = 0; i < N; i++) {
		cin >> arr[i].cost;
		arr[i].num = i;
	}
	cin >> money;
	//비용은 작은순, 숫자는 큰순으로 정렬
	sort(arr.begin(), arr.end(), cmp);
	//가장 숫자가 많이 들어갈 수 있도록 candidate 구하기
	vector<info> candidate;
	int total = money;
	//첫 글자에 0이 들어갈 수 없으므로 예외처리
	if (arr[0].num == 0) {
		candidate.push_back(arr[1]);
		total -= arr[1].cost;
	}
	//나머지 candidate 구하기
	for (; total - arr[0].cost >= 0; total -= arr[0].cost)
		candidate.push_back(arr[0]);
	//숫자 큰순으로 정렬
	sort(arr.begin(), arr.end(), cmp2);
	//candidate의 첫번째 원소부터 큰 숫자로 바꿀 수 있으면, 바꾸고 다음 원소 탐색
	for (int idx = 0; idx < candidate.size(); idx++) {
		for (int i = 0; i < N; i++) {
			if (total + candidate[idx].cost - arr[i].cost >= 0) {
				total = total + candidate[idx].cost - arr[i].cost;
				candidate[idx] = arr[i];
				break;
			}
		}
	}
	for (int i = 0; i < candidate.size(); i++)
		cout << candidate[i].num;
}