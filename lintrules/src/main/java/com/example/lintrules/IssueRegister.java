package com.example.lintrules;

import java.util.Arrays;
import java.util.List;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.example.lintrules.detectors.FiledDetector;
import com.example.lintrules.detectors.LayoutDetector;
import com.example.lintrules.detectors.ReferenceDetector;
import com.example.lintrules.detectors.SampleCodeDetector;
import com.example.lintrules.detectors.TestOneDetector;
import com.example.lintrules.detectors.ToastUtilsDetector;


/**
 * XsfActivityFragmentLayoutNameDetector.ACTIVITY_LAYOUT_NAME_ISSUE,
 * XsfActivityFragmentLayoutNameDetector.FRAGMENT_LAYOUT_NAME_ISSUE,
 *                 XsfMessageObtainDetector.ISSUE,
 *                 XsfCustomToastDetector.ISSUE,
 *                 XsfLogDetector.ISSUE,
 *                 XsfViewIdNameDetector.ISSUE
 */
public class IssueRegister extends IssueRegistry {

    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                SampleCodeDetector.ISSUE,
                TestOneDetector.ISSUE,
                ReferenceDetector.ISSUE,
                LayoutDetector.ISSUE,
                FiledDetector.ISSUE,
                ToastUtilsDetector.ISSUE
        );
    }
}
