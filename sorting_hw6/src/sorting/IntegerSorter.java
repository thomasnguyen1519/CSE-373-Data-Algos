// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 3/8/17
// Homework #6

package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort Integers.
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class IntegerSorter {
    /**
     * Sorts the given array of integers in ascending order according to the
     * comparator using mergesort. You may create as many private helper
     * functions as you wish to implement this method.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the integers to sort
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void mergeSort(Integer[] array, Comparator<Integer> comparator) {
		Integer[] storage = new Integer[array.length];
		mergeSort(array, storage, comparator, 0,  array.length - 1);
	}

    /*
     * Recursively sorts the Integer[] array param in ascending order.
     * Accepts params of types Integer[], Integer[], Comparator<Integer>, int, 
     * int.
     */
	private static void mergeSort(Integer[] array, Integer[] storage, 
								  Comparator<Integer> comparer, int leftBound, 
								  int endOfPartition) {
		if (leftBound < endOfPartition) {
			int splitPoint = (leftBound + endOfPartition) / 2;
			mergeSort(array, storage, comparer, leftBound, splitPoint);
			mergeSort(array, storage, comparer, splitPoint + 1, endOfPartition);
			merge(array, storage, comparer, leftBound, splitPoint + 1, endOfPartition);
		}
	}
	
	/* 
	 * Pseudo-merges two "halves" of the Integer[] array param together in ascending 
	 * sorted order. Accepts params of types Integer[], Integer[], Comparator<Integer>,
	 * int, int, int.
	 */
	private static void merge(Integer[] array, Integer[] storage, 
							  Comparator<Integer> comparer, int leftPointer,
							  int rightPointer, int endOfPartition) {
		int pointer = leftPointer;
        int rightBeginning = endOfPartition - leftPointer + 1;
        int endOfLeft = rightPointer - 1;
        while (leftPointer <= endOfLeft && rightPointer <= endOfPartition) {
            if (comparer.compare(array[leftPointer], array[rightPointer]) > 0) {
            	storage[pointer] = array[rightPointer];
                rightPointer++;
            } else {
            	storage[pointer] = array[leftPointer];
                leftPointer++;
            }
            pointer++;
        }
        for (int i = leftPointer; i <= endOfLeft; i++) {
            storage[pointer] = array[i];
            pointer++;
        }
        for (int i = rightPointer; i <= endOfPartition; i++) {
            storage[pointer] = array[i];
            pointer++;
        }
        for(int i = 0; i < rightBeginning; i++) {
            array[endOfPartition] = storage[endOfPartition];
            endOfPartition--;
        }
    }
    

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integer that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void selectionSort(Integer[] array,
            Comparator<Integer> comparator) {
        for (int i = 0; i < array.length; i++) {
        	Integer swap = array[i];
        	int minIndex = i;
	        for (int j = array.length - 1; j > i; j--) { 
        		if (comparator.compare(array[j], array[minIndex]) <= 0) {
        			minIndex = j;
        		}
        	}
        	array[i] = array[minIndex];
        	array[minIndex] = swap;
        }
    }
    

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integers that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integer.
     */
    public static void insertionSort(Integer[] array,
            Comparator<Integer> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Integer currentInt = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentInt, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentInt;
        }
    }
}
