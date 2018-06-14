package testcase;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCommunication  implements Closeable {

    private Server server;
    private Socket sock = null;
    Logger logger = LoggerFactory.getLogger(ServerCommunication.class);

    //* Constructor
    public ServerCommunication(Server server){
	super();
	this.server = server;
    }

    //* Connect method creating socket
    public void connect() throws IOException {
	this.sock = new Socket(server.getHostName(), server.getPort());
	logger.info("Connected OK to " + sock.getRemoteSocketAddress());
    }

    //* sendMessage method sending message to socket
    public void sendMessage(String message) throws IOException  {
	StringBuilder instr = new StringBuilder();
	
	if (this.sock.isConnected()) {
	    //* Sending Message
	    try {
	    BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
	    OutputStreamWriter osw = new OutputStreamWriter(bos);       //(bos, "US-ASCII");
	    String messageToSend = message+'\r';
	    osw.write(messageToSend);
	    osw.flush();
	    //InputStream  isr = sock.getInputStream();
	    	//* Reading Answer
	    //int carrier;
	    logger.debug("Sending message : " + message);    
//* Commented to check connection to Linux
	    	 InputStream inFromServer = sock.getInputStream();
	         DataInputStream in = new DataInputStream(inFromServer);
	         logger.debug("Server says " + in.readUTF());
	         
	    //while ( (carrier = isr.read()) != 'A') {
	    //	    logger.debug("Received=" + carrier);
	    //	    instr.append((char)carrier);	    	    
	    //	}
	    logger.debug("End of sending : " + messageToSend);
	    }
	    catch (IOException e) {logger.error("Error while sending : " + e.getMessage());}
	} 
	else {logger.error("Socket is not connected");}
    }
    
    //* Close method closing socket
    public void close() throws IOException {
	if (!this.sock.isClosed()) {
	    this.sock.close();
	    logger.error("Closing socket");
	}
    }
}
