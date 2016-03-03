package sort;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ep001656 on 2016/03/03.
 */
public class MergeSortTest {

    @Test
    public void testMergeSort() throws Exception {

        final int[] ints = new MergeSort().mergeSort(new int[] { -1, 0, 10, 60, 7, 2, 1, 44444, 1, 4 });

        Assert.assertThat(ints[0], Matchers.is(-1));
        Assert.assertThat(ints[ints.length -1], Matchers.is(44444));

        for (int i : ints) {
            System.out.println(i);
        }

    }
}