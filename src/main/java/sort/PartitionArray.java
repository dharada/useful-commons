package sort;

/**
 *
 */
public class PartitionArray {

    /**
     * 配列array[left..right]を、array[left..point-1]の各要素が、array[point]以下で、
     * array[point+1..right]の各要素が、array[point]より大きいarray[point+1..right]に分割し、インデックスpointを戻り値として返す。
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] array, int left, int right) {

        int pivot = array[right];

        int index = left - 1;

        for (int j = left; j < right - 1; j++) {

            if (array[j] <= pivot) {
                index++;
                swap(array, index, j);
            }
        }

        swap(array, index + 1, right);

        return index + 1;

    }

    static void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
