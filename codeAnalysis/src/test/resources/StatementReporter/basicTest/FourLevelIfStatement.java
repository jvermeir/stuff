package StatementReporter;

public class FourLevelIfStatement {
    private static final MethodData EMPTY_MOTHODDATA = new MethodData("EMPTY", "EMPTY", "EMPTY");

    public int methodWithStatementInStatementInStatementInStatemnent() {
        int i = 1;
        if (true) {
            i = 2;
            if (true) {
                i = 3;
                if (true) {
                    // methodWithStatementInStatementInStatementInStatemnent
                    i = 4;
                }
            }
        }
        return i;
    }
}
