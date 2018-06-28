package testcase;

import java.io.IOException;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseRun {

    private static Logger logger = LoggerFactory.getLogger(TestCaseRun.class);

    public static void execute(Server server, TestCase tc) throws IOException, InterruptedException, SocketException {
	// *
	ServerCommunication serverCommunication = new ServerCommunication(server);
	serverCommunication.connect();
	
	tc.readFile();
	for (String cur : tc)
	{
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		logger.warn(e.getMessage());
	    }
	    //logger.debug("Sending:" + cur);
	    try {
		String result = serverCommunication.sendMessage(cur);
		logger.debug("Sending " + cur);
		logger.debug(" Output from server : " + result);
		
		// check output from server, if I as 1st, not R = QUIT
		
		if (!result.isEmpty()) {
	    if ( (result.substring(0,2).equals("E["))) {
		logger.error(" ERROR, EXITING!!!");
		serverCommunication.close();
		System.exit(1);
	    }
	    
		}
	    
		Thread.sleep(1000);
	    }
	     catch (IOException c) {
		logger.error(c.getMessage());
		throw c;
	    }
	}
	serverCommunication.close();
    }

    public static void main(String args[]) throws Exception {

	Logger logger = LoggerFactory.getLogger(TestCase.class);
	logger.info("Start files reading");
	
	
	TestCase testCase = new TestCase("source_file1.txt");
	TestCase testCase2 = new TestCase("source_file2.txt");
	
	//TestCase testCase = new TestCase("c:\\Java\\Projects\\source_file1.txt");
	//TestCase testCase2 = new TestCase("c:\\Java\\Projects\\source_file2.txt");
	logger.info("Files loaded, connecting");
	//Server server = new Server("192.168.198.130", 31015);
        Server server = new Server("10.242.44.22",31015);

	execute(server, testCase);
	execute(server, testCase2);

    }
}
