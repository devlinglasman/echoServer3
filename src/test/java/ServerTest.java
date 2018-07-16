import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    @Test
    public void serverReceivesUsernameAndClientMessageAndPrintsToTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Devlin\nHello");

        Socket socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(socketStub);
        Server server = new Server(stdIO.getIn(), stdIO.getOut(), serverSocketStub);

        server.start();

        assertEquals(Message.clientConnected("Devlin") + "\n" +
                Message.clientSays("Devlin") + "Hello\n", stdIO.getOutput());
    }

    @Test
    public void serverReceivesAndEchoesBackClientMessageToClientSocketAfterUsername() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Devlin\nHello");

        Socket socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(socketStub);
        Server server = new Server(stdIO.getIn(), stdIO.getOut(), serverSocketStub);

        server.start();

        assertEquals("Hello\n", socketIO.getOutput());
    }
}
