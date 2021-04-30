import java.util.HashMap;

public class _210430_다단계칫솔판매 {
	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];
		HashMap<String, String> sellerMap = new HashMap<>();
		HashMap<String, Integer> priceMap = new HashMap<>();
		for (int index = 0; index < enroll.length; index++) {
			sellerMap.put(enroll[index], referral[index]);
		}
		for (int index = 0; index < seller.length; index++) {
			int price = amount[index] * 100;
			String person = seller[index];
			while (!sellerMap.get(person).equals("-")) {
				String nextPerson = sellerMap.get(person);
				priceMap.put(person, priceMap.getOrDefault(person, 0) + price - price / 10);
				price /= 10;
				person = nextPerson;
			}
			priceMap.put(person, priceMap.getOrDefault(person, 0) + price - price / 10);
		}
		for (int index = 0; index < enroll.length; index++)
			if (!priceMap.containsKey(enroll[index]))
				answer[index] = 0;
			else
				answer[index] = priceMap.get(enroll[index]);
		return answer;
	}

	public static void main(String[] args) {
		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };
		solution(enroll, referral, seller, amount);
	}
}
