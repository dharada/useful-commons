package search;

/**
 * Binary Search example with "Recursive call"
 */
public class BinarySearch {

    /**
     *
     * @param sortedArray
     * @param target
     * @param left
     * @param right
     * @return
     */
    public boolean binarySearch(int[] sortedArray, int target, int left, int right) {

        if (sortedArray == null || sortedArray.length < 1) {
            return false;
        }
        int leftSideIndex = left;
        int rightSideIndex = right;

        int midIndex = (leftSideIndex + rightSideIndex) / 2;

        if (sortedArray[midIndex] == target) {
            return true;
        } else if (sortedArray[midIndex] < target) {

            // 検索対象が残っていない
            if (leftSideIndex == rightSideIndex) {
                return false;
            }

            // 左半分にはtargetは存在しない。-> 左半分を切り捨て
            leftSideIndex = midIndex + 1;

            // Recursive call
            binarySearch(sortedArray, target, leftSideIndex, rightSideIndex);

        } else {

            // 検索対象が残っていない
            if (leftSideIndex == rightSideIndex) {
                return false;
            }

            // 右半分にはtargetは存在しない -> 右半分を切り捨て
            rightSideIndex = midIndex - 1;

            // Recursive call
            binarySearch(sortedArray, target, leftSideIndex, rightSideIndex);
        }

        return false;

    }
}
