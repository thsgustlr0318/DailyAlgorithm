#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
struct info{
	int num, cost;
};
//����� ������, ���ڴ� ū��
bool cmp(info a, info b) {
	if (a.cost == b.cost) return a.num > b.num;
	return a.cost < b.cost;
}
//���� ū��
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
	//����� ������, ���ڴ� ū������ ����
	sort(arr.begin(), arr.end(), cmp);
	//���� ���ڰ� ���� �� �� �ֵ��� candidate ���ϱ�
	vector<info> candidate;
	int total = money;
	//ù ���ڿ� 0�� �� �� �����Ƿ� ����ó��
	if (arr[0].num == 0) {
		candidate.push_back(arr[1]);
		total -= arr[1].cost;
	}
	//������ candidate ���ϱ�
	for (; total - arr[0].cost >= 0; total -= arr[0].cost)
		candidate.push_back(arr[0]);
	//���� ū������ ����
	sort(arr.begin(), arr.end(), cmp2);
	//candidate�� ù��° ���Һ��� ū ���ڷ� �ٲ� �� ������, �ٲٰ� ���� ���� Ž��
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