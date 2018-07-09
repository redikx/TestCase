package testcase;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestCase implements Iterable<String>, Runnable {

    
    private final static Logger logger = LoggerFactory.getLogger(TestCase.class);
    
    File file;
    private String filePath;

    @Autowired
    private Server server;
    
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

    public void execute() throws IOException, InterruptedException, SocketException {
	// *

	ServerCommunication serverCommunication = new ServerCommunication(server);
	serverCommunication.connect();

	readFile();
	for (String cur : lines) {
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		logger.warn(e.getMessage());
	    }
	    // logger.debug("Sending:" + cur);
	    try {
		String result = serverCommunication.sendMessage(cur);
		logger.debug("Sending " + cur);
		logger.debug(" Output from server : " + result);

		// check output from server, if I as 1st, not R = QUIT

		if (!result.isEmpty()) {
		    if ((!result.substring(0, 2).equals("R["))) {
			logger.error(" ERROR, EXITING!!!");
			serverCommunication.close();
		//	System.exit(1);
		    }

		}

		Thread.sleep(1000);
	    } catch (IOException c) {
		logger.error(c.getMessage());
		throw c;
	    }
	}
	serverCommunication.close();
    }

    public void run() {
	logger.debug("Czeck if RUN() is executed");
	try {
	    this.execute();
	} catch (SocketException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    
    }
}
