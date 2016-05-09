package sort;

/**
 * ピボットとして一つ選びそれをPとする。
 * 左から順に値を調べ、P以上のものを見つけたらその位置をiとする。
 * 右から順に値を調べ、P以下のものを見つけたらその位置をjとする。
 *
 *  iがjより左にあるのならばその二つの位置を入れ替え、2に戻る。
 *  ただし、次の2での探索はiの一つ右、次の3での探索はjの一つ左から行う。
 *  2に戻らなかった場合、iの左側を境界に分割を行って2つの領域に分け、
 *  そのそれぞれに対して再帰的に1からの手順を行う。要素数が1以下の領域ができた場合、その領域は確定とする。
 *
 *  注：このアルゴリズムでは、領域の左端の値が領域内で最小かつ同じ値が他の位置に無い場合、
 *
 *  ピボットとしてその値を選ぶと同じ領域を再帰的に呼び出す無限ループとなってしまう。
 *  ピボットは、通常、データ列からランダムに選んだり、データ列中の適当な三つ程度の数の中央値を選ぶ。
 *  このように、ピボットの選び方を工夫することで、最悪計算時間がかかってしまうような入力データ列の可能性を抑える。
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
     * 大きい要素の"開始番号"を返します。
     */
    int partition(int[] array, int startIndex, int endIndex, int pivot) {

        int left = startIndex;
        int right = endIndex;

        // 検索が交差するまで繰り返します
        while (left <= right) {

            // 軸要素以上のデータを探します
            while (left <= endIndex && array[left] < pivot) {
                left++;
            }

            // 軸要素未満のデータを探します
            while (right >= startIndex && array[right] >= pivot) {
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
