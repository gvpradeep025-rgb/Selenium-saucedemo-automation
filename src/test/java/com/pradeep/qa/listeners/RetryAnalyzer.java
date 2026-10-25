package com.pradeep.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** Re-runs a failed test once, to handle flaky failures caused by slow network. */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRY = 1;
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            System.out.println("[RETRY] " + result.getMethod().getMethodName() + " attempt " + count);
            return true;
        }
        return false;
    }
}
