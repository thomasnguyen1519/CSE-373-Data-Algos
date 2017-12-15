// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 1/31/17
// Homework #3

/* 
 * MyClient is an interactive program that will switch specified words within an
 * input sentence with its antonym (if available). The antonym database is very
 * limited at this point in MyClient's development. The capabilities mostly only
 * span for some adjectives and a few nouns.
 * 
 */


import java.util.*;



public class MyClient {

	public static void main(String[] args) {
		TextAssociator antonymBank = new TextAssociator();
		
		Scanner scan = new Scanner(System.in);
		antonymBank.addNewWord("clean");
		antonymBank.addAssociation("clean", "dirty");
		antonymBank.addNewWord("happy");
		antonymBank.addAssociation("happy", "sad");
		antonymBank.addNewWord("overjoyed");
		antonymBank.addAssociation("overjoyed", "livid");
		antonymBank.addNewWord("tired");
		antonymBank.addAssociation("tired", "energetic");
		antonymBank.addNewWord("blue");
		antonymBank.addAssociation("blue", "orange");
		antonymBank.addNewWord("black");
		antonymBank.addAssociation("black", "white");
		antonymBank.addNewWord("hot");
		antonymBank.addAssociation("hot", "cold");
		antonymBank.addNewWord("introvert");
		antonymBank.addAssociation("introvert", "extrovert");
		antonymBank.addNewWord("love");
		antonymBank.addAssociation("love", "hate");
		antonymBank.addNewWord("cat");
		antonymBank.addAssociation("cat", "dog");
		antonymBank.addNewWord("excited");
		antonymBank.addAssociation("excited", "indifferent");
		antonymBank.addNewWord("good");
		antonymBank.addAssociation("good", "bad");
		antonymBank.addNewWord("starving");
		antonymBank.addAssociation("starving", "full");
		antonymBank.addNewWord("rich");
		antonymBank.addAssociation("rich", "poor");
		antonymBank.addNewWord("young");
		antonymBank.addAssociation("young", "old");
		antonymBank.addNewWord("fun");
		antonymBank.addAssociation("fun", "boring");
		antonymBank.addNewWord("focussed");
		antonymBank.addAssociation("focussed", "distracted");
		antonymBank.addNewWord("long");
		antonymBank.addAssociation("long", "short");
		antonymBank.addNewWord("late");
		antonymBank.addAssociation("late", "early");
		antonymBank.addNewWord("adult");
		antonymBank.addAssociation("adult", "child");
		antonymBank.addNewWord("boy");
		antonymBank.addAssociation("boy", "girl");
		antonymBank.addNewWord("true");
		antonymBank.addAssociation("true", "false");
		antonymBank.addNewWord("genuine");
		antonymBank.addAssociation("genuine", "counterfeit");
		antonymBank.addNewWord("fake");
		antonymBank.addAssociation("fake", "real");
		antonymBank.addNewWord("small");
		antonymBank.addAssociation("small", "large");
		antonymBank.addNewWord("husky");
		antonymBank.addAssociation("husky", "cougar");
		antonymBank.addNewWord("sea");
		antonymBank.addAssociation("sea", "land");
		System.out.println("Hello, my friend. I am an antonym replacer.");
		System.out.println("My purpose is to completely change the intention of");
		System.out.println("your sentence, hopefully for comedic purposes.");
		System.out.println("However, I am not very functional right now but I am trying");
		System.out.println("to learn new vocabulary :).");
		System.out.println("Enter a sentence and I will show your true intentions.");
		System.out.println();
		String inputString = "";
		Random rand = new Random();
		while (true) {
			System.out.print("Please input the text you would like to be \"enhanced\"? (enter \"exit\" to exit):");
			inputString = scan.nextLine();
			if (inputString.equals("exit")) {
				break;
			}
			String[] tokens  = inputString.split(" ");
			String result = "";
			for (String token : tokens) {
				Set<String> words = antonymBank.getAssociations(token.toLowerCase());
				if (words == null) {
					result += " " + token;
				} else {
					result += " " + words.toArray()[rand.nextInt(words.size())];
				}
			}
			System.out.println(result.trim());
			System.out.println();	
		}
		System.out.println();	
		System.out.println("Goodbye now. That's enough human interaction for one day.");
		System.out.println("Time to go back to sleep.");	
	}


}
