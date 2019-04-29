package com.example.lintrules.detectors;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;

import org.jetbrains.uast.UCallExpression;

/**
 * 扫描findViewById方法的返回类型
 * 根据返回类型来确定是否是返回的某个类
 * 即使用findViewById方法来构造某个view的扫描
 */
public class LocalFiledDetector extends Detector implements Detector.UastScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = LocalFiledDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "LocalFiledIssue";
    private static final String ISSUE_DESCRIPTION = "局部变量扫描";
    private static final String ISSUE_EXPLANATION = "对应某个类的应用";
    private static final Category ISSUE_CATEGORY = Category.SECURITY;
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
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("findViewById");
    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {
        PsiType returnType = node.getReturnType();
        if (returnType instanceof PsiClassType) {
            PsiClass psiClass = ((PsiClassType) returnType).resolve();
            if (psiClass.getQualifiedName().equals(CHECK_PACKAGE)) {
                context.report(ISSUE, context.getLocation(node), "使用findViewById来初始化TestView了");
            }
        }
    }
}
