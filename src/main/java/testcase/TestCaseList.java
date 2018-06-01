package testcase;

import java.util.ArrayList;
import java.util.List;

public class TestCaseList {


    private List<TestCase> testCaseList = new ArrayList<TestCase>();

    public void setTestCaseList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    public void addTestCase(TestCase tc) {
        testCaseList.add(tc);
    }

    public int getTestCaseListSize() {
        return testCaseList.size();
    }

    public TestCase getTestCase(int tc) {
        return testCaseList.get(tc);
    }
}
