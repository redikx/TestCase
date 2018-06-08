package testcase;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseRun {
    

    public static void main(String args[]) throws Exception {
	Logger logger = LoggerFactory.getLogger(TestCase.class);
	logger.info("Start files reading");
	TestCase testCase = new TestCase("c:\\Java\\Projects\\source_file1.txt");
	TestCase testCase2 = new TestCase("c:\\Java\\Projects\\source_file2.txt");
	int Users;
	TestCaseList testCaseList = new TestCaseList();
	testCaseList.addTestCase(testCase);
	testCaseList.addTestCase(testCase2);
	

	for (int i = 0; i < testCaseList.getTestCaseListSize(); i++) {
	    TestCase tc = testCaseList.getTestCase(i);
	    for (int j = 0; j < tc.getSize(); j++) {
		//Server server = new Server("10.242.44.22",31015);
		Server server = new Server("127.0.0.1",31015);
		ServerCommunication serverCommunication = new ServerCommunication(server);
		Thread.sleep(3000);
		try {serverCommunication.SendMessage(tc.getLine(j));} 
			catch (ConnectException c) {logger.error(c.getMessage());}

	    }
	}
    }
}
