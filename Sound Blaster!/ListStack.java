// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 1/13/17
// Homework #1

/* 
 * ListStack is a data structure that stores doubles in LIFO order
 * in a singly-linked list.
 * Implementation of the DStack interface.
 */


import java.util.EmptyStackException;


public class ListStack implements DStack {
	
	// Simple singly-linking node class that stores a double.
	private class ListStackNode {
		
		private ListStackNode next;
		private double data;
		
		// Initializes a ListStackNode with the double and ListStackNode params.
		public ListStackNode(double data, ListStackNode other) {
			this.data = data;
			this.next = other;
		}
	}
	
	private ListStackNode top;
	private int size;
	
	// Initializes an empty stack.
	public ListStack() {
		top = null;
	}
	
	// Returns true if the stack is empty, otherwise false.
	public boolean isEmpty() {
		return top == null;
	}

	// Adds the double param to the stack in LIFO order.
	public void push(double d) {
		top = new ListStackNode(d,top);
		size++;
	}

	/* 
	 * Removes and returns the double value on top of the stack.
	 * Throws EmptyStackException error when trying to call on an empty stack.
	 */
	public double pop() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		double value = top.data;
		top = top.next;
		size--;
		return value;
	}

	/* 
	 * Returns the double on the top of the stack but does not remove it. 
	 * Throws EmptyStackException error when trying to call on an empty stack.
	 */
	public double peek() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		return top.data;
	}
	
	// Returns a string representation of the stack.
	public String toString() {
        String output = "]";
        ListStackNode scan = top;
        if (scan == null) {
        	return "[" + output;
        }
        while (scan.next != null) {
            output = " " + scan.data + output;
            scan = scan.next;
        }
        return "[" + scan.data + output;
    }
	
	// Returns the number of items in the stack.
	public int size() {
		return size;
	}
}
