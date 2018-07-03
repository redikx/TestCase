package testcase_Junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cloudracer.mocktcpserver.MockTCPServer;
import io.cloudracer.mocktcpserver.tcpclient.TCPClient;
import testcase.Server;
import testcase.TestCase;
import testcase.TestCaseRun;

public class TestCaseRunTest extends TestCaseRun {

    private static Logger logger = LoggerFactory.getLogger(TestCaseRunTest.class);
    
    Server srv ;
    MockTCPServer mockserver;
    
    public TestCaseRunTest() {
	srv  = new Server("127.0.0.1",31015);
    }

    @Before
    public void setUp() throws Exception {
	byte b[] = { 13 };
	 this.mockserver = new MockTCPServer(31015);
	 this.mockserver.setTerminator(b);
    }

    @After
    public void tearDown() throws Exception {
	  this.mockserver.close();
    }

   @Test
   public void testConnectToMockTCPServer() throws ClassNotFoundException, IOException {
       TCPClient client = new TCPClient(31015);
       client.connect();
        assertArrayEquals("A".getBytes(), client.send("Hello World\r").toByteArray());
       client.close();
   }
   
   
    @Test
    public void testCase1() throws InterruptedException {
	TestCase tc1 = new TestCase("c:\\Java\\Projects\\source_file1.txt");
	try {
	    tc1.execute();
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    fail(e.getMessage());
	    
	}
    }
    
    @Test
    public void testCase2() throws InterruptedException {
	TestCase tc2 = new TestCase("c:\\Java\\Projects\\source_file2.txt");
	try {
	    tc2.execute();
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    fail(e.getMessage());
	    
	}
    }

}
