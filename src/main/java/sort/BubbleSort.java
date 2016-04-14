package sort;

/**
 * Created by ep001656 on 2016/04/05.
 */
public class BubbleSort {

    public static void sort(long[] array) {


        for (int outDex = array.length-1; outDex > 1; outDex--) {

            for (int inDex = 0; inDex < outDex; inDex++) {

                if (array[inDex] > array[inDex + 1]) {
                    // swap
                    long temp = array[inDex];
                    array[inDex] = array[inDex + 1];
                    array[inDex + 1] = temp;
                }

            }

        }




    }

}
