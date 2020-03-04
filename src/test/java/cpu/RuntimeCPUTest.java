package cpu;

import org.junit.Test;

import java.io.IOException;

public class RuntimeCPUTest {

    @Test
    public void testExecute() throws IOException {
        new RuntimeCPU().test();

        System.out.println("Runtime.getRuntime().totalMemory(sdfasd)=" + Runtime.getRuntime().totalMemory());
        System.out.println("Runtime.getRuntime().freeMemory()=" + Runtime.getRuntime().freeMemory());
        System.out.println("Runtime.getRuntime().maxMemory()=" + Runtime.getRuntime().maxMemory());

    }
}
