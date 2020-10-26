package maptest;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
		
		System.out.println("Iterating with keySet()");
		
		Set<Integer> hs = hm1.keySet();
		
		for (Integer i:hs) {
			System.out.println("Key: " + i + " Value: " + hm1.get(i));
		}
		
		System.out.println("Iterating with entrySet()");
		
		for (Entry<Integer,String> e:hm1.entrySet()) {
			System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
		}
		
		System.out.println("Iterating with iterator while removing even numbered keys and null values");
		
		Iterator<Entry<Integer,String>> i = hm1.entrySet().iterator();
		
		while (i.hasNext()) {
			Entry<Integer,String> e = i.next();
			if (e.getKey()%2==0 || e.getValue()==null) {
				i.remove();
			}
		}
		
		System.out.println(hm1);

		Map<Integer,String> ht1= new Hashtable<>();
		
		ht1.put(123, "first 3 numbers");
		ht1.put(456, "forest");
		ht1.put(789, "tree");
		ht1.put(10000, "desert");
		ht1.put(10, "mountain");
		ht1.put(55, "lake");
		ht1.put(556, "ocean");
		
		
		System.out.println("Iterating over a hashtable with keySet()");
		Set <Integer> hs2 = ht1.keySet();
		
		for (Integer j:hs2) {
			System.out.println("Key : " + j + " Value: " + ht1.get(j));
		}
		
		System.out.println("Iterating over a hashtable with entrySet()");
		for (Entry<Integer,String> e:ht1.entrySet()) {
			System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
		}
		
		System.out.println("Iterating over a hashtable with an iterator");
		
		Iterator<Entry<Integer,String>> i2 = ht1.entrySet().iterator();
		
		while (i2.hasNext()) {
			Entry<Integer,String> e = i2.next();
			if (e.getKey()%5==0) {
				i2.remove();
			}
		}
		
		System.out.println(ht1);
		
		Map<Integer,String> lhm1 = new LinkedHashMap<>();
		
		lhm1.put(55,"test1");
		lhm1.put(66,"test2");
		lhm1.put(77,"test3");
		
		System.out.println("printing linked hashmap");
		System.out.println(lhm1);
		}
	
		
		
		
}
