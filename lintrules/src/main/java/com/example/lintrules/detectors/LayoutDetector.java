package com.example.lintrules.detectors;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import org.w3c.dom.Element;

public class LayoutDetector extends ResourceXmlDetector {
    private static final Class<? extends Detector> DETECTOR_CLASS = LayoutDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.RESOURCE_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "ResourceXmlIssue";
    private static final String ISSUE_DESCRIPTION = "扫描某个view在xml里的调用";
    private static final String ISSUE_EXPLANATION = "对应某个类的应用";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.ERROR;
    private static final String CHECK_PACKAGE = "com.example.lintdemo.view.TestView";
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
    public Collection<String> getApplicableElements() {
        return Arrays.asList(
                CHECK_PACKAGE,
                "com.example.lintdemo.view.TestView2"
        );
    }

    @Override
    public void visitElement(XmlContext context, Element element) {
        String tagName = element.getTagName();
        if (tagName.equals(CHECK_PACKAGE) || tagName.equals("com.example.lintdemo.view.TestView2")) {
            context.report(ISSUE, element, context.getLocation(element), "xml布局中调用了TestView");
        }
    }
}
