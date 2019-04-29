package com.example.lintrules.detectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.lang.ASTNode;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMember;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.impl.java.stubs.JavaClassElementType;
import com.intellij.psi.tree.IElementType;

import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UExpression;
import org.jetbrains.uast.UField;
import org.jetbrains.uast.UMethod;
import org.jetbrains.uast.UVariable;

/**
 * 全局变量的扫描
 */
public class FiledDetector extends Detector implements Detector.UastScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = FiledDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "FiledIssue";
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
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Arrays.asList(UField.class);
    }

    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new FiledHandler(context);
    }

    private static class FiledHandler extends UElementHandler {
        private final JavaContext mContext;

        public FiledHandler(JavaContext context) {
            this.mContext = context;
        }

        @Override
        public void visitField(UField node) {
            PsiType type = node.getType();
            if (type instanceof PsiClassType) {
                PsiClass psiClass = ((PsiClassType) type).resolve();
                String qualifiedName = psiClass.getQualifiedName();
                if (qualifiedName.equals(CHECK_PACKAGE)) {
                    mContext.report(ISSUE, node, mContext.getLocation(node), "Java的全局变量里调用了TestView这个类了");
                }
            }
        }

    }

}
