package cpu;

public class RuntimeCPU {

    public void test() {

        // Returns the number of processors available to the Java virtual machine.
        int i = Runtime.getRuntime().availableProcessors();

        System.out.printf(new Integer(i).toString());

    }
}
