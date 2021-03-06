#include <iostream>
#include <string>
#include <algorithm>
#include <stack>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	string str, boom;
	cin >> str >> boom;
	int lenBoom = boom.length(), lenStr = str.length();
	stack<char> s;
	//ù ���ں��� Ž��
	for (int idx = 0; idx < lenStr; idx++) {
		char cur = str[idx];
		s.push(cur);
		//���� ���ڿ��� ������ ���ڷ� ������ ���ÿ� ���� ���ڿ� ũ�� �̻� ���� ���, ���� ���ڿ����� Ž��
		if (cur == boom[lenBoom - 1] && s.size() >= lenBoom) {
			stack<char> temp;
			bool check = true;
			//���߹��ڿ� ũ�⸸ŭ ���ÿ��� ���� Ȯ��
			for (int j = lenBoom - 1; j >= 0; j--) {
				temp.push(s.top());
				s.pop();
				if (temp.top() != boom[j]) {
					check = false;
				}
			}
			//���� ���ڿ��� �ƴѰ��, �ٽ� ���ÿ� �ֱ�
			if (!check)
				while (!temp.empty()) {
					s.push(temp.top());
					temp.pop();
				}
		}
	}
	if (s.empty())
		cout << "FRULA";
	else {
		string res;
		while (!s.empty()) {
			res += s.top();
			s.pop();
		}
		reverse(res.begin(), res.end());
		cout << res;
	}
}