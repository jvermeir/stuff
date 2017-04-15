public class Y {

    private static String CLASSNAME="Y";
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
}

