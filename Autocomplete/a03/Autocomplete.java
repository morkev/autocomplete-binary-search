package a03;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Implements an immutable data type that provides autocomplete functionality 
 * for a given set of string and weights, using Term and BinarySearchDeluxe. 
 * To do so, it sorts the terms in lexicographic order; use binary search to 
 * find the set of terms that start with a given prefix; and sort the matching 
 * terms in descending order by weight.
 * 
 * @author Kevin Mora
 * @author Michael Barlow
 */
public class Autocomplete {
    private Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
        	throw new NullPointerException();
        }
        for (Term x : terms) {
            if (x == null) {
                throw new NullPointerException();
            }
        }
        this.terms = terms;
        // Sorts the data structure, in order to make Binary Search work.
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
    	if (prefix == null) {
        	throw new NullPointerException();
        }
    	
    	int start = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        int end = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        
        if(start == -1 || end == -1) {
        	return new Term[0];
        }
        
        Term[] matches = new Term[numberOfMatches(prefix)];
        matches = Arrays.copyOfRange(terms, start, end + 1);
        
        // Sort elements by reverse weight order
        Arrays.sort(matches,Term.byReverseWeightOrder());
        
		return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
        	throw new NullPointerException();
        }
        // Creates a Term query (prefix), and initializes the weight with 0.
        Term term = new Term(prefix, 0);
        // Initializes "start" as the first element of the data structure:
        // (Key[] a, Key key, Comparator<Key> comparator)
        int start = BinarySearchDeluxe.firstIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
        if(start < 0) {
        	return 0;
        }
        int end = BinarySearchDeluxe.lastIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
        
        return end - start + 1;
    }

    public static void main(String[] args) {
    	// Read in the terms from a file
        String filename = "src/dictionaries/wiktionary.txt";
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // Read the next weight
            in.readChar();                         // Scan past the tab
            String query = in.readLine();          // Read the next query
            terms[i] = new Term(query, weight);    // Construct the term
        }

        // Read in queries from standard input and print out the top k matching terms
        StdOut.println("Read data finished");
        StdOut.println("Words in Dictionary: " + terms.length);
        // Max. number of suggestions to return
        int k = 5;
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}