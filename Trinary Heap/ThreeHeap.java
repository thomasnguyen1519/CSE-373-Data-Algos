// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 1/20/17
// Homework #2

/* 
 * ThreeHeap is a data structure that stores doubles in a ternary heap
 * that dynamically resizes to necessitate storage needs. Duplicates of
 * data are allowed in ThreeHeap.
 * Implements the PriorityQueue interface.
 */


import java.util.List;
import java.util.Arrays;

public class ThreeHeap implements PriorityQueue {

	private double[] heap;
	private int size;
	
	// Initializes an empty heap.
	public ThreeHeap() {
		this.heap = new double[10];
	}
	
	// Returns true if the heap is empty, otherwise false.
	public boolean isEmpty() {
		return this.size == 0;
	}

	// Returns the number of items in the heap.
	public int size() {
		return this.size;
	}

	/* 
	 * Returns the minimum double that is stored in the heap.
	 * Throws EmptyHeapException error, if called on an empty heap.
	 */
	public double findMin() {
		if (this.isEmpty()) {
			throw new EmptyHeapException();
		}
		return this.heap[1];
	}
	
	// Inserts the double param to the heap and resizes the storage, if necessary.
	public void insert(double x) {
		heap[percolateUp(++size, x)] = x;
		if (size + 1 == heap.length) {
			heap = Arrays.copyOf(heap, heap.length * 2);
		}
	}

	/* 
	 * Removes and returns the minimum double from the heap, while maintaining
	 * the heap structure.
	 * Throws an EmptyHeapException error, if called on an empty heap.
	 */
	public double deleteMin() {
		double value = this.findMin();
		int hole = percolateDown(1, heap[size]);
		heap[hole] = heap[size];
		size--;
		return value;
	}
	
	/*
	 *  Pseudo-resets the heap so that it appears to be empty and overwrites
	 *  it with a new heap of the values from the List param.
	 */
	public void buildQueue(List<Double> list) {
		makeEmpty();
		for (double data : list) {
			heap[++size] = data;
		}
		fixHeapOrder();
	}
	
	// Pseudo-resets the heap so that it appears to be empty.
	public void makeEmpty() {
		this.size = 0;
	}
	
	// Returns a string representation of the heap.
	public String toString() {
		String output = "[-";
		if (this.isEmpty()) {
			return output + "]";
		}
		for (int i = 1; i <= size; i++) {
			output += ", " + heap[i];
		}
		return output + "]";
	}
	
	/* 
	 * Returns the index of where the double param should be placed within the
	 * heap using an upward traversal starting from the index of the int param.
	 */
	private int percolateUp(int position, double value) {
		while (position > 1 && value < heap[(position + 1) / 3]) {
			heap[position] = heap[(position + 1) / 3];
			position = (position + 1) / 3;
		}
		return position;
	}
	
	/* 
	 * Returns the index of where the double param should be placed within
	 * the heap using a downward traversal starting from the index of the
	 * int param.
	 */
	private int percolateDown(int position, double value) {
		while (3 * position - 1 <= size) {
			int middle = position * 3;
			int right = middle + 1;
			int left = middle - 1;
			int target = 1;
			if (middle > size || (heap[left] <= heap[right]
				 && heap[left] <= heap[middle])) { 
				target = left;
			} else if (heap[middle] <= heap[right] || right > size) {
				target = middle;
			} else {
				target = right;
			}
			if (heap[target] < value) {
				heap[position] = heap[target];
				position = target;
			} else {
				break;
			}
		}
		return position;
	}
	
	/*
	 *  Adjusts the heap's structure with Floyd's method to maintain the heap
	 *  order property, if necessary. 
	 */
	private void fixHeapOrder() {
		for (int i = (size + 1) / 3; i > 0; i--) {
			double parentValue = heap[i];
			heap[percolateDown(i,parentValue)] = parentValue;
		}
	}
}
