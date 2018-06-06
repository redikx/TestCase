package testcase;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCommunication {

    private Server server;
    Logger logger = LoggerFactory.getLogger(TestCase.class);

    public ServerCommunication(Server server) {
	super();
	this.server = server;
    }

    public void SendMessage(String message) throws IOException {
	Socket socket = null;
	try {
	    socket = new Socket(server.getHostName(), server.getPort());
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    out.println(message);
	} catch (IOException e) {
	    logger.error(e.getMessage());
	} finally {
	    socket.close();
	}
    }
}
