package testcase;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class Server {

    private String hostName;
    private int port;
    private InetAddress inetAddress;
    Logger logger = LoggerFactory.getLogger(TestCase.class);
    
    //* Constructor
    public Server(String hostName, int port) {
	super();
	this.hostName = hostName;
	this.port = port;
    }

    //* Getters and Setters
    public String getHostName() {return hostName;}

    public void setHostName(String hostName) {this.hostName = hostName;}

    public int getPort() {return port;}

    public void setPort(int port) {this.port = port;}

    public InetAddress getInetAddress() {return inetAddress;}

    public void setInetAddress(String hostName) {
	try {
	    inetAddress = InetAddress.getByName(hostName);
	} catch (UnknownHostException e) {
	    logger.error(e.getMessage(),e);
	}
    };

}
