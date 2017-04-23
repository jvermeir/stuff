import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StatementReporterTest {
    private static final String PATH_TO_SOURCE_FILES = "target/test-classes/StatementReporter";

    @Test
    public void testMethodWithStatementInStatementInStatementReturnsBlockWithiIs3() {
        StatementExtractor statementExtractor = new StatementExtractor();
        List<Node> nodes = statementExtractor.parseAllClassesAndMethods(PATH_TO_SOURCE_FILES);
        Assert.assertEquals(1, nodes.size());
        String statement = nodes.get(0).toString(new PrettyPrinterConfiguration());
        Assert.assertTrue(statement.indexOf("i = 3;") > 0 );
        Assert.assertTrue(statement.indexOf("i = 4;") > 0 );
        Assert.assertTrue(statement.indexOf("i = 2;") < 0 );
    }

}
