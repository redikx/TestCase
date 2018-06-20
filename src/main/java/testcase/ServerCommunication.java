package testcase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCommunication implements Closeable {

    private Server server;
    private Socket sock = null;
    Logger logger = LoggerFactory.getLogger(ServerCommunication.class);

    // * Constructor
    public ServerCommunication(Server server) {
	super();
	this.server = server;
    }

    // * Connect method creating socket
    public void connect() throws UnknownHostException, IOException {
	this.sock = new Socket(server.getHostName(), server.getPort());
	logger.info("Connected OK to " + sock.getRemoteSocketAddress());
    }

    // * sendMessage method sending message to socket
    public void sendMessage(String message) throws IOException,BufferOverflowException,SocketException {
	StringBuilder instr = new StringBuilder();

	if (this.sock.isConnected()) {
	    // * Sending Message
	    try {
		BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
		OutputStreamWriter osw = new OutputStreamWriter(bos,"US-ASCII");
		BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());
		
		//* Header sending
		ByteBuffer obbuf = ByteBuffer.allocate(2);
		obbuf.order(ByteOrder.BIG_ENDIAN);
		short shortLe = (short) message.getBytes("US-ASCII").length;
		obbuf.putShort(shortLe);
		bos.write(obbuf.array());
		osw.write(message);
		bos.flush();
		osw.flush();
		logger.debug("Header sent " + shortLe);
		logger.debug("Sending message : " + message);
		
		
		//InputStreamReader isr = new InputStreamReader(bis,"US-ASCII");
		getInputStreamSize(bis);
		logger.debug("Server response size "); //" says " + isr.read());
		logger.debug("End of sending : " + message);
		
	    }
	    catch (SocketException s) {
	  		logger.error(s.getMessage());
	  	    }
	    catch (IOException e) {
		logger.error("Error while sending : " + e.getMessage(), e);
	    }
	} else {
	    logger.error("Socket is not connected");
	}
	
    }

    public int getInputStreamSize(BufferedInputStream is) throws IOException
    {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	logger.debug("Starting Input Stream reading");
     ByteArrayOutputStream bais = new ByteArrayOutputStream();
     	   
     	    int reads = is.read();
     	    //int reas2 = is.readAllBytes().length;
     	    //logger.debug("reas2 : " + reas2);
     	    while (reads != -1) {
     		bais.write(reads);
     		reads = is.read();
     	    }
     	    	//while ( (is.read(buf, 0, len)) != -1) {
     	    	//    System.out.println(is.toString());
     	    	//    bais.write(buf,0,len);
     	    	//    buf = bais.toByteArray();
     	    	//    len++;
     byte[] data = bais.toByteArray();
     logger.info("Input bytes : " + data.length);
     return data.length;
	}
     
    
    
    
    // * Close method closing socket
    public void close() throws IOException {
	if (!this.sock.isClosed()) {
	    this.sock.close();
	    logger.error("Closing socket");
	}
    }
}
