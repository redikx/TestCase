package testcase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCommunication  implements Closeable {

    private Server server;
    private Socket sock = null;

    Logger logger = LoggerFactory.getLogger(ServerCommunication.class);

    public ServerCommunication(Server server){
	super();
	this.server = server;
    }

    public void connect() throws IOException {
	this.sock = new Socket(server.getHostName(), server.getPort());
	logger.info("SocketClient initialized");
    }

    public void sendMessage(String message) throws IOException  {
	StringBuilder instr = new StringBuilder();

	if (this.sock.isConnected()) {
	    BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
	    OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
	    String messageToSend = message + '\r';
	    osw.write(messageToSend);
	    osw.flush();
	    //BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());
	    //InputStreamReader isr = new InputStreamReader(bis, "US-ASCII");
	    InputStream  isr = sock.getInputStream();
	    int carrier;
	    logger.debug("Reading answer" );
	    while ( (carrier = isr.read()) != 'A') {
		logger.debug("Received=" + carrier);
	        instr.append((char)carrier);	    	    
	    }
	} else {
	    logger.error("Socket is not connected");
	}
	   
    }

    public void close() throws IOException {
	if (!this.sock.isClosed()) {
	    this.sock.close();
	}
	
    }
    
}
