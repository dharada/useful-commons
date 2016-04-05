package sort;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class QuickSortTest {

    @Test
    public void testQuickSort() throws Exception {

        int[] array = new int[] { 1, 2, 45352, 22, 9 };
        new QuickSort().quickSort(array, 0, array.length - 1);

        assertThat(array[2], Matchers.is(9));

    }
}