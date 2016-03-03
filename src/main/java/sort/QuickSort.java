package sort;

/**
 *
 */
public class QuickSort {

    /*
     * クイックソート（再帰用） 配列arrayの、a[sindex]からa[eindex]を並べ替えます。
     */
    public void quickSort(int[] array, int sindex, int eindex) {

        if (sindex == eindex) {
            return;
        }

        int p = pivot(array, sindex, eindex);

        if (p != -1) {

            int k = partition(array, sindex, eindex, array[p]);

            quickSort(array, sindex, k - 1);
            quickSort(array, k, eindex);
        }

    }

    /*
     * 軸要素の選択 順に見て、最初に見つかった異なる2つの要素のうち、 大きいほうの番号を返します。 全部同じ要素の場合は -1 を返します。
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
     * パーティション分割 a[i]～a[j]の間で、x を軸として分割します。 x より小さい要素は前に、大きい要素はうしろに来ます。
     * 大きい要素の開始番号を返します。
     */
    int partition(int[] a, int i, int j, int x) {

        int l = i, r = j;

        // 検索が交差するまで繰り返します
        while (l <= r) {

            // 軸要素以上のデータを探します
            while (l <= j && a[l] < x)
                l++;

            // 軸要素未満のデータを探します
            while (r >= i && a[r] >= x)
                r--;

            if (l > r)
                break;
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++;
            r--;
        }
        return l;
    }

}
