package search;

import org.junit.Test;

/**
 */
public class BinarySearchTest {

    @Test
    public void testBinarySearch() throws Exception {

        int[] array = new int[] { 1, 5, 7, 10, 11, 11, 100 };

        boolean b = new BinarySearch().binarySearch(array, 1000, 0, array.length - 1);
        org.junit.Assert.assertFalse(b);
    }
}