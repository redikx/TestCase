package testcase_Junit;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Test;

import testcase.Server;

public class ServerTest {

    @Test
    public void ifServerPortReachable() {
	Server server = new Server("127.0.0.1",31015);
	assertTrue(serverListening(server));
    }
    
    public static boolean serverListening(Server server)
    {
        Socket s = null;
        try
        {
            s = new Socket(server.getHostName(), server.getPort());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if(s != null)
                try {s.close();}
                catch(Exception e){}
        }
    }
}



