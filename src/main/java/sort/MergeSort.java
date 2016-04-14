package sort;

/**
 * もっとも小さい配列（要素数1）まで分解されたら、それを順序良く併合していきます。
 */
public class MergeSort {

    public int[] mergeSort(int[] array) {

        if (array.length > 1) {

            int middle = array.length / 2;
            int n = array.length - middle;

            int[] left = new int[middle];
            int[] right = new int[n];

            for (int i = 0; i < middle; i++) {
                left[i] = array[i];
            }

            for (int j = 0; j < n; j++) {
                right[j] = array[middle + j];
            }

            mergeSort(left);
            mergeSort(right);

            // 最後にマージ

            //merge(left, right, array);
            mergeExecute(left, right, array);

        }

        return array;

    }

    private void mergeExecute(int[] leftArray, int[] rightArray, int[] array) {
        new Merge().execute(leftArray, rightArray, array);
    }

    /**
     * merge logic impl
     * 
     * @param leftArray
     * @param rightArray
     * @param array
     */
    private void merge(int[] leftArray, int[] rightArray, int[] array) {

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftArray.length || rightIndex < rightArray.length) {

            if (rightIndex >= rightArray.length
                    || (leftIndex < leftArray.length && leftArray[leftIndex] < rightArray[rightIndex])) {

                array[leftIndex + rightIndex] = leftArray[leftIndex];
                leftIndex++;

            } else {

                array[leftIndex + rightIndex] = rightArray[rightIndex];
                rightIndex++;

            }
        }
    }

}
