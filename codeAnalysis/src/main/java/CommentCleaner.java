import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommentCleaner {
    public static final String NOMETHODFOUND = "<NOMETHODFOUND>";
    private List<MethodData> correctMethods = new ArrayList<>();
    private List<MethodData> errors = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(CommentCleaner.class.getName());

    public List<MethodData> getMethodsWithWrongLogLine() {
        List<MethodData> result = new ArrayList<>();
        result.addAll(errors);
        return result;
    }

    public List<MethodData> getCorrectMethods() {
        List<MethodData> result = new ArrayList<>();
        result.addAll(correctMethods);
        return result;
    }

    public void parseAllClassesAndMethods(String pathToSourceFiles) {
        Collection<File> allJavaSourceFiles = FileUtils.listFiles(new File(pathToSourceFiles), new JavaSourceFilesFilter(), TrueFileFilter.INSTANCE);
        List<MethodData> methodDataList = allJavaSourceFiles.stream().map(file -> parseFile(file)).flatMap(Collection::stream).collect(Collectors.toList());
        errors.addAll(methodDataList.stream().filter(method -> !isMethodOk(method)).collect(Collectors.toList()));
        correctMethods.addAll(methodDataList.stream().filter(method -> isMethodOk(method)).collect(Collectors.toList()));
    }

    public String printReport() {
        StringBuilder result = new StringBuilder();
        result.append("Methods with errors \n");
        for (MethodData methodData : errors) {
            result.append(methodData + "\n");
        }
        result.append("Methods without errors \n");
        for (MethodData methodData : correctMethods) {
            result.append(methodData + "\n");
        }
        return result.toString();
    }

    private boolean isMethodOk(MethodData methodData) {
        return methodData.methodName.equals(methodData.logLabel);
    }

    protected List<MethodData> parseFile(String fileName) {
        return parseFile(new File(fileName));
    }

    protected List<MethodData> parseFile(File file) {
        List<MethodData> result = new ArrayList<>();
        try {
            CompilationUnit cu = JavaParser.parse(new FileInputStream(file));
            Stream<List<MethodData>> listStream = cu.getTypes().stream().map(type -> type.getMembers().stream()
                    .filter(member -> member instanceof MethodDeclaration)
                    .map(member -> getMethodNameVariableValue(type.getName(), (MethodDeclaration) member))
                    .filter(methodData -> methodData != null).collect(Collectors.toList()));
            result = listStream.findFirst().get();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ignoring: " + file.getAbsolutePath());
        }
        return result;
    }

    private MethodData getMethodNameVariableValue(SimpleName className, MethodDeclaration declaration) {
        MethodDeclaration method = (MethodDeclaration) declaration;
        Optional<BlockStmt> body = method.getBody();
        BlockStmt blockStmt = body.orElse(null);
        if (blockStmt == null) {
            return null;
        }
        NodeList<Statement> statements = blockStmt.getStatements();
        String logLabel = getMethodNameVariableValue(statements);
        if (logLabel != NOMETHODFOUND) {
            return new MethodData(className.toString(), method.getNameAsString(), logLabel);
        }
        return null;
    }

    private String getMethodNameVariableValue(NodeList<Statement> statements) {
        return statements.stream()
                .map(statement -> statement.getChildNodes().get(0).toString())
                .filter(statement -> statement.indexOf("String METHODNAME") >= 0)
                .map(statement -> getMethodName(statement))
                .findFirst()
                .orElse(NOMETHODFOUND);
    }

    private String getMethodName(String statementAsString) {
        String assignmentPart = statementAsString.split("=")[1].trim();
        String expressionToSplitOn = assignmentPart.indexOf('(') > 0 ? "\\(" : " ";
        String[] statementParts = assignmentPart.split(expressionToSplitOn);
        return statementParts[0].replaceAll("\"", "").trim();
    }

    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("Usage: CommentCleaner [pathToSources");
            System.exit(-1);
        }
        String pathToSources = args[0];
        CommentCleaner commentCleaner = new CommentCleaner();
        commentCleaner.parseAllClassesAndMethods(pathToSources);
        System.out.println(commentCleaner.printReport());
    }
}

class JavaSourceFilesFilter implements IOFileFilter {
    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(".java");
    }

    @Override
    public boolean accept(File file, String s) {
        return file.getName().endsWith(".java");
    }
}