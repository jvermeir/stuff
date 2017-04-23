package StatementReporter;

public class MultiMethod1 {
    private static final MethodData EMPTY_MOTHODDATA = new MethodData("EMPTY", "EMPTY", "EMPTY");

    public int multiMethod1FirstMethod() {
        int i = 1;
        if (true) {
            i = 2;
            if (true) {
                i = 3;
                if (true) {
                    // multiMethod1FirstMethod()
                    i = 4;
                }
            }
        }
        return i;
    }

    public int multiMethod1SecondMethod() {
        int i = 1;
        if (true) {
            i = 2;
            if (true) {
                i = 3;
                if (true) {
                    // multiMethod1SecondMethod()
                    i = 4;
                }
            }
        }
        return i;
    }
}
