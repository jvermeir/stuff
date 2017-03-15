public class MethodData {
    public final String methodName;
    public final String logLabel;
    public final String className;

    public MethodData(String className, String methodName, String logLabel) {
        this.className = className;
        this.methodName = methodName;
        this.logLabel = cleanupLogLabel(logLabel);
    }

    private String cleanupLogLabel(String logLabel) {
        int indexOfParenthesis = logLabel.indexOf('(');
        if (indexOfParenthesis > 0) {
            return logLabel.substring(0,indexOfParenthesis);
        }
        return logLabel;
    }

    @Override
    public String toString() {
        return "[" + className +", " + methodName + ", " + logLabel + "]";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}
