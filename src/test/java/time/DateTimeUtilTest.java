package time;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by ep001656 on 2015/12/07.
 */
public class DateTimeUtilTest {

    @Test
    public void testYyyyMMdd() throws Exception {
        assertThat(DateTimeUtil.yyyyMMdd(), Matchers.containsString("/"));
    }
}