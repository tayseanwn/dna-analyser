package dnaAnalyser;

import java.util.Iterator; // java.util.Iterator at the top of this file.

public class DnaAnalyser {
		
	private LinkedQueue<Checker> checkers;	// holds all the checkers for this Analyzer
	private LinkedQueue<String> results;	// holds all the results for only the recently searched sequence
	
	/*
	 *  Search Algorithm
	 *  	Initialize a Queue for active checkers.
	 *  	Clear previous DnaAnalysers’ results
	 *  	Loop through each character from the dnaSequence (keeping track of its position) 
	 *  		Concatenate the character into a full String of the sequence
	 *  		Add a new checker to the active checkers for each of the DnaAnalysers’ checkers using cloneHere(pos)
	 *  		have all active checkers process(c)
	 *  			If process(c) is successful add its toString() to the results
	 *  			Otherwise if is not finished() leave this checker as an active checker
	 */
	
	public DnaAnalyser (LinkedQueue<Checker> checkers) {	// Initializes all instance variables
		this.checkers = checkers;							// checkers as specified
		results = new LinkedQueue<String>();				// results as an empty LinkedQueue<String>
	}
	

	public String search(Iterator<Character> dnaSequence) {
		
		/*
		 * 1. Initialize the linked queue to keep the current checkers
		 * 2. Initialize the results (in line 23)
		 * 3. Use a while loop based on if dnaSequence hasNext()
		 * 		a. use char variable
		 * 		b. store current char of dnaSequence (char c = dnaSequence.next())
		 * 			use Iterator (Iterator iter = checkers.iterator())
		 * 4. Outside the while loop, use a string to create complete return string
		 * 		return a string of the dnaSequence
		 */
		
		//----------------
		//STEP 1
		//----------------
		
		LinkedQueue<Checker> current = new LinkedQueue<Checker>(); // create a linkedqueue to hold current checker
		//restart result
		results = new LinkedQueue<String>();
		String output = "";
		
		//----------------
		//STEP 2
		//----------------
		
		
		// 1. Go through the dnaSequence (string)
		// 2. Use every checker on each character in dnaSequence
		
		while (dnaSequence.hasNext()) {
			// take every character out of the dnaSequence
			char c = dnaSequence.next();
			// create an Iterator to store the element
			Iterator<Checker> dnaChecker = checkers.iterator();
			// add c to the output
			output += c;
			
			while (dnaChecker.hasNext()) {
				
				/*
				 * Initial checkers has the patterns/checkers
				 * The current implements the cloneHere method
				 */
				current.enqueue(dnaChecker.next().cloneHere(output.length()));
			}
			
			//----------------
			//STEP 3
			//----------------
			
			//user private helper method: testChecker
			
			testChecker(c, current);


		}
		
		return output;
	}
	
	private void testChecker (char c, LinkedQueue<Checker> current) {
		
		/*
		 * Going through every pattern and cloneHere method
		 * check if the process method is complete, returns true
		 */
		
		//since current.size() will change we will store it in a variable
		int size = current.size();
		
		for (int i = 0; i < size; i++) {
			
			// going to dequeue from the current
			// This is a cloneHere method from one of our Checkers (Repeat or Palindrome)
			Checker dequeuedChecker = current.dequeue();
			
			// see if the result is true with the current character from the dnaSequence
			if (dequeuedChecker.process(c) == true) {
				results.enqueue(dequeuedChecker.toString());
			}
			
			// if it doesn't finish it is false
			if (!dequeuedChecker.finished()) {
				current.enqueue(dequeuedChecker);
			}
		}
		
	}

	public String displayAnalysis(String dnaSequence) {
//		Prints out the results queue followed by a formatted dnaSequence string to visually confirm the results
		
		String output = "";
		
		Iterator<String> resultIterator = results.iterator();
		
		for (int i=0; i<results.size(); i++) {
			output += resultIterator.next();
		}
		
		return output + DNA.display(dnaSequence);
	}


}
