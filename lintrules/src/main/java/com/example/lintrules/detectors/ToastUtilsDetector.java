package com.example.lintrules.detectors;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UExpression;

public class ToastUtilsDetector extends Detector implements Detector.UastScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = ToastUtilsDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "ToastUseError";
    private static final String ISSUE_DESCRIPTION = "hahahhahahahahah";
    private static final String ISSUE_EXPLANATION = "举起手来，你被发现了！！！";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.ERROR;
    private static final String CHECK_PACKAGE = "android.widget.Toast";

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
        return Arrays.asList("makeText", "show");
    }

    @Override
    public void visitMethod(@NonNull JavaContext context, @NonNull UCallExpression node, @NonNull PsiMethod method) {
        if (!context.getEvaluator().isMemberInClass(method, CHECK_PACKAGE)) {
            return;
        }

        // context.report(ISSUE, context.getLocation(node), "这个是toast的检测");
        context.report(ISSUE, node, context.getLocation(node), ISSUE_DESCRIPTION, "这个是toast的检测");
    }
}
