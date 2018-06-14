package testcase_Junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import testcase.Server;

public class ServerTest {

    @Test
    public void ifServerPortReachable() throws IOException{
	Server server = new Server("10.242.44.22",31015);
	assertTrue(serverListening(server));
    }
    
    public static boolean serverListening(Server server) throws IOException
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



