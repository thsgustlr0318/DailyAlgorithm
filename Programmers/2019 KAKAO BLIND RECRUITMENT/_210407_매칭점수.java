import java.util.HashMap;

public class _210407_매칭점수 {
	public static int solution(String word, String[] pages) {
		int answer = 0;
		int pageSize = pages.length;
		word = word.toLowerCase();
		HashMap<String, Double> value = new HashMap<>();
		HashMap<String, Double> linkValue = new HashMap<>();
		HashMap<String, Integer> pageIndex = new HashMap<>();
		for (int i = 0; i < pageSize; i++) {
			String curPage = pages[i];
			String pageUrl = curPage.substring(curPage.indexOf("<head>"), curPage.indexOf("</head>"));
			String pattern = "<meta property=\"og:url\" content=\"https://";
			pageUrl = pageUrl.substring(pageUrl.indexOf(pattern) + pattern.length());
			pageUrl = pageUrl.substring(0, pageUrl.indexOf("\""));

			pageIndex.put(pageUrl, i);
			value.put(pageUrl, value.getOrDefault(pageUrl, (double) 0));

			String body = curPage.substring(curPage.indexOf("<body>"), curPage.indexOf("</body>"));
			String body2 = new String(body);

			body = body.toLowerCase();
			String[] Splits = body.split(word);
			for (int idx = 1; idx < Splits.length; idx++) {
				if (Splits[idx - 1].length() == 0 || (Splits[idx - 1].charAt(Splits[idx - 1].length() - 1) <= 'a' && 'z' <= Splits[idx - 1].charAt(Splits[idx - 1].length() - 1)))
					continue;
				if (Splits[idx].length() == 0 || (Splits[idx].charAt(0) <= 'a' && 'z' <= Splits[idx].charAt(0)))
					continue;
				value.put(pageUrl, value.getOrDefault(pageUrl, (double) 0) + 1);
			}

			String[] linkUrl = body2.split("<a href=\"https://");
			for (int j = 1; j < linkUrl.length; j++) {
				String url = linkUrl[j].substring(0, linkUrl[j].indexOf("\""));
				linkValue.put(url, linkValue.getOrDefault(url, (double) 0) + (double) value.get(pageUrl) / (linkUrl.length - 1));
			}
		}
		for (String key : linkValue.keySet()) {
			if (!value.containsKey(key))
				continue;
			value.put(key, value.getOrDefault(key, (double) 0) + linkValue.get(key));
		}
		String maxPage = "";
		double maxValue = -1;
		for (String key : value.keySet()) {
			if (value.get(key) > maxValue) {
				maxValue = value.get(key);
				maxPage = key;
			}
		}
		return answer = pageIndex.get(maxPage);
	}

	public static void main(String[] args) {
		String word = "blind";
		String[] pages = {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" };
		solution(word, pages);
	}
}
