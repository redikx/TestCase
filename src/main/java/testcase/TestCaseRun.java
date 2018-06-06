package testcase;

public class TestCaseRun {

    public static void main(String args[]) throws Exception {
	System.out.println("Starting file Reading");
	TestCase testCase = new TestCase("c:\\Java\\Projects\\source_file.txt");
	TestCase testCase2 = new TestCase("c:\\Java\\Projects\\source_file_ext.txt");

	TestCaseList testCaseList = new TestCaseList();
	System.out.println("Size before adding to list: " + testCaseList.getTestCaseListSize());
	testCaseList.addTestCase(testCase);
	testCaseList.addTestCase(testCase2);
	System.out.println("Size after adding to list: " + testCaseList.getTestCaseListSize());

	for (int i = 0; i < testCaseList.getTestCaseListSize(); i++) {
	    TestCase tc = testCaseList.getTestCase(i);
	    for (int j = 0; j < tc.getSize(); j++) {
		System.out.println(" File " + tc.getFilePath() + " line " + j + " value : " + tc.getLine(j));
	    }
	}
    }
}
