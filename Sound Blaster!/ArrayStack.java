// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 1/13/17
// Homework #1

/* 
 * ArrayStack is a data structure that stores doubles in LIFO order
 * in an array that dynamically resizes to necessitate storage needs.
 * Implementation of the DStack interface.
 */


import java.util.EmptyStackException;

public class ArrayStack implements DStack {
	
	public static final double RESIZE_THRESHOLD = 0.25;
	public static final int INITIAL_ARRAY_LENGTH = 2;

	private double[] stack;
	private int size;
	
	// Constructs an empty stack.
	public ArrayStack() {
		stack = new double[INITIAL_ARRAY_LENGTH];
	}
	
	// Returns true if the stack is empty, otherwise false.
	public boolean isEmpty() {
		return size == 0;
	}

	/* 
	 * Adds the double param to the stack in LIFO order.
	 * Resizes the stack storage when it gets full.
	 */
	public void push(double d) {
		stack[size] = d;
		size++;
		if (size == stack.length) {
			double[] newStack = new double[stack.length * 2];
			for (int i = 0; i < size; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
	}

	/* 
	 * Removes and returns the double value on top of the stack.
	 * Throws EmptyStackException error when trying to call when the stack
	 * is empty.
	 * Resizes the max storage on the stack when it is 3/4 empty.
	 */
	public double pop() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		if ((double) size / stack.length <= RESIZE_THRESHOLD) {
			double[] newStack = new double[stack.length / 2];
			for (int i = 0; i < size; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
		return stack[--size];
	}

	/* 
	 * Returns the double on the top of the stack but does not remove it. 
	 * Throws EmptyStackException error when trying to call when the stack 
	 * is empty.
	 */
	public double peek() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[size - 1];
	}

	// Returns a string representation of the stack.
	public String toString() { // for testing purposes
		String output = "[";
		for (int i = 0; i < size - 1; i++) {
			output += stack[i] + " ";
		}
		if (this.isEmpty()) {
			return output + "]";
		} else {
			return output + stack[size - 1] + "]";
		}
	}
	
	// Returns the number of items in the stack.
	public int size() {
		return size;
	}
}
