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
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import org.jetbrains.uast.UCallExpression;


public class TestOneDetector extends Detector implements Detector.UastScanner {

    private static final Class<? extends Detector> DETECTOR_CLASS = TestOneDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "TestOneIssue";
    private static final String ISSUE_DESCRIPTION = "这是一个测试的例子";
    private static final String ISSUE_EXPLANATION = "模仿着来写";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.ERROR;
    private static final String CHECK_PACKAGE = "com.example.lintdemo.view.ViewTest";
    private static final String CHECK_METHOD = "getName";

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
    public List<String> getApplicableMethodNames() {
        return Collections.singletonList(CHECK_METHOD);
    }

    @Override
    public List<String> getApplicableCallNames() {
        return Collections.singletonList(CHECK_METHOD);
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod method) {
        boolean inClass = context.getEvaluator().isMemberInClass(method, CHECK_PACKAGE);
        if (inClass) {
            context.report(ISSUE, context.getLocation(call), "查到了调用ViewTest这个类了");
        }
    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {
        boolean inClass = context.getEvaluator().isMemberInClass(method, CHECK_PACKAGE);
        if (inClass) {
            context.report(ISSUE, context.getLocation(node), "查到了调用ViewTest这个类了");
        }
    }

}
