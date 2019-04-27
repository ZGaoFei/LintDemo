package com.example.lintrules;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.example.lintrules.detectors.SampleCodeDetector;
import com.example.lintrules.detectors.XsfActivityFragmentLayoutNameDetector;
import com.example.lintrules.detectors.XsfCustomToastDetector;
import com.example.lintrules.detectors.XsfLogDetector;
import com.example.lintrules.detectors.XsfMessageObtainDetector;
import com.example.lintrules.detectors.XsfViewIdNameDetector;


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
        System.out.println("*******XSF LINT RULES WORKS*******");
        return Collections.singletonList(
                SampleCodeDetector.ISSUE
        );
    }

}
