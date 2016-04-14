package sort;

/**
 * Created by dharada on 2016/04/14.
 */
public class Merge {

    public int[] execute(int[] leftArray, int[] rightArray, int[] wksArray) {

        int leftArrayLength = leftArray.length;
        int rightArrayLength = rightArray.length;

        int leftDex = 0;
        int rightDex = 0;

        //wksArray = new int[leftArrayLength+rightArrayLength];
        int wksIndex = 0;

        while (leftDex < leftArrayLength && rightDex < rightArrayLength) {

            if (leftArray[leftDex] <= rightArray[rightDex]) {

                wksArray[wksIndex] = leftArray[leftDex];
                leftDex++;

            } else {

                wksArray[wksIndex] = rightArray[rightDex];
                rightDex++;
            }

            wksIndex++;
        }

        while (leftDex < leftArrayLength) {
            wksArray[wksIndex] = leftArray[leftDex];
            leftDex++;
            wksIndex++;
        }

        while (rightDex < rightArrayLength) {

            wksArray[wksIndex] = rightArray[rightDex];
            rightDex++;
            wksIndex++;
        }

        return wksArray;
    }

}
