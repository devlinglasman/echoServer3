import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    private InputStream stdIn;
    private ByteArrayOutputStream stdOut;
    private PrintStream stdPrint;

    private InputStream dataIntoServerSocket;
    private String serverSocketMessageIn;
    private OutputStream dataOutOfServerSocket = new ByteArrayOutputStream();

    private SocketStub socketStub;
    private ServerSocketStub serverSocketStub;
    private Server server;

    public ServerTest() throws IOException {
    }

    public void setup(String stdInMessage, String clientSocketMessageIn) throws IOException {
        stdIn = new ByteArrayInputStream(stdInMessage.getBytes());
        stdOut = new ByteArrayOutputStream();
        stdPrint = new PrintStream(stdOut);

        dataIntoServerSocket = new ByteArrayInputStream(clientSocketMessageIn.getBytes());
        dataOutOfServerSocket = new ByteArrayOutputStream();

        socketStub = new SocketStub(dataIntoServerSocket, dataOutOfServerSocket);
        serverSocketStub = new ServerSocketStub(socketStub);
        server = new Server(stdIn, stdPrint, serverSocketStub);
    }


    @Test
    public void serverAcceptsClient_printsToTerminal() throws IOException {
        setup("","");
        server.start();

        assertEquals(Message.clientConnected + "\n", stdOut.toString());
    }

    @Test
    public void serverAcceptsClient_sendsConfirmationToClient() throws IOException {
        setup("","");
        server.start();

        assertEquals(Message.clientConnected + "\n", server.getClientSocket().getOutputStream().toString());
    }

    @Test
    public void serverReceivesAndPrintsClientMessage() throws IOException {
        setup("","Hello");
        server.start();

        server.receiveClientMessage();

        assertEquals(Message.clientConnected + "\nHello\n", stdOut.toString());
    }
}
