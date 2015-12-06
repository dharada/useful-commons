package collect;

import java.util.List;

/**
 * utility of List
 */
public class Lists {

    public static <E> List<E> newArrayList(E... elements) {
        return com.google.common.collect.Lists.newArrayList(elements);
    }

}
