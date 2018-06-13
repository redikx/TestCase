package testcase;

<<<<<<< HEAD
import java.io.IOException;
=======
import java.net.ConnectException;
>>>>>>> 2e8c80dc26338d67864a021e4d640948371c58c6

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseRun {
    

<<<<<<< HEAD
    private static Logger logger = LoggerFactory.getLogger(TestCaseRun.class);

    public static void execute(Server server, TestCase tc) throws IOException, InterruptedException {
	// *
	ServerCommunication serverCommunication = new ServerCommunication(server);
	serverCommunication.connect();

	tc.readFile();
	for (String cur : tc) {
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		logger.warn(e.getMessage());
	    }

	    logger.debug("Sending:" + cur);
	    try {
		serverCommunication.sendMessage(cur);
		Thread.sleep(1000);
	    } catch (IOException c) {
		logger.error(c.getMessage());
		throw c;
=======
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

>>>>>>> 2e8c80dc26338d67864a021e4d640948371c58c6
	    }
	}

	serverCommunication.close();

    }

    public static void main(String args[]) throws Exception {

	Logger logger = LoggerFactory.getLogger(TestCase.class);
	logger.info("Start files reading");
	TestCase testCase = new TestCase("c:\\Java\\Projects\\source_file1.txt");
	TestCase testCase2 = new TestCase("c:\\Java\\Projects\\source_file2.txt");
	logger.info("Files loaded, connecting");

        Server server = new Server("10.242.44.22",31015);
	//Server server = new Server("192.168.198.130", 31015);
	execute(server, testCase);
	execute(server, testCase2);

    }
}
