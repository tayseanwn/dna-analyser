# dna-analyser
<h1>Language and Framework</h1>
Java (Eclipse)

<h1>Overview</h1>

DNA sequences are being used in many fields: genetics, virology, forensics, and even
computing. Analysing the sequences is of critical importance. The sequences are composed of
four bases: adenine(A), thymine(T), guanine(G), and cytosine(C). A double helix of base pairs
(A binds with T and G binds with C) form within a DNA molecule. In a DNA sequence, a triplet of
base pairs form a codon which is used to code for amino acids as well as activation and
termination.

<h2>Purpose</h2>
We are going to model DNA simply as a sequence of chars that we will
traverse looking for certain patterns and pattern types. To limit the scope of our DNA analysis,
we are only going to focus on two pattern types: palindromes of a certain length and repeating
sequences of a certain length and number of repetitions. In general, a palindrome is a sequence
that reads the same forward as backwards.

<h2>Design</h2>
The DNA class provides arbitrary sequences which will simulate real DNA sequences.

The palindrome checker recognizes a palindrome of a certain length.

The repeat checker recognizes sequences of a certain lengths that are repeated a certain number of times

The analyzer takes a sequence check for specific types of sequences. 

The two checker classes is e used to create many instances, each will only check for sequences that start at a
particular base in the sequence. 

Specifically, a new checker will be created for each base in the
sequence. This strategy of
using many checkers helps reduce the complexity of code of each checker.
