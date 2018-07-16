import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    @Test
    public void serverAcceptsClient() throws IOException {
        InputStream stdIn = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
        PrintStream stdPrint = new PrintStream(stdOut);

        InputStream dataIntoServerSocket = new ByteArrayInputStream("".getBytes());
        OutputStream dataOutOfServerSocket = new ByteArrayOutputStream();

        SocketStub socketStub = new SocketStub(dataIntoServerSocket, dataOutOfServerSocket);
        ServerSocketStub serverSocketStub = new ServerSocketStub(socketStub);
        Server server = new Server(stdIn, stdPrint, serverSocketStub);

        server.start();

        assertEquals(Message.clientConnected + "\n", stdOut.toString());
    }
}
