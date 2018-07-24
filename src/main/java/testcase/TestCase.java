package testcase;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import datamodel.Run_CasesDAO;
import datamodel.Run_CasesDAO_interface;

@Component
@Scope(value ="prototype")// ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestCase implements Iterable<String>, Runnable {

	
    private final static Logger logger = LoggerFactory.getLogger(TestCase.class);
    
    private int run_id;
    
    public TestCase() 
    { }
    
    public void readRunId(int Run_id) {
    	this.run_id = Run_id;
    }
    
    File file;
    private String filePath;

    @Autowired
    private Server server;
  
    @Autowired
    private Run_CasesDAO run_CasesDAO;
    
    private List<String> lines = null;

    public TestCase(String filePath) {
	// file = new File(filePath);
	ClassLoader classLoader = getClass().getClassLoader();
	file = new File(classLoader.getResource(filePath).getFile());
    }

    public String getFilePath() {
	return filePath;
    }

    public void readFile() throws IOException {
	lines = new ArrayList<String>();
	try {
	    Scanner sc = new Scanner(file);

	    while (sc.hasNextLine()) {
		lines.add(sc.nextLine());
	    }
	    sc.close();
	} catch (IOException ioe) {
	    logger.error("IOException error while scanning " + file.getPath());
	} 
    }
	
    public String getLine(int line) throws IOException {
	if (lines == null) {
	    throw new IOException("File has not been read");
	}
	return lines.get(line);
    }

    public int getSize() throws IOException {
	if (lines == null) {
	    throw new IOException("File has not been read");
	}
	return lines.size();
    }

    public Iterator<String> iterator() {
	Iterator<String> it = new Iterator<String>() {

	    private int currentIndex = 0;

	    public boolean hasNext() {
		return lines != null && currentIndex < lines.size() && lines.get(currentIndex) != null;
	    }

	    public String next() {
		return lines.get(currentIndex++);
	    }

	    public void remove() {
		throw new UnsupportedOperationException();
	    }
	};
	return it;
    }

    public void run() {
    	System.out.println("RUN ID ::::: " + run_id);
    	//int run_id = this.readRun_Id();
	// *
	//long startTime =  System.currentTimeMillis();
	//Date startDate=new Date(startTime);
	logger.debug("Start RUN()");
	ServerCommunication serverCommunication = new ServerCommunication(server);
	try {
	    serverCommunication.connect();
            readFile();
	} catch (UnknownHostException e) {
	    logger.error(e.getMessage() + "Unknown host Exception");
	} catch (IOException e) {
	    logger.error(e.getMessage() + " IOException");
	}

	for (String cur : lines) {
	    /*
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		logger.warn(e.getMessage());
	    }
	    */
	    try {
		logger.debug("Sending " + cur);
		// Here insert into Run_cases
		 int run_case_id = run_CasesDAO.insertRuns(cur);
		 System.out.println("RUN CASE ID = " + run_case_id);
		 
		String result = serverCommunication.sendMessage(cur);
		logger.debug(" Output from server : " + result);
		// check output from server, if I as 1st, not R = QUIT

		if (!result.isEmpty()) {
			String colResult = result.substring(0, 3);
			run_CasesDAO.updateETime(run_case_id, colResult);
		    if ((!result.substring(0, 2).equals("R["))) {
			//logger.error(" ERROR, EXITING!!!");
			serverCommunication.close();
			//	System.exit(1);
		    }

		}

	    } catch (IOException c) {
		logger.error(c.getMessage());
	    }
	}
	try {
	    serverCommunication.close();
	} catch (IOException e) {
	    logger.error(e.getMessage());
	}
	//long endTime = System.currentTimeMillis();
	logger.debug("STOP Thread");

    }
    }
