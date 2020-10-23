package queues;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		
		Queue <Integer> q1 = new LinkedList<>();
		
		q1.add(123);
		q1.add(123);
		q1.add(121);
		q1.add(null);
		q1.add(null);
		q1.add(44);
		q1.add(12);
		q1.add(123);
		q1.add(123);
		q1.add(null);
		
		System.out.println(q1);
		
		
		System.out.println(q1);
		
	}
}
