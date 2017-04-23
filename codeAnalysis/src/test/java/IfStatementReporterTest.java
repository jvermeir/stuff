import com.github.javaparser.ast.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IfStatementReporterTest {
    private static final String PATH_TO_SOURCE_FILES = "target/test-classes/ifStatementReporter";

    @Test
    public void testMethodWithStatementInStatementInStatementReturnsBlockWithiIs3() {
        StatementExtractor statementExtractor = new StatementExtractor();
        List<Node> nodes = statementExtractor.parseAllClassesAndMethods(PATH_TO_SOURCE_FILES);
        Assert.assertEquals(1, nodes.size());

//        String extractedBlock = statementExtractor.getBlockToBeExtracted();
//        Assert.assertTrue(extractedBlock.indexOf("i = 3;") > 0 );
//        Assert.assertTrue(extractedBlock.indexOf("i = 4;") > 0 );
//        Assert.assertTrue(extractedBlock.indexOf("i = 2;") == 0 );
    }

}
