package dnaAnalyser;

public class DnaTester {
	public static int passed = 0;
	public static int tested = 0;

	public static void main(String[] args) {
		PalindromeChecker p1 = new PalindromeChecker(6);
		boolean result = false;
		String pat1 = "abccba";
		String pat2 = "ababab";
		for (int i = 0; i < pat1.length(); i++)
			result = p1.process(pat1.charAt(i));

		// ********** PalindromeChecker result1
		test(1, "PalindromeChecker result1", result);

		// ********** PalindromeChecker toString
		test(2, "PalindromeChecker toString", p1.toString().equals("Palindrome(6) - 0{abccba}"));
		System.out.println(p1.toString());

		// ********** PalindromeChecker instanceof Checker
		test(3, "PalindromeChecker instanceof Checker", p1 instanceof Checker);

		Checker ch = p1.cloneHere(0);
		for (int i = 0; i < pat2.length(); i++)
			result = ch.process(pat2.charAt(i));

		// ********** PalindromeChecker result2
		test(4, "PalindromeChecker result2", !result);

		RepeatChecker r1 = new RepeatChecker(2, 3);
		for (int i = 0; i < pat2.length(); i++)
			result = r1.process(pat2.charAt(i));

		// ********** RepeatChecker result1
		test(5, "RepeatChecker result1", result);

		// ********** RepeatChecker toString
		test(6, "RepeatChecker toString", r1.toString().equals("Repeat(2,3) - 0{ababab}"));
		System.out.println(r1.toString());

		// ********** RepeatChecker instanceof Checker
		test(7, "RepeatChecker instanceof Checker", r1 instanceof Checker);

		ch = r1.cloneHere(0);
		for (int i = 0; i < pat1.length(); i++)
			result = ch.process(pat1.charAt(i));

		// ********** RepeatChecker result2
		test(8, "RepeatChecker result2", !result);
		
		LinkedQueue<Checker> checker = new LinkedQueue<Checker>();
		checker.enqueue(new RepeatChecker(2, 2));
		checker.enqueue(new PalindromeChecker(4));
		DnaAnalyser testD = new DnaAnalyser(checker);
		String dna = testD.search(DNA.iterateString("AGGATCTCACCAG"));
		String out = testD.displayAnalysis(dna);
		
		// ********** DnaAnalyer search1
		test(9, "DnaAnalyer search1", out.contains("Palindrome(4) - 1{AGGA}") && out.contains("Repeat(2,2) - 5{TCTC}") // it says starting at char 1, when you make them they start at 0
				&& out.contains("Palindrome(4) - 9{ACCA}")
				&& out.contains("....*....1....*....2....*....3....*....4....*....5") && out.contains("AGGATCTCACCAG"));

		System.out.println(out.toString());
		
		dna = testD.search(DNA.iterateString("ATGGGGGAAGAG"));
		out = testD.displayAnalysis(dna);

		// ********** DnaAnalyer search2
		test(10, "DnaAnalyer search2", out.contains("Repeat(2,2) - 3{GGGG}") && out.contains("Palindrome(4) - 3{GGGG}")
				&& out.contains("Repeat(2,2) - 4{GGGG}") && out.contains("Palindrome(4) - 4{GGGG}")
				&& out.contains("Palindrome(4) - 7{GAAG}") && out.contains("Repeat(2,2) - 9{AGAG}")
				&& out.contains("....*....1....*....2....*....3....*....4....*....5") && out.contains("ATGGGGGAAGAG"));

		checker = new LinkedQueue<Checker>();
		for (int i = 5; i < 7; i++)
			checker.enqueue(new RepeatChecker(i, 2));
		for (int i = 2; i < 5; i++)
			checker.enqueue(new RepeatChecker(i, 3));
		for (int i = 10; i < 14; i++)
			checker.enqueue(new PalindromeChecker(i));
		testD = new DnaAnalyser(checker);
		dna = testD.search(new DNA(0.25, (long) 59961));
		out = testD.displayAnalysis(dna);

		// ********** DnaAnalyer search3
		test(11, "DnaAnalyer search3",
				out.contains("Repeat(5,2) - 138{GCCATGCCAT}") && out.contains("Palindrome(11) - 237{AATCAAACTAA}")
						&& out.contains("Palindrome(10) - 310{CAAGAAGAAC}")
						&& out.contains("....*....1....*....2....*....3....*....4....*....5")
						&& out.contains("ATGATGAATACGTGTGGGGCTATCGTCAAATGTAGCTTAGCGCCTAACAC 0")
						&& out.contains("AAGGAAGGGGTATGCGTCGAGTAGTTTTAGGACGGGCGTCACCGATCGGG")
						&& out.contains("TGGGCGGTGCTATTTCCTCAAACGTGCGGGTTGAGTGGCCATGCCATAAA 100")
						&& out.contains("CGACTGTCATTGAGGAACATTTCTCGTTCTGGCTCGGGACAAGCTCTACT")
						&& out.contains("TTGTAAGAACCTAGAGTACCACGGTCGAAGCCACGCAATCAAACTAAGCG 200")
						&& out.contains("CCATAACCATCATTACTCTGTACAGGATTCCCGTTGCGAACGATAAGAGT")
						&& out.contains("GTGGCATTACAAGAAGAACGCAATACCATACATTCTTCTACGAGAGTGCC 300")
						&& out.contains("CACCAACGTGTCCTACTGCCCCTTGGGCCGTTTTCCAGTACGTCGGCCCG")
						&& out.contains("TCCGAGCACCCCAAACAGACAGCGAACAGGTGCATGCGTTTCATGCGCAT 400")
						&& out.contains("AAGTATAGAGGCTCAGTGATACGTAGTTTGGCAAAGGAGCGACTATAA"));

		dna = testD.search(new DNA(0.3, (long) 21379));
		out = testD.displayAnalysis(dna);

		// ********** DnaAnalyer search4
		test(12, "DnaAnalyer search4",
				out.contains("Repeat(3,3) - 166{GATGATGAT}") && out.contains("Palindrome(10) - 206{TAGTTTTGAT}")
						&& out.contains("Palindrome(10) - 276{TTGTGGTGTT}") && out.contains("Repeat(2,3) - 301{TTTTTT}")
						&& out.contains("Repeat(2,3) - 469{GCGCGC}") && out.contains("Repeat(2,3) - 544{TGTGTG}")
						&& out.contains("Palindrome(11) - 550{GGTTGTGTTGG}")
						&& out.contains("....*....1....*....2....*....3....*....4....*....5")
						&& out.contains("ATGAGCTATAAGGATCTTGTTAGCTTTAGGTCCGAGTTTTATTTGACTTA 0")
						&& out.contains("TGTCGTAGTTTATGTCACGGTAGAATTTTGCTTCATTGCTGTTTGTGTTA")
						&& out.contains("TTTTCTTGTTGTCAGGTGTCGAGATCTTGTCGTTATTAAAGTGGACTCGC 100")
						&& out.contains("GATCGCAGAAAGCTAGATGATGATATTTGGGGTCTCGTCGGCTCGCAGTC")
						&& out.contains("CATGCTAGTTTTGATTTTTCAGTGCGTCTGCGCGGAGCGAGTTTTTGTCG 200")
						&& out.contains("GAGCTGGGCCTATGAGTAGTGTCCTTTGTGGTGTTCGGGAGCACGGGAGG")
						&& out.contains("TTTTTTCTATGTGGCTCAGCTCCAACCTATGGGGAGGGGCTGGTACTTAG 300")
						&& out.contains("ATGCTTCCTAATACGAGGTGTTTTCGCTATGACACTCAGAGGGTCTAAAG")
						&& out.contains("GTAAGGATAATGCGTTGGCCCCACTATATGTGACGACGCCTCGAGTAAGG 400")
						&& out.contains("TGCGTAGCTAGGAATGAAGCGCGCCGTATGCATGAGCTTCATGGGTACGT")
						&& out.contains("CTCGCATTTCGCGAGGGGTCGTTTAGTAGATTTCGAAGACGTTTGTGTGG 500")
						&& out.contains("GTTGTGTTGGTCTGCGGAATTTTTGGCTGTGTTGTCAGGGCGGATGGTAT")
						&& out.contains("TATTCTGCTGTATAG"));

		System.out.println("Your code scored: " + passed + " / " + tested);
	}

	public static void test(int testNumber, String message, boolean testStatus) {
		tested++;
		System.out.println("Test " + testNumber + " (" + message + ") " + (testStatus ? "passed" : "failed"));
		if (testStatus)
			passed++;
	}

}
