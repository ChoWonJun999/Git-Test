import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
public class MenuRenewal {
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2, 3, 4};
		System.out.println(Arrays.toString(solution(orders, course)));
		
		String[] orders1 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course1 = {2, 3, 5};
		System.out.println(Arrays.toString(solution(orders1, course1)));
		
		String[] orders2 = {"XYZ", "XWY", "WXA"};
		int[] course2 = {2, 3, 4};
		System.out.println(Arrays.toString(solution(orders2, course2)));
	}
	
	static HashMap<String,Integer> map;
	static int m;
	
	static public String[] solution(String[] orders, int[] course) {
		PriorityQueue<String> pq = new PriorityQueue<>();
		for(int i=0; i<course.length; i++){
			map = new HashMap<>();
			m = 0;
			for(int j=0; j<orders.length; j++) {
				find(0, "", course[i], 0, orders[j]);
			}
			for(String s : map.keySet()){
				if(map.get(s)==m && m>1) pq.offer(s);
			}
		}
		String ans[] = new String[pq.size()];
		int k = 0;
		while(!pq.isEmpty()) ans[k++] = pq.poll();
		return ans;
	}
	
	static void find(int cnt, String str, int targetNum, int idx, String word){
		if(cnt==targetNum){
			char[] c = str.toCharArray();
			Arrays.sort(c);
			String temps="";
			for(int i=0; i<c.length; i++) temps += c[i];
			map.put(temps, map.getOrDefault(temps, 0)+1);
			m = Math.max(m, map.get(temps));
			return;
		}
		for(int i=idx; i<word.length(); i++){
			char now = word.charAt(i);
			find(cnt+1, str+now, targetNum, i+1, word);
		}
	}
	
	/*
	static public String[] solution(String[] orders, int[] course) {
		List<String> list = new ArrayList<>();
		for(int i=0; i<course.length; i++){
			List<String> tempList = courseFunction(course[i], orders);
			for(String str : tempList){
				list.add(str);
			}
		}
		Collections.sort(list);
		return list.toArray(new String[list.size()]);
	}
	
	static List<String> courseFunction(int number, String[] orders){
		HashMap<String, Integer> ha = new HashMap<>();
		for(int i=0; i<orders.length; i++){
			int count = 0;
			dfs(number, orders[i], count, "", 0, ha);
		}
		
		List<String> list = new ArrayList<>();
		int max = 0;
		Iterator<String> it = ha.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(ha.get(key) > 1){
				if(max < ha.get(key)){
					max = ha.get(key);
					list.clear();
					list.add(key);
				}else if(max == ha.get(key)){
					list.add(key);
				}
			}
			
		}
		return list;
	}
	
	static void dfs(int number, String order, int count, String getStr, int getInt, HashMap<String, Integer> ha){
		count++;
		if(count == number){
			for(int i=getInt; i<order.length(); i++){
				String temp = getStr + String.valueOf(order.charAt(i));
				char[] StringtoChar = temp.toCharArray();
				Arrays.sort(StringtoChar);
				String SortedString = new String(StringtoChar);
				if(ha.containsKey(SortedString)) ha.replace(SortedString, ha.get(SortedString)+1);
				else ha.put(SortedString, 1);
			}
			return;
		}else{
			for(int i=getInt; i<order.length(); i++){
				String temp = getStr + String.valueOf(order.charAt(i));
				char[] StringtoChar = temp.toCharArray();
				Arrays.sort(StringtoChar);
				String SortedString = new String(StringtoChar);
				dfs(number, order, count, SortedString, i+1, ha);
			}
		}
	}
	*/
}