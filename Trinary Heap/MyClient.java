// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 1/20/17
// Homework #2

// This is a test client class for ThreeHeap. 


import java.util.*;

public class MyClient {

	public static void main(String[] args) {
		PriorityQueue test = new ThreeHeap();
		for(int i = 1; i <= 10; i++) { // checks the resizing ability, size(), and insert()
			test.insert(i);
			System.out.println(test + " " + test.size());
		}
		System.out.println(test.findMin());
		test.insert(0.5);
		System.out.println(test);
		System.out.println(test.findMin());
		test.insert(0.7);
		System.out.println(test);
		test.insert(100.0);
		System.out.println(test);
		test.makeEmpty(); // checking the makeEmpty()
		System.out.println(test); // checks toString() on an empty heap
		// test.findMin(); // checks if it throws EmptyExceptionError
		test.insert(1.0);
		System.out.println(test);
		test.insert(2.0);
		System.out.println(test);
		test.insert(1.0);
		System.out.println(test);
		test.insert(3.0);
		System.out.println(test);
		List<Double> input = new ArrayList<Double>();
		for (int i = 0; i < 10; i++) {
			input.add((double) i);
		}
		System.out.println(input + " input");
		test.buildQueue(input); // testing the buildQueue() and Floyd's Method implementation
		System.out.println(test + " heap");
		input = new ArrayList<Double>();
		input.add(25.3);
		input.add(2.1);
		input.add(938.1);
		input.add(53.9);
		input.add(392.1);
		input.add(6.3);
		input.add(0.3);
		input.add(9.3);
		input.add(592.1);
		input.add(10.2);
		input.add(10.6);
		input.add(10.8);
		input.add(3.1); // checking duplicates
		input.add(3.1);
		System.out.println(input + " input");
		test.buildQueue(input);
		System.out.println(test + " heap");
		test.deleteMin();
		System.out.println(test + " heap");
		test.deleteMin();
		System.out.println(test + " heap");
		test.deleteMin();
		System.out.println(test + " heap");
		test.deleteMin();
		System.out.println(test + " heap");
	}
}
