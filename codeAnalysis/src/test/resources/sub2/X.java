public class X {

    private static String CLASSNAME="X";
    private boolean wrongMethodName(String value) {
        String METHODNAME = "thisMethodNameIsWrong";
        LOGGER.entering(CLASSNAME, METHODNAME);
        boolean result;
        LOGGER.exiting(CLASSNAME, METHODNAME);
        return result;
    }

    private Map<String, Object> methodNameOK(Map<String, Object> promocode) {
        String METHODNAME = "methodNameOK";
        LOGGER.entering(CLASSNAME, METHODNAME);
        LOGGER.exiting(CLASSNAME, METHODNAME);
        return responseMap;
    }

    private void methodWithParameters(String[] keywords, Integer storeId) {
        String METHODNAME = "methodWithParameters(String[] keywords, Integer storeId)";
    }

    private void methodWithleadingSpaceInLogText(String[] keywords, Integer storeId) {
        String METHODNAME = " methodWithleadingSpaceInLogText(String[] keywords, Integer storeId)";
    }
}
