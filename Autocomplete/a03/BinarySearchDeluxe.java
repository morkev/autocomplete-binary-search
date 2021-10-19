package a03;

import java.util.Comparator;

/**
 * When binary is searching a sorted array that contains more than 
 * one key equal to the search key, the client may want to know 
 * the index of either the first or the last such key. 
 * 
 * @author Kevin Mora
 * @author Michael Barlow
 */
public class BinarySearchDeluxe {
	
    /**
     * Return the index of the first key in a[] that equals the search key, or -1 if no such key.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) {
    		throw new NullPointerException("Arguments cannot be null.");
    	}
    	int low = 0,
    		high = a.length - 1;
    	if (comparator.compare(a[0], key) == 0) {
    		return 0;	// Non-recursive base case.
    	}
    	while (low <= high) {
    		int mid = low + (high - low) / 2;
    		// For key, we are searching for the first occurrence.
    		// Comparator: compare the key is being sorted with.
    		if (comparator.compare(key, a[mid]) < 0) high = mid - 1;
    		else if (comparator.compare(key, a[mid]) > 0) low = mid + 1;
    		else if (comparator.compare(a[mid - 1], a[mid]) == 0) high = mid - 1;
    		
    		else return mid;
    	}
		return -1;		// Index of the first occurrence of an element matching key in a[].
    }

    /**
     * Return the index of the last key in a[] that equals the search key, or -1 if no such key.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) {
    		throw new NullPointerException("Arguments cannot be null.");
    	}
    	int low = 0,
    		high = a.length - 1;
    	if (comparator.compare(a[high], key) == 0) {
    		return high;	// Non-recursive base case.
    	}
    	while (low <= high) {
    		int mid = low + (high - low) / 2;
    		// Search for the last occurrence: key.
    		if (comparator.compare(key, a[mid]) < 0) high = mid - 1;
    		else if (comparator.compare(key, a[mid]) > 0) low = mid + 1;
    		else if (comparator.compare(a[mid + 1], a[mid]) == 0) low = mid + 1;
    		
    		else return mid;
    	}
		return -1;			// Index of the last occurrence of an element matching key in a[].
    }
    
    
//    /**
//     * My attempt at making first/lastIndexOf recursive. This method, however, 
//     * requires 2 more integer parameters, which will make us fail JUnits.
//     */
//    private static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator, int low, int high) {
//        if (a == null || key == null || comparator == null) {
//            throw new NullPointerException("Arguments cannot be null.");
//        }
//        if (comparator.compare(a[0], key) == 0) {
//            return 0; // Non-recursive base case.
//        }
//        if (low <= high) {
//            int mid = low + (high - low) / 2;
//            // For key, we are searching for the first occurrence.
//            // Comparator: compare the key is being sorted with.
//            if (comparator.compare(key, a[mid]) < 0)
//                return firstIndexOf(a, key, comparator, low, mid - 1);
//            else if (comparator.compare(key, a[mid]) > 0)
//                return firstIndexOf(a, key, comparator, mid+1, high);
//            else if (comparator.compare(a[mid - 1], a[mid]) == 0)
//                return firstIndexOf(a, key, comparator, low, mid - 1);
//            else
//                return mid;
//        }
//        return -1; // Index of the first occurrence of an element matching key in a[].
//    }
}
