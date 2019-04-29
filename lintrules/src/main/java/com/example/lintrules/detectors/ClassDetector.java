package com.example.lintrules.detectors;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.uast.UCallExpression;

/**
 * 构造函数的扫描
 */
public class ClassDetector extends Detector implements Detector.UastScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = ClassDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "ClassIssue";
    private static final String ISSUE_DESCRIPTION = "扫描某个view在java代码里的调用";
    private static final String ISSUE_EXPLANATION = "对应某个类的应用";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.ERROR;
    private static final String CHECK_PACKAGE = "com.example.lintdemo.view.TestView";

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
    public List<String> getApplicableConstructorTypes() {
        return Collections.singletonList(CHECK_PACKAGE);
    }

    @Override
    public void visitConstructor(JavaContext context, UCallExpression node, PsiMethod constructor) {
        super.visitConstructor(context, node, constructor);
        context.report(ISSUE, node, context.getLocation(node), "调用TestView的构造函数");
    }

}
