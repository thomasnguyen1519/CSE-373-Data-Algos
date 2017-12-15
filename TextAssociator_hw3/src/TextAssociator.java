// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 1/30/17
// Homework #3

/* 
 * 
 * TextAssociator represents a collection of associations between words. It dynamically
 * resizes its storage when it sees fit. 
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class TextAssociator {

	public static final double RESIZE_THRESHOLD = 1.00;
	public static final int[] PRIMES = {53, 97, 193, 389, 769, 1543, 3079, 6151, 
										12289, 24593, 49157, 98317, 196613, 393241,
										786433, 1572869, 3145739, 6291469, 12582917,
										25165843, 50331653, 100663319};
	
	private WordInfoSeparateChain[] table;
	private int size;
	private int resizeCounter;
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			if (!chain.contains(wi)) {
				chain.add(wi);
				return true;
			}
			return false;
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if (chain.contains(wi)) {
				chain.remove(wi);
				return true;
			}
			return false;
		}
		
		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	
	/* Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
		resizeCounter = 0;
		size = 0;
		table = new WordInfoSeparateChain[PRIMES[resizeCounter]];
	}
	
	
	/* Adds a word with no associations to the TextAssociator 
	 * Returns False if this word is already contained in your TextAssociator ,
	 * Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
		if ((double) size / table.length >= RESIZE_THRESHOLD) {
			resizeTable();
		}
		WordInfo newWord = new WordInfo(word);
		boolean result = addToTable(this.table, newWord);
		if (result) {
			size++;
		}
		return result;
	}
	
	
	/* Adds an association between the given words. Returns true if association correctly added, 
	 * returns false if first parameter does not already exist in the TextAssociator or if 
	 * the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		WordInfo newWord = new WordInfo(word);
		int hashTableIndex = newWord.hashCode() % table.length;
		if (table[hashTableIndex] != null) {
			List<WordInfo> chainOfWordInfos = table[hashTableIndex].getElements();
			for (WordInfo w : chainOfWordInfos) {
				if (w.equals(newWord)) {
					Set<String> currAssociations = w.getAssociations();
					if (!currAssociations.contains(association)) {
						w.addAssociation(association);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		WordInfo removal = new WordInfo(word);
		int hashTableIndex = removal.hashCode() % table.length;
		boolean result = false;
		if (table[hashTableIndex] != null) {
			WordInfoSeparateChain chainOfWordInfos = table[hashTableIndex];
			result = chainOfWordInfos.remove(removal);
			if (result) {
				size--;
			}
		}
		return result;
	}
	
	
	/* Returns a set of all the words associated with the given String  
	 * Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
		WordInfo finder = new WordInfo(word);
		int hashTableIndex = finder.hashCode() % table.length;
		if (table[hashTableIndex] != null)  {
			List<WordInfo> chainOfWordInfos = table[hashTableIndex].getElements();
			for (WordInfo w : chainOfWordInfos) {
				if (w.equals(finder)) {
					return w.getAssociations();
				}
			}
		}
		return null;
	}
	
	
	/* Prints the current associations between words being stored
	 * to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}
	
	// Resizes the hash table and rehashes the WordInfo's that were priorly contained
	private void resizeTable() {
		resizeCounter++;
		WordInfoSeparateChain[] newTable = new WordInfoSeparateChain[resizeCounter];
		for (int i = 0; i < table.length; i++) {
			WordInfoSeparateChain target = table[i];
			if (target != null) {
				for (WordInfo w : target.getElements()) {
					this.addToTable(newTable, w);
				}
			}
		}
		table = newTable;
	}
	
	/*
	 * Returns true if the WordInfo param was successfully added to the
	 * WordInfoSeparateChain[] param. If not, then returns false.
	 */
	private boolean addToTable(WordInfoSeparateChain[] currTable, WordInfo addition) {
		int hashTableIndex = addition.hashCode() % currTable.length;
		WordInfoSeparateChain chain = null;
		if (currTable[hashTableIndex] != null) {
			chain = currTable[hashTableIndex];
		} else {
			chain = new WordInfoSeparateChain();
			currTable[hashTableIndex] = chain;
		}
		return chain.add(addition);
	}
}
