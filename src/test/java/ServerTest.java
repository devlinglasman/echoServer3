import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    private InputStream stdIn = new ByteArrayInputStream("".getBytes());
    private ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
    private PrintStream stdPrint = new PrintStream(stdOut);

    private InputStream dataIntoServerSocket = new ByteArrayInputStream("".getBytes());
    private OutputStream dataOutOfServerSocket = new ByteArrayOutputStream();

    private SocketStub socketStub = new SocketStub(dataIntoServerSocket, dataOutOfServerSocket);
    private ServerSocketStub serverSocketStub = new ServerSocketStub(socketStub);
    private Server server = new Server(stdIn, stdPrint, serverSocketStub);

    public ServerTest() throws IOException {
    }

    @Before
    public void setup() throws IOException {
        stdIn = new ByteArrayInputStream("".getBytes());
        stdOut = new ByteArrayOutputStream();
        stdPrint = new PrintStream(stdOut);

        dataIntoServerSocket = new ByteArrayInputStream("".getBytes());
        dataOutOfServerSocket = new ByteArrayOutputStream();

        socketStub = new SocketStub(dataIntoServerSocket, dataOutOfServerSocket);
        serverSocketStub = new ServerSocketStub(socketStub);
        server = new Server(stdIn, stdPrint, serverSocketStub);
    }


    @Test
    public void serverAcceptsClient_printsToTerminal() {
        server.start();

        assertEquals(Message.clientConnected + "\n", stdOut.toString());
    }

    @Test
    public void serverAcceptsClient_sendsConfirmationToClient() throws IOException {
        server.start();

        assertEquals(Message.clientConnected + "\n", server.getClientSocket().getOutputStream().toString());
    }
}
