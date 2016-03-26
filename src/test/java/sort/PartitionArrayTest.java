package sort;

import org.junit.Test;

/**
 * Created by dharada on 2016/03/26.
 */
public class PartitionArrayTest {

    @Test
    public void partition() throws Exception {

        int[] array = new int[] { 1, 5, 19, 7, 23232, 33, 3232, 122, 99090909, 33 };

        int partition = PartitionArray.partition(array, 0, array.length - 1);

        System.out.println(partition);

        for (int element : array) {
            System.out.println(element);
        }

    }
}