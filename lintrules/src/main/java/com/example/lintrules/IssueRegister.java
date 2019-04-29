package com.example.lintrules;

import java.util.Arrays;
import java.util.List;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.example.lintrules.detectors.ClassDetector;
import com.example.lintrules.detectors.FiledDetector;
import com.example.lintrules.detectors.FindViewDetector;
import com.example.lintrules.detectors.LayoutDetector;
import com.example.lintrules.detectors.LocalFiledDetector;
import com.example.lintrules.detectors.ReferenceDetector;
import com.example.lintrules.detectors.SampleCodeDetector;
import com.example.lintrules.detectors.TestOneDetector;
import com.example.lintrules.detectors.ToastUtilsDetector;


/**
 * 注册Issues
 * 在build.gradle中添加注册才能生效
 */
public class IssueRegister extends IssueRegistry {

    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                SampleCodeDetector.ISSUE,
                /*TestOneDetector.ISSUE,
                ReferenceDetector.ISSUE,
                FiledDetector.ISSUE,
                ToastUtilsDetector.ISSUE,*/
                ClassDetector.ISSUE,
                LayoutDetector.ISSUE,
                LocalFiledDetector.ISSUE
                // FindViewDetector.ISSUE
        );
    }
}
