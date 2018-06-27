package testcase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
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
		osw.flush();
		
		int IntSize = getInputStreamSize(sock);
		
		String line_in = "";
		while (bis.available() > 0 ) {
		    char c = (char) bis.read();
		    line_in += c; 
		}
		
		
		logger.debug("Server response size " + IntSize); //" says " + isr.read());
		logger.debug("Response from server : " + line_in);
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

    public int getInputStreamSize(Socket socket) throws IOException
    {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	byte[] bytes = new byte[2];
	InputStream is = socket.getInputStream();
	is.read(bytes, 0, 2) ; 
	int msglen;
	msglen=(bytes[0] & 0xff) * 256;
	msglen+=(bytes[1] & 0xff);
	return msglen;
     }    
    
    // * Close method closing socket
    public void close() throws IOException {
	if (!this.sock.isClosed()) {
	    this.sock.close();
	    logger.error("Closing socket");
	}
    }
}
