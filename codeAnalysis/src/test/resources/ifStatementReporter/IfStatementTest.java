package ifStatementReporter;

public class IfStatementTest {
    private static final MethodData EMPTY_MOTHODDATA = new MethodData("EMPTY", "EMPTY", "EMPTY");

    public int methodWithStatementInStatementInStatement() {
        int i = 1;
        if (true) {
            i = 2;
            if (true) {
                i = 3;
                if (true) {
                    i = 4;
                }
            }
        }
        return i;
    }
}
