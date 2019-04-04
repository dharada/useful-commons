package cpu;

import org.junit.Test;

import java.io.IOException;

public class RuntimeCPUTest {

    @Test
    public void testExecute() throws IOException {
        new RuntimeCPU().test();
    }
}
