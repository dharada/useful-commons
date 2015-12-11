package validate;

/**
 * utility of Object
 */
public class ObjectUtil {

    /**
     * check the param is null or not.
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     *
     * check the param is not null, or null.
     * 
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

}
