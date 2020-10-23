package maptest;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapDemo {

	public static void main(String[] args) {
		
		
		Map<Integer, String> hm1 = new HashMap<>();
		
		
		hm1.put(123, "hello world");
		hm1.put(456, "hey what's up");
		hm1.put(789, "java");
		hm1.put(1000, "dog");
		hm1.put(1001, "cat");
		hm1.put(1003, null);
		System.out.println("Interating using keySet()");
		
		Set<Integer> hs = hm1.keySet();
		
		for (Integer i:hs) {
			System.out.println("Key: " + i + " Value: " + hm1.get(i));
		}
		
		System.out.println("Iterating using entrySet()");
		
		for (Entry <Integer, String> e:hm1.entrySet()) {
			System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
		}
		
		System.out.println("Iterating with iterator while removing even keys and null values");
		Iterator<Entry<Integer,String>> i = hm1.entrySet().iterator();	
		while (i.hasNext()) {
			Entry<Integer, String> e=i.next(); //read&increment
			if (e.getKey()%2==0 || e.getValue()==null) {
				i.remove();
			}
		}
	
		System.out.println(hm1);
		}
	

}
