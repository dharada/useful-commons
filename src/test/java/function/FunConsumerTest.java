package function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FunConsumerTest {

    @Test
    public void testConsumer() {

        // todo about java.util.function.Consumer usage

        Consumer<Integer> consumer = i -> System.out.print(" " + i);

        List<Integer> integerList = Arrays.asList(new Integer(1),
                new Integer(10), new Integer(200),
                new Integer(101), new Integer(-10),
                new Integer(0));

        printList(integerList, consumer);


    }

    private void printList(List<Integer> integerList, Consumer<Integer> consumer) {

        for (Integer integer : integerList) {
            consumer.accept(integer);
        }
    }

}
