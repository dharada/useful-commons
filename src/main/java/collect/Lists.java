package collect;

import java.util.List;

/**
 * Created by dharada on 2015/12/06.
 */
public class Lists {

    public static <E> List<E> newArrayList(E... elements) {
        return com.google.common.collect.Lists.newArrayList(elements);
    }

}
