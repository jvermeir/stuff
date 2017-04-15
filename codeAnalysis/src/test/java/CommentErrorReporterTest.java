import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CommentErrorReporterTest {
    @Test
    public void testTwoMethodsAreWrongAndFourMethodsAreCorrect() {
        CommentErrorReporter commentErrorReporter = new CommentErrorReporter();
        commentErrorReporter.parseAllClassesAndMethods("target/test-classes/");
        Assert.assertEquals(2, commentErrorReporter.getMethodsWithWrongLogLine().size());
        Assert.assertEquals(4, commentErrorReporter.getCorrectMethods().size());
    }

    @Test
    public void testMethodsAreQualifiedCorrectly() {
        CommentErrorReporter commentErrorReporter = new CommentErrorReporter();
        commentErrorReporter.parseAllClassesAndMethods("target/test-classes/sub2/");
        HashSet correctMethods = new HashSet(commentErrorReporter.getCorrectMethods());
        Assert.assertEquals(3, correctMethods.size());
        correctMethods.remove(new MethodData("X", "methodNameOK", "methodNameOK"));
        correctMethods.remove(new MethodData("X", "methodWithParameters", "methodWithParameters"));
        correctMethods.remove(new MethodData("X", "methodWithleadingSpaceInLogText", "methodWithleadingSpaceInLogText"));
        Assert.assertEquals(0, correctMethods.size());

        HashSet incorrectMethods = new HashSet(commentErrorReporter.getMethodsWithWrongLogLine());
        Assert.assertEquals(1, incorrectMethods.size());
        incorrectMethods.remove(new MethodData("X", "wrongMethodName", "thisMethodNameIsWrong"));
        Assert.assertEquals(0, incorrectMethods.size());
    }

    @Test
    public void testClassNameIsCorrectInResult() {
        CommentErrorReporter commentErrorReporter = new CommentErrorReporter();
        List<MethodData> methodDataList = commentErrorReporter.parseFile("target/test-classes/sub2/X.java");
        MethodData firstMethod = methodDataList.get(0);
        Assert.assertEquals("X",firstMethod.className);
    }

    @Test
    public void testReportContainsAllMethods() {
        CommentErrorReporter commentErrorReporter = new CommentErrorReporter();
        commentErrorReporter.parseAllClassesAndMethods("target/test-classes/");
        String expectedResult = "Methods with errors \n" +
                "[Y, wrongMethodName, thisMethodNameIsWrong]\n" +
                "[X, wrongMethodName, thisMethodNameIsWrong]\n" +
                "Methods without errors \n" +
                "[Y, methodNameOK, methodNameOK]\n" +
                "[X, methodNameOK, methodNameOK]\n" +
                "[X, methodWithParameters, methodWithParameters]\n" +
                "[X, methodWithleadingSpaceInLogText, methodWithleadingSpaceInLogText]\n";
        Assert.assertEquals(expectedResult, commentErrorReporter.printReport());
    }

    @Test
    public void junk() {
        List<String> l = Arrays.asList("a", "b");
        String result = l.stream().filter(x -> x.equals("hello")).findFirst().orElse("");
        System.out.println(result);
    }
}
