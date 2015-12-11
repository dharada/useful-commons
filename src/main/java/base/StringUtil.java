package base;

import com.google.common.base.Strings;

import javax.annotation.Nullable;

/**
 * utility of strings
 * 
 */
public class StringUtil {

    /**
     * Returns the given string if it is non-null; the empty string otherwise.
     *
     * @param string
     *            the string to test and possibly return
     * @return {@code string} itself if it is non-null; {@code ""} if it is null
     */
    public static String nullToEmpty(@Nullable String string) {
        return Strings.nullToEmpty(string);
    }

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     *
     * @param string
     *            the string to test and possibly return
     * @return {@code string} itself if it is nonempty; {@code null} if it is
     *         empty or null
     */
    @Nullable
    public static String emptyToNull(@Nullable String string) {
        return Strings.emptyToNull(string);
    }

}
