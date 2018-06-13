package testcase;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseRun {
    
    private  static Logger logger = LoggerFactory.getLogger(TestCaseRun.class);
  
    public static void execute(Server server, TestCase tc) throws IOException, InterruptedException {
	//* 
	ServerCommunication serverCommunication = new ServerCommunication(server);
	    serverCommunication.connect();
	    
	    for (int j = 0; j < tc.getSize(); j++) {
		try {
		   Thread.sleep(100);
		} catch (InterruptedException e) {
		    logger.warn(e.getMessage());
		}
		
		String msg = tc.getLine(j);
		logger.debug("Sending:" + msg);
		try {
		    serverCommunication.sendMessage(msg);
		    Thread.sleep(100);
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
	logger.info("Files loaded, connecting");
	Server server = new Server("192.168.198.130",22);
	execute(server, testCase);
	execute(server, testCase2);
	    

	}
    }

