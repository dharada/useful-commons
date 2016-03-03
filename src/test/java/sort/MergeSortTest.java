package sort;

import org.junit.Test;

/**
 * Created by ep001656 on 2016/03/03.
 */
public class MergeSortTest {

    @Test
    public void testMergeSort() throws Exception {

        final int[] ints = new MergeSort().mergeSort(new int[] { -1, 0, 10, 60, 7, 2, 1, 44444, 1, 4 });

        for (int i : ints) {

            System.out.println(i);

        }

    }
}