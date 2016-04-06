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

        for (int jindex = left; jindex < right - 1; jindex++) {

            // find smaller than pivot and move left
            if (array[jindex] <= pivot) {
                index++;
                swap(array, index, jindex);
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

    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {

            // partitionの位置は、決定
            int partition = partition(array, left, right);

            // 左半分 (partitionを除く）
            quickSort(array, left, partition - 1);

            // 右半分 (partitionを除く）
            quickSort(array, partition + 1, right);

        }

    }

}
