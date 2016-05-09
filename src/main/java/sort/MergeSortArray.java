package sort;

/**
 * Created by dharada on 2016/04/03.
 */
public class MergeSortArray {

    // ref to array theArarry
    private long[] theArray;

    // number of data itmes
    private int nElems;

    public MergeSortArray(int max) {

        theArray = new long[max];
        nElems = 0;
    }

    public void insert(long item) {

        if (nElems + 1 > theArray.length) {
            // over max limitation
            // throw Exception
        }

        theArray[nElems] = item;
        nElems++;


    }

    public void display() {

        for (int index = 0; index < nElems; index++) {
            System.out.print(theArray[index] + " ");
        }

        System.out.print(System.getProperty("line.separator"));
    }

    public void mergeSort() {
        long[] longs = new long[nElems];
        recursiveMergeSort(longs, 0, nElems - 1);
    }

    public void recursiveMergeSort(long[] wks, int lowerBound, int upperBound) {

        if (lowerBound == upperBound) {
            // if range is 1, no use sorting
            return;
        }

        // divide
        int mid = (lowerBound + upperBound) / 2;
        recursiveMergeSort(wks, lowerBound, mid);
        recursiveMergeSort(wks, mid + 1, upperBound);

        // merge
        merge(wks, lowerBound, mid + 1, upperBound);

    }

    /**
     * こっちのmerge logicは素人にもわかりやすいなあ・・
     * @param wks
     * @param lowPtr
     * @param highPtr
     * @param upperBound
     */
    private void merge(long[] wks, int lowPtr, int highPtr, int upperBound) {

        int jDex = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;

        int numberOfItems = upperBound - lowPtr + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {

            if (theArray[lowPtr] <= theArray[highPtr]) {

                wks[jDex] = theArray[lowPtr];
                lowPtr++;

            } else if (theArray[lowPtr] > theArray[highPtr]) {
                
                wks[jDex] = theArray[highPtr];
                highPtr++;
            }
            jDex++;
        }

        while (lowPtr <= mid) {
            wks[jDex] = theArray[lowPtr];
            lowPtr++;
            jDex++;
        }

        while (highPtr <= upperBound) {
            wks[jDex] = theArray[highPtr];
            highPtr++;
            jDex++;
        }

        for (int hDex = 0; hDex < numberOfItems; hDex++) {
            theArray[lowerBound + hDex] = wks[hDex];
        }

        // end merge

    }


    public static void main(String[] args) {

        int max = 10;
        MergeSortArray mergeSortArray = new MergeSortArray(max);

        mergeSortArray.insert(1);
        mergeSortArray.insert(5);
        mergeSortArray.insert(2);
        mergeSortArray.insert(7000);
        mergeSortArray.insert(70);
        mergeSortArray.insert(320);
        mergeSortArray.insert(0);

        mergeSortArray.display();
        mergeSortArray.mergeSort();
        mergeSortArray.display();

    }

}
