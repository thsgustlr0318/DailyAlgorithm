#include <iostream>
#include <cstring>
#include <string>
#include <vector>
using namespace std;

struct Trie {
	//단어 끝인지 확인
	bool finish;
	Trie* Next[10];
	Trie() {
		finish = false;
		memset(Next, 0, sizeof(Next));
	};
	void insert(string str, int idx) {
		//문자열 끝나는 지점 표시
		if (idx == str.size()) {
			finish = true;
			return;
		}
		int cur = str[idx] - '0';
		//처음 탐색일 경우, 할당
		if (Next[cur] == NULL)
			Next[cur] = new Trie();
		//다음 문자 삽입
		Next[cur]->insert(str, idx + 1);
	}

	bool find(string str, int idx) {
		//문자열을 끝까지 탐색 안했는데 단어 끝일 경우(다른 번호의 접두어가 있는 경우)
		if (finish && idx < str.size())
			return false;
		//다른 번호의 접두어가 없는 경우
		if (idx == str.size())
			return true;
		int cur = str[idx] - '0';
		if (Next[cur] == NULL) {
			return false;
		}
		return Next[cur]->find(str, idx + 1);
	}
};
int main()
{
	int tc, n;
	cin >> tc;
	while (tc--) {
		cin >> n;
		vector<string> phoneNumbers(n);
		Trie *root = new Trie();
		for (int i = 0; i < n; i++) {
			cin >> phoneNumbers[i];
			root->insert(phoneNumbers[i], 0);
		}
		bool check = true;
		for (int i = 0; i < n; i++) {
			check = root->find(phoneNumbers[i], 0);
			if (!check)
				break;
		}
		cout << (check ? "YES" : "NO") << "\n";
	}
}