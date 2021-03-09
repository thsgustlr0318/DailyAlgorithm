import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _210309_BOJ_S1_19583_싸이버개강총회 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start, mid, end, res = 0;
		String str = st.nextToken();
		start = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
		str = st.nextToken();
		mid = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
		str = st.nextToken();
		end = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
		
		HashSet<String> students = new HashSet<>();
	
		while ((str=br.readLine()) != null) {
			int time = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
			String name = str.substring(6, str.length());
			if(time <= start) {
				students.add(name);
			}else if(mid <= time && time <= end) {
				if(students.contains(name)) {
					res++;
					students.remove(name);
				}
			}
		}
		System.out.println(res);
	}
}
