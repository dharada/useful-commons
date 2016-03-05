package sort;

/**
 *
 */
public class QuickSort {

    /*
     * クイックソート（再帰用） 配列arrayの、leftからrightを並べ替えます。
     */
    public void quickSort(int[] array, int left, int right) {

        if (left == right) {
            return;
        }

        int pivot = pivot(array, left, right);

        if (pivot != -1) {

            int index = partition(array, left, right, array[pivot]);

            quickSort(array, left, index - 1);
            quickSort(array, index, right);

        }

    }

    /*
     * 軸要素の選択 順に見て、最初に見つかった異なる2つの要素のうち、
      * 大きいほうの番号を返します。 全部同じ要素の場合は -1 を返します。
     */
    int pivot(int[] a, int i, int j) {

        int k = i + 1;

        while (k <= j && a[i] == a[k]) {
            k++;
        }

        if (k > j) {
            return -1;
        }

        if (a[i] >= a[k]) {
            return i;
        } else {
            return k;
        }

    }

    /*
     * パーティション分割 a[startIndex]～a[endIndex]の間で、x を軸として分割します。
     * x より小さい要素は前に、大きい要素はうしろに来ます。
     *
     * 大きい要素の開始番号を返します。
     */
    int partition(int[] array, int startIndex, int endIndex, int x) {

        int left = startIndex;
        int right = endIndex;

        // 検索が交差するまで繰り返します
        while (left <= right) {

            // 軸要素以上のデータを探します
            while (left <= endIndex && array[left] < x) {
                left++;
            }

            // 軸要素未満のデータを探します
            while (right >= startIndex && array[right] >= x) {
                right--;
            }

            if (left > right) {
                break;
            }

            // 入れ替え
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }

        return left;
    }

}
