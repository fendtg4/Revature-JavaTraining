package listtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListDemo {

	public static void main(String[] args) {
		
		List<Integer> li1 = new LinkedList<>();
		
		li1.add(3);
		li1.add(5);
		li1.add(4);
		li1.add(100);
		li1.add(333);
		li1.add(50);
		
		/*System.out.println(li1);
		
		for (Integer i:li1) {
			System.out.println(i);
		}*/
		
	
		
		Collections.reverse(li1);
		System.out.println(li1);
		
		Collections.shuffle(li1);
		System.out.println(li1);
		
		List<Integer> li2 = new Vector<>();
		
		li2.add(3);
		li2.add(4);
		li2.add(6);
		li2.add(50);
		li2.add(200);
		
		List<Integer> li3 = new ArrayList<>();
		li3.addAll(li1);
		li1.removeAll(li2);
		li2.removeAll(li3);
		
		System.out.println(li1);
		System.out.println(li2);
		
		System.out.println("Collectons.binarySearch(list1, 333) : " + Collections.binarySearch(li1, 333));
		
	}
}
