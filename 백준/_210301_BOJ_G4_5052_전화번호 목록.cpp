#include <iostream>
#include <cstring>
#include <string>
#include <vector>
using namespace std;

struct Trie {
	//�ܾ� ������ Ȯ��
	bool finish;
	Trie* Next[10];
	Trie() {
		finish = false;
		memset(Next, 0, sizeof(Next));
	};
	void insert(string str, int idx) {
		//���ڿ� ������ ���� ǥ��
		if (idx == str.size()) {
			finish = true;
			return;
		}
		int cur = str[idx] - '0';
		//ó�� Ž���� ���, �Ҵ�
		if (Next[cur] == NULL)
			Next[cur] = new Trie();
		//���� ���� ����
		Next[cur]->insert(str, idx + 1);
	}

	bool find(string str, int idx) {
		//���ڿ��� ������ Ž�� ���ߴµ� �ܾ� ���� ���(�ٸ� ��ȣ�� ���ξ �ִ� ���)
		if (finish && idx < str.size())
			return false;
		//�ٸ� ��ȣ�� ���ξ ���� ���
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