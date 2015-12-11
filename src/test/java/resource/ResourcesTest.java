package resource;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static resource.Resources.utf8Bundle;

/**
 *
 *
 */
public class ResourcesTest {

    @Test
    public void testUtf8Bundle() throws Exception {
        assertThat(utf8Bundle("hoge").getString("key_hoge"), is("あいう"));
    }
}