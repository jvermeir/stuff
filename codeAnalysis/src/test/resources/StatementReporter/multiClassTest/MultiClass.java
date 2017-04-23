package StatementReporter;

public class MultiClass {
    private static final MethodData EMPTY_MOTHODDATA = new MethodData("EMPTY", "EMPTY", "EMPTY");

    public int method1MultiClass() {
        int i = 1;
        if (true) {
            i = 2;
            if (true) {
                i = 3;
                if (true) {
                    // method1MultiClass()
                    i = 4;
                }
            }
        }
        return i;
    }

}

class MultiClassPrivate {
    private static final MethodData EMPTY_MOTHODDATA = new MethodData("EMPTY", "EMPTY", "EMPTY");

    public int method1MultiClassPrivate() {
        int i = 1;
        if (true) {
            i = 2;
            if (true) {
                i = 3;
                if (true) {
                    // method1MultiClassPrivate()
                    i = 4;
                }
            }
        }
        return i;
    }

}
