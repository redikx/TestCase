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
<<<<<<< HEAD
    private Socket sock = null;
=======
<<<<<<< HEAD
    private Socket sock = null;
=======
>>>>>>> 2e8c80dc26338d67864a021e4d640948371c58c6
>>>>>>> eb9d5e3e3fbc87b4774469ca2a0ae9529da21559
    Logger logger = LoggerFactory.getLogger(ServerCommunication.class);

    //* Constructor
    public ServerCommunication(Server server){
	super();
	this.server = server;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> eb9d5e3e3fbc87b4774469ca2a0ae9529da21559
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
<<<<<<< HEAD
=======
	    OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
	    String messageToSend = message + '\r';
	    osw.write(messageToSend);
	    osw.flush();
	    InputStream  isr = sock.getInputStream();
	    	//* Reading Answer
	    int carrier;
	    logger.debug("Reading answer" );    
//* Commented to check connection to Linux
	    	 //InputStream inFromServer = sock.getInputStream();
	         //DataInputStream in = new DataInputStream(inFromServer);
	         //logger.info("Server says " + in.readUTF());
	         
	    while ( (carrier = isr.read()) != 'A') {
	    	    logger.debug("Received=" + carrier);
	    	    instr.append((char)carrier);	    	    
	    	}
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
=======
    public void SendMessage(String message) throws UnknownHostException, IOException  {
	Socket socket = new Socket(server.getHostName(),server.getPort());
	StringBuffer instr = new StringBuffer();
	logger.info("SocketClient initialized for " + message);
	try {
	    BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
>>>>>>> eb9d5e3e3fbc87b4774469ca2a0ae9529da21559
	    OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
	    String messageToSend = message + '\r';
	    osw.write(messageToSend);
	    osw.flush();
<<<<<<< HEAD
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
=======
	    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
	    InputStreamReader isr = new InputStreamReader(bis, "US-ASCII");
	    int carrier;
	    	while ( (carrier = isr.read()) != 13) instr.append((char)carrier);
	    	socket.close();
	}
	
	catch (IOException f) {logger.error(f.getMessage());}
	catch (Exception e) {logger.error(e.getMessage());}
	finally { }
>>>>>>> 2e8c80dc26338d67864a021e4d640948371c58c6
>>>>>>> eb9d5e3e3fbc87b4774469ca2a0ae9529da21559
	}
    }
}
