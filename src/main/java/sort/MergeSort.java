package sort;

/**
 * もっとも小さい配列（要素数1）まで分解されたら、それを順序良く併合していきます。
 */
public class MergeSort {

    void mergeSort(int[] array) {

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
            merge(left, right, array);

        }

    }

    private void merge(int[] left, int[] right, int[] a) {

        int i = 0, j = 0;

        while (i < left.length || j < right.length) {

            if (j >= right.length || (i < left.length && left[i] < right[j])) {
                a[i + j] = left[i];
                i++;
            } else {
                a[i + j] = right[j];
                j++;
            }
        }
    }

}
