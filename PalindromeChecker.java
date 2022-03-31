package dnaAnalyser;

public class PalindromeChecker implements Checker{
	
//	Class is used to check if a sequence of chars(passed to process method) is a palindrome of a certain length
	//E.X. CGAGC
	
	
	private final int seqLength;			// seqLength (final int) - holds the sought after length
	private final int position;				// position (final int) - holds the start position in the DNA sequence (for tag reference)
	private boolean fail;					// fail (boolean) - holds whether the processed sequence so far could not be palindrome
	private String patternSoFar;			// patternSoFar (String) - holds the accumulated chars so far given to this checker
	private StackADT<Character> previous;	// previous (StackADT<Character>) - holds the initial chars that must later be checked against the later chars to determine if the sequence is a palindrome

	
	public PalindromeChecker (int seqLength) { 	// Initializes all instance variables
		this.seqLength = seqLength; 			// seqLength as specified
		position = 0;							// position as 0
		fail = false;							// fail as false
		patternSoFar = "";						// patternSofar as “”
		previous = new ArrayStack<Character>(); // previous as an empty stack
	}

	
	private PalindromeChecker (int seqLength, int position) { // Same but position is specified. Used in cloneHere.
		this.seqLength = seqLength;
		this.position = position;
		fail = false;
		patternSoFar = "";
		previous = new ArrayStack<Character>();
	}
	
	
//		o If the palindrome has an even length
//			▪ For the first half of the characters, add them to the stack.
//			▪ For the second half of the characters, see if they match the expected
//	  	 	 characters on the stack, if not, set the instance variable, fail, to true.
//		o If the palindrome has an odd length do the same as the even except skip over
//   	  the middle character (in terms of the stack)
//		o Return true if current length of the patternSoFar is the seqLength and fail is false
//	 	  (meaning: it found a palindrome of the sought after length), otherwise return false.
	
	public boolean process (char c) { // this method is used multiple times
		
		/*
		 * Process
		 * 	> One step at a time
		 * 	> Appending c to patternSoFar each time
		 * 	> Every time we append, we check to see if we have a palindrome
		 * 	> We are only checking for palindromes of length [seqLength]
		 * 	> Only check once we have patternSoFar.length() == this.seqLength
		 * 		
		 * 	> process every time even before we reach seqLength
		 * 	> 
		 */

		//--------------------------------
		// STEP 1 - add to patternSoFar
		//--------------------------------
		patternSoFar += c; // keep a record of what we got so far, more for the toString, not process

		
		//--------------------------------
		// STEP 2 - don't check when less than half
		//--------------------------------
		
		if (patternSoFar.length() <= seqLength/2) {	// ints round down when divided, so the mid val is not considered (e.g. 7/2 = 3)
			previous.push(c);
		}
		
		//--------------------------------
		// STEP 3 - ignore mid val when odd
		//--------------------------------
		

		else if ((seqLength % 2 != 0) && (patternSoFar.length() == seqLength/2 + 1)) {}
		// This case will prevent the program from checking the mid val when odd
			// If we are odd and we are at the middle value...
			// DO NOTHING
		
		
		//--------------------------------
		// STEP 4 - compare values
		//--------------------------------
		
		else if (!previous.isEmpty()) {	// if it is empty, then we've successfully checked the first half
			if (patternSoFar.length() > seqLength/2) {	// now we check for failure
				// check if the top stack is equal to the current c
				if (previous.peek() != c) {		// if not equal then its not a palindrome
					fail = true;
				}
				else {							// if they are pop it off, so compare the next element (character)
					previous.pop();
				}
			}	
		}
		
		//--------------------------------
		// STEP 5 - is Empty then passed
		//--------------------------------

		else {
			return true;
		}
		
		return ((patternSoFar.length()==seqLength) && (fail == false));
	}

	public boolean finished () {
		return fail;
		
	}
	
	public Checker cloneHere (int pos) {
		PalindromeChecker pChecker = new PalindromeChecker(this.seqLength, pos);
		/*
		 * starts at pos in DnaAnalyser
		 * 
		 */
		return pChecker;
	}
	
	@Override
	public String toString() {
//		o e.g. “Palindrome(10) - 39{CAAGAAGAAC}”
		return "Palindrome(" + this.seqLength + ") - " + this.position + "{" + this.patternSoFar + "}";

	}

}
