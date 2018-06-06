package testcase;

public class ServerConn {

    private String ipHost;
    private int portNumber;
    
    public ServerConn(String ipHost, int portNumber) {
	this.ipHost = ipHost;
	this.portNumber = portNumber;
    }
    
    public String getIpHost() {
        return ipHost;
    }
    public void setIpHost(String ipHost) {
        this.ipHost = ipHost;
    }
    public int getPortNumber() {
        return portNumber;
    }
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
    
    public void sendLine() {
	
    }
    
}
