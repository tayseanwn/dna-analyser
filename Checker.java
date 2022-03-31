package dnaAnalyser;

public interface Checker {

//	This is an interface that we use to define the important features of Checkers. The interface must
//	have the following abstract methods:
//		● public boolean process(char c) - used to process a single char c
//		● public boolean finished() - used for seeing if the checker needs to continue processing
//		● public Checker cloneHere(int pos) - used to create a new Checker with the same
//		  attributes but restarting at pos position in the DNA sequence
	
	public boolean process (char c);
	
	public boolean finished ();
	
	public Checker cloneHere (int pos);
	
}
