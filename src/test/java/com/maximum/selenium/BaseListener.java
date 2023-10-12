package com.maximum.selenium;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseListener
        extends BaseTest
        implements ITestListener {

    private final static String PROD_TEST = "prod";

    @Override
    public void onTestStart(ITestResult result) {
        Set<String> groups = new HashSet<>(List.of(result.getMethod().getGroups()));
        String testName = result.getTestName();
        if (isOnProd() && !groups.contains(PROD_TEST))
            throw new SkipException("Test with name: '" + testName +
                    "' is skipped because not marked as PROD-ready");
    }

    private boolean isOnProd() {
        return PROD_TEST.equalsIgnoreCase(getEnvironment());
    }
}