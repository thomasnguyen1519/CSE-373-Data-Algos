// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 3/8/17
// Homework #6


package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort packets received from a
 * server containing image metadata.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class PacketSorter {
    /**
     * Sorts the given array of packets in ascending order according to the
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
     *            the packets to sort
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void mergeSort(Packet[] array, Comparator<Packet> comparator) {
		Packet[] storage = new Packet[array.length];
		mergeSort(array, storage, comparator, 0,  array.length - 1);
	}

    /*
     * Recursively sorts the Packet[] array param in ascending order.
     * Accepts params of types Packet[], Packet[], Comparator<Packet>, int, 
     * int.
     */
	private static void mergeSort(Packet[] array, Packet[] storage, 
								  Comparator<Packet> comparer, int leftBound, 
								  int endOfPartition) {
		if (leftBound < endOfPartition) {
			int splitPoint = (leftBound + endOfPartition) / 2;
			mergeSort(array, storage, comparer, leftBound, splitPoint);
			mergeSort(array, storage, comparer, splitPoint + 1, endOfPartition);
			merge(array, storage, comparer, leftBound, splitPoint + 1, endOfPartition);
		}
	}
	
	/* 
	 * Pseudo-merges two "halves" of the Packet[] array param together in ascending 
	 * sorted order. Accepts params of types Packet[], Packet[], Comparator<Packet>,
	 * int, int, int.
	 */
	private static void merge(Packet[] array, Packet[] storage, 
							  Comparator<Packet> comparer, int leftPointer,
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
     * Sort the array of packets in ascending order using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void selectionSort(Packet[] array,
            Comparator<Packet> comparator) {
    	for (int i = 0; i < array.length; i++) {
        	Packet swap = array[i];
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
     * Sort the array of packets in ascending order using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void insertionSort(Packet[] array,
            Comparator<Packet> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Packet currentPacket = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentPacket, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentPacket;
        }
    }
}
