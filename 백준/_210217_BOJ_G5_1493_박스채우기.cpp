#include <iostream>
using namespace std;

int arr[20];
bool flag = true;
int res = 0;

void recursive(int l, int w, int h) {
	if (!flag)
		return;
	if (l == 0 || w == 0 || h == 0)
		return;
	for (int i = 19; i >= 0; i--) {
		int len = 1 << i;
		if (arr[i] > 0 && l >= len && w >= len && h >= len) {
			arr[i]--;
			res++;
			recursive(l, w, h - len);
			recursive(len, w - len, len);
			recursive(l - len, w, len);
			return;
		}
	}
	flag = false;
}

int main() {
	int length, width, height, n;
	cin >> length >> width >> height >> n;

	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		arr[a] = b;
	}
	recursive(length, width, height);
	if (flag)
		cout << res;
	else
		cout << "-1";
}