import java.util.EmptyStackException;

public class DoubleQueue {

	private class ListStackNode {
		
		private ListStackNode next;
		private double data;
		
		public ListStackNode(double data, ListStackNode other) {
			this.data = data;
			this.next = other;
		}
	}
	
	private ListStackNode front;
	private int size;
	
	// Initializes an empty stack.
	public DoubleQueue() {
		front = null;
	}
	
	// Returns true if the stack is empty, otherwise false.
	public boolean isEmpty() {
		return front == null;
	}

	// Adds the double param to the stack in LIFO order.
	public void add(double d) {
		ListStackNode scan = front;
		if (scan != null) {
			while (scan.next != null) {
				scan = scan.next;
			}
			scan.next = new ListStackNode(d,null);
		} else {
			front = new ListStackNode(d,null);
		}
		size++;
	}

	/* 
	 * Removes and returns the double value on front of the stack.
	 * Throws EmptyStackException error when trying to call on an empty stack.
	 */
	public double remove() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		double value = front.data;
		front = front.next;
		size--;
		return value;
	}

	/* 
	 * Returns the double on the front of the stack but does not remove it. 
	 * Throws EmptyStackException error when trying to call on an empty stack.
	 */
	public double peek() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		return front.data;
	}
	
	
	public String toString() { // testing purposes only
        String s = "]";
        ListStackNode scan = front;
        if (scan == null) {
        	return "[" + s;
        }
        while (scan.next != null) {
            s = " " + scan.data + s;
            scan = scan.next;
        }
        return "[" + scan.data + s;
    }
	
	// Returns the size of 
	public int size() { // possibly testing purposes only
		return size;
	}
}
