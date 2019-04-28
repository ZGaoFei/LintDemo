package com.example.lintrules.detectors;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UImportStatement;

/**
 * 对导包的扫描
 */
public class ReferenceDetector extends Detector implements Detector.UastScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = ReferenceDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "ReferenceCheckIssue";
    private static final String ISSUE_DESCRIPTION = "扫描引用被调用";
    private static final String ISSUE_EXPLANATION = "对应某个类的应用";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.ERROR;
    private static final String CHECK_PACKAGE = "com.example.lintdemo.view.ViewTest";
    private static final String CHECK_CLAss = "ViewTest";

    public static final Issue ISSUE = Issue.create(
            ISSUE_ID,
            ISSUE_DESCRIPTION,
            ISSUE_EXPLANATION,
            ISSUE_CATEGORY,
            ISSUE_PRIORITY,
            ISSUE_SEVERITY,
            IMPLEMENTATION
    );

    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(UImportStatement.class);
    }

    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new ImportVisitor(context);
    }

    private static class ImportVisitor extends UElementHandler {

        private final JavaContext context;

        public ImportVisitor(JavaContext context) {
            this.context = context;
        }

        public void visitImportStatement(UImportStatement statement) {
            PsiElement resolved = statement.resolve();
            if (resolved instanceof PsiClass) {
                String qualifiedName = ((PsiClass)resolved).getQualifiedName();
                if (CHECK_PACKAGE.equals(qualifiedName)) {
                    Location location = this.context.getLocation(statement);
                    this.context.report(ISSUE, statement, location, "不能在这里引入这个包");
                }
            }

        }
    }
}
