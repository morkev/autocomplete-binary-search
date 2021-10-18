package a03;

import java.util.Comparator;

/**
 * Implements the Comparable interface, which includes the {@code compareTo} method, 
 * defining thus the ordering of an object.
 * 
 * Comparator<Term> allows us to specify an alternative order in which elements of a 
 * given type <Term> should be sorted, and also decouples the definition of the type 
 * <Term> from the definition of what it means to compare two objects of that type.
 * <br><br>
 * {@code <Term>}: immutable data type representing an auto-complete term.
 * 
 * @author Kevin Mora
 * @author Michael Barlow
 */
public class Term implements Comparable<Term> {
    private String query;
    private double weight;

    /**
     * Creates a Term with the given query string and weight.
     * @param query: term to be assigned.
	 * @param weight: weight the term will have.
     */
    public Term(String query, double weight) {
        if (query == null) {
        	throw new NullPointerException();
        }
        if (weight < 0) {
        	throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in descending order by weight.
     */
    public static Comparator<Term> byReverseWeightOrder() {
        Comparator<Term> comparator = new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return (int) (o2.weight - o1.weight);
            }
        };
        return comparator;
    }

    /**
     * Compares the two terms in lexicographic order, but 
     * using only the first r characters of each query.
     */
    public static Comparator<Term> byPrefixOrder(final int r) {
        if (r < 0) {
        	throw new IllegalArgumentException();
        }
        return new Comparator<Term>() {
            @Override
            public int compare(Term lexiValue1, Term lexiValue2) {
                int k = Math.min(lexiValue2.query.length(), 
                		Math.min(lexiValue1.query.length(), r));
                
                String subString1 = lexiValue1.query.substring(0, k);
                String subString2 = lexiValue2.query.substring(0, k);
                
                return subString1.compareTo(subString2);
            }
        };
    }

    /**
     * Compares the two terms in lexicographic order by query.
     */
    public int compareTo(Term that) {
        return query.compareTo(that.query);
    }

    /**
     * Returns a string representation of this term in the following format: <br>
     * the weight, followed by a tab, followed by the query.
     */
    public String toString() {
        return weight + "\t" + query;
    }
}