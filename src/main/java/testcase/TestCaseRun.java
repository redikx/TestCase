package testcase;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseRun {
    
    private  static Logger logger = LoggerFactory.getLogger(TestCaseRun.class);
  
    public static void execute(Server server, TestCase tc) throws IOException {
	    ServerCommunication serverCommunication = new ServerCommunication(server);
	    serverCommunication.connect();
	    
	    for (int j = 0; j < tc.getSize(); j++) {
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    logger.warn(e.getMessage());
		}
		
		String msg = tc.getLine(j);
		logger.debug("Sending:" + msg);
		try {
		    serverCommunication.sendMessage(msg);
		} catch (IOException c) {
		    logger.error(c.getMessage());
		    throw c;
		}

	    }
	    
	    serverCommunication.close();
    }

    public static void main(String args[]) throws Exception {
	Logger logger = LoggerFactory.getLogger(TestCase.class);
	logger.info("Start files reading");
	TestCase testCase = new TestCase("c:\\Java\\Projects\\source_file1.txt");
	TestCase testCase2 = new TestCase("c:\\Java\\Projects\\source_file2.txt");
	//int Users;
	TestCaseList testCaseList = new TestCaseList();
	testCaseList.addTestCase(testCase);
	testCaseList.addTestCase(testCase2);
	

	for (int i = 0; i < testCaseList.getTestCaseListSize(); i++) {
	    TestCase tc = testCaseList.getTestCase(i);
	    Server server = new Server("127.0.0.1",31015);

	}
    }
}
