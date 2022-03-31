package dnaAnalyser;
import java.util.Iterator;
import java.util.Random;

public class DNA implements Iterator<Character> {
	private boolean completed;
	private static final String[] stopCodon = { "TAG", "TGA", "TAA" };
	private int codonCount;
	private char[] currentCodon = new char[3];
	private double percentG;
	private StackADT<Character> initial;
	private Random random;

	public DNA(double percentG, long seed) {
		codonCount = 0;
		this.percentG = percentG;
		initial = new ArrayStack<Character>();
		initial.push('G'); // must start with ATG....
		initial.push('T');
		initial.push('A');
		completed = false;
		random = new Random(seed);
	}

	public char randomBase() { 
		double randomNumber = random.nextDouble();
		if (randomNumber < percentG)
			return 'G';
		if (randomNumber < (2 * percentG))
			return 'T';
		if (randomNumber < (0.5 + percentG))
			return 'A';

		return 'C';
	}

	public Character next() {
		char c;
		if (initial.isEmpty())
			c = randomBase();
		else
			c = initial.pop();
		currentCodon[codonCount] = c;
		codonCount = (codonCount + 1) % 3;

		if (codonCount == 0) {
			String check = String.valueOf(currentCodon);
			for (int i = 0; i < stopCodon.length; i++) {
				if (stopCodon[i].equals(check))
					completed = true;
			}
		}

		return c;
	}

	public boolean hasNext() {
		return !completed;
	}

	/**
	 *
	 */
	
//	This is a complete class but it contains a few methods that you will need to use. Its main
//	purpose of this class is to produce a sequence of Characters one at a time to simulate a DNA
//	sequence. It implements the iterator interface, hasNext() and next(), to have access to the
//	sequence one Character at a time. As a means of testing defined sequences, the iterateString
//	method produces an iterator that will walk through the input String. To help verify the results, the
//	display method formats a long sequence into 50 character lengths with additional numbers to
//	help indicate a characters’ location in the sequence.
	
//			● public static Iterator<Character> iterateString(String) - to simulate the DNAs action with
//			defined String. The returned Iterator will give the characters of the input String one at a
//			time.
	
//			● public static String display(String) - shows the DNA String in a friendlier way to locate the
//			found patterns to better understand the results from the checkers
	
	public static Iterator<Character> iterateString(String sequence) {
		LinkedQueue<Character> chars = new LinkedQueue<Character>();
		for (int i = 0; i < sequence.length(); i++) {
			chars.enqueue(sequence.charAt(i));
		}
		return chars.iterator();
	}

	public static String display(String sequence) {
		String out = "";
		// make it easier to find any sequence by number
		for (int i = 1; i <= 50; i++)
			out += (i % 10 == 0) ? (i % 100) / 10 : (i % 10 == 5) ? "*" : ".";
		out += "\n";

		char[] c = sequence.toCharArray();
		for (int i = 0; i < c.length; i++) {
			out += c[i];
			if (i % 50 == 49)
				if (i % 100 == 49)
					out += " " + (i - 49) + "\n";
				else
					out += "\n";
		}
		out += "\n";
		return out;
	}

	public static void main(String[] args) {
		// testing
		long seed = System.currentTimeMillis();
		DNA dna = new DNA(0.25, seed);
		int dead = 1001;

		System.out.println("DNA strand " + seed);
		while (dna.hasNext() && dead > 0) {
			System.out.print(dna.next());
			if (dead % 3 == 0)
				System.out.print(".");

			dead--;
		}
		System.out.println();
	}
}
