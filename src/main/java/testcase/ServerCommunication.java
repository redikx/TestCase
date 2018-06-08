package testcase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCommunication {

    private Server server;
    Logger logger = LoggerFactory.getLogger(ServerCommunication.class);

    public ServerCommunication(Server server) {
	super();
	this.server = server;
    }

    public void SendMessage(String message) throws UnknownHostException, IOException  {
	Socket socket = new Socket(server.getHostName(),server.getPort());
	StringBuffer instr = new StringBuffer();
	logger.info("SocketClient initialized for " + message);
	try {
	    BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
	    OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
	    String messageToSend = (message + (char)13);
	    osw.write(messageToSend);
	    osw.flush();
	    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
	    InputStreamReader isr = new InputStreamReader(bis, "US-ASCII");
	    int carrier;
	    	while ( (carrier = isr.read()) != 13) instr.append((char)carrier);
	    	socket.close();
	}
	
	catch (IOException f) {logger.error(f.getMessage());}
	catch (Exception e) {logger.error(e.getMessage());}
	finally { }
	}
    }
