import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StatementExtractor {
    private static final Logger LOGGER = Logger.getLogger(StatementExtractor.class.getName());
    private static final int TRESHHOLD = 3;
    public static final Set<Class> complexNodeTypes = new HashSet<Class>(Arrays.asList(IfStmt.class, WhileStmt.class, ForStmt.class, ForeachStmt.class));

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: StatementExtractor [pathToSources]");
            System.exit(-1);
        }
        String pathToSources = args[0];
        StatementExtractor statementExtractor = new StatementExtractor();
        List<Node> extractableNodes = statementExtractor.parseAllClassesAndMethods(pathToSources);
        LOGGER.log(Level.INFO, "Code fragments to be extracted: ");
        for (Node node : extractableNodes) {
            LOGGER.log(Level.INFO, node.toString(new PrettyPrinterConfiguration()));
        }
    }

    public List<Node> parseAllClassesAndMethods(String pathToSourceFiles) {
        Collection<File> allJavaSourceFiles = FileUtils.listFiles(new File(pathToSourceFiles), new JavaSourceFilesFilter(), TrueFileFilter.INSTANCE);
        List<Node> extractableNodes = allJavaSourceFiles.stream()
                .map(file -> parseFile(file))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return extractableNodes;
    }

    protected List<Node> parseFile(File file) {
        List<Node> result = new ArrayList<>();
        try {
            CompilationUnit cu = JavaParser.parse(new FileInputStream(file));
            for (TypeDeclaration typeDeclaration:cu.getTypes()) {
                result.addAll(processType(typeDeclaration));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ignoring: " + file.getAbsolutePath());
        }
        return result;
    }

    private List<Node> processType(TypeDeclaration<?> typeDeclaration) {
        List<Node> result = new ArrayList<>();
        for (MethodDeclaration methodDeclaration: typeDeclaration.getMethods()) {
            BlockStmt body = methodDeclaration.getBody().get();
            result.addAll(traverseNodes(body.getChildNodes(), new ArrayList<>(), 0));
        }
        return result;
    }

    private List<Node> traverseNodes(List<Node> childNodes, List<Node> result, int level) {
        for (Node node : childNodes) {
            if (level == TRESHHOLD) {
                List<Node> offspring = getOffspring(node);
                if (nodeHasChildren(offspring)) {
                    Node parentNode = node.getParentNode().get();
                    if (nodeIsOfComplexType(parentNode)) {
                        result.add(parentNode);
                        return result;
                    }
                }
            } else {
                traverseNodes(node.getChildNodes(), result, level + 1);
            }
        }
        return result;
    }

    private boolean nodeIsOfComplexType(Node node) {
        if (complexNodeTypes.contains(node.getClass())) {
            return true;
        }
        return false;
    }

    private boolean nodeHasChildren(List<Node> nodes) {
        for (Node node : nodes) {
            if (node.getChildNodes().size() > 0) {
                return true;
            }
        }
        return false;
    }

    private List<Node> getOffspring(Node node) {
        return getOffspring(node, new ArrayList<>());
    }

    private List<Node> getOffspring(Node node, List<Node> result) {
        for (Node child : node.getChildNodes()) {
            result.add(node);
            result.addAll(getOffspring(child, result));
        }
        return result;
    }
}
