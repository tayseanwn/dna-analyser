package dnaAnalyser;

public class RepeatChecker implements Checker{
	
//	This class is used to check if a sequence of chars(passed to its process method) is an example of a sequence of a certain length repeated a certain number of times.
	//E.X. GAGAGAGA
	
	
	private final int seqLength;			// seqLength (final int) - holds the sought after length
	private final int repeatNumber;			// repeatNumber (final int) - holds the sought after length
	private final int position;				// position (final int) - holds the start position in the DNA sequence (for tag reference)
	private boolean fail;					// fail (boolean) - holds whether the processed sequence so far could not be this repeat type
	private String patternSoFar;			// patternSoFar (String) - holds the accumulated chars so far given to this checker
	private QueueADT<Character> previous;	// previous (QueueADT<Character>) - holds the initial chars that must later be checked against the later chars to determine if the sequence is repeated repeatNumber times
	
	
	public RepeatChecker(int seqLength, int repeatNumber) {	// Initializes all instance variables
		this.seqLength = seqLength;							// seqLength as specified
		this.repeatNumber = repeatNumber;					// repeatNumber as specified
		position = 0;										// position as 0
		fail = false;										// fail as false
		patternSoFar = "";									// patternSoFar as “”
		previous = new LinkedQueue<Character>();			// previous as an empty queue
	}
	
	public RepeatChecker(int seqLength, int repeatNumber, int position) { // Same but position is specified. Used in cloneHere.
		this.seqLength = seqLength;
		this.repeatNumber = repeatNumber;
		this.position = position;
		fail = false;
		patternSoFar = "";
		previous = new LinkedQueue<Character>();
	}
	

	public boolean process (char c) {
		
		//--------------------------------
		// STEP 1 - add to patternSoFar
		//--------------------------------
		
		patternSoFar += c;
		
		
		//--------------------------------
		// STEP 2 - add (seqLength) amount of chars to queue 
		//--------------------------------
		
//		For the first seqLength of the characters, add them to the queue.
		
		if (patternSoFar.length() <= seqLength) {
			previous.enqueue(c);
		}
		
		//--------------------------------
		// STEP 3 - start matching
		//--------------------------------
		
//		After that see if they match the expected characters in the queue, if not, set the
//		  instance variable, fail, to true. (hint: after a character is matched dequeue and then enqueue)
		
		else { 								// once finished adding to queue (STEP 2), then we begin matching
			if (previous.first() != c) {	// if one match is not made then it automatically fails
				fail = true;
			}
			else {							// once a match is found, dequeue from front, enqueue to end
				char match = previous.dequeue();
				previous.enqueue(match);
			}
		}
		
		
		//--------------------------------
		// STEP 4 - return value
		//--------------------------------
		
//		Return true if current length of the patternSoFar is the seqLength times repeatNumber and fail is false
//		  otherwise return false.

		return ((patternSoFar.length() == seqLength*repeatNumber) && !fail);
	}
	

	public boolean finished () {
		return fail;
	}
	
	public Checker cloneHere (int pos) {
		RepeatChecker rChecker = new RepeatChecker(this.seqLength, this.repeatNumber, pos);
		return rChecker;
	}
	
	@Override
	public String toString() {
//		o e.g. “Repeat(5,2) - 37{GCCATGCCAT}”
		return "Repeat(" + this.seqLength +","+ this.repeatNumber + ") - " + this.position + "{" + this.patternSoFar + "}";
	}


}
