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
	//첫 글자부터 탐색
	for (int idx = 0; idx < lenStr; idx++) {
		char cur = str[idx];
		s.push(cur);
		//폭발 문자열의 마지막 글자로 끝나고 스택에 폭발 문자열 크기 이상 쌓인 경우, 폭발 문자열인지 탐색
		if (cur == boom[lenBoom - 1] && s.size() >= lenBoom) {
			stack<char> temp;
			bool check = true;
			//폭발문자열 크기만큼 스택에서 빼서 확인
			for (int j = lenBoom - 1; j >= 0; j--) {
				temp.push(s.top());
				s.pop();
				if (temp.top() != boom[j]) {
					check = false;
				}
			}
			//폭발 문자열이 아닌경우, 다시 스택에 넣기
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