import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    @Test
    public void serverReceivesAndPrintsToTerminalClientMessage() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        Socket socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(socketStub);
        Server server = new Server(stdIO.getIn(), stdIO.getOut(), serverSocketStub);

        server.start();

        assertEquals(Message.clientConnected + "\n" + Message.messageFromClientIntro + "Hello\n", stdIO.getOutput());
    }

    @Test
    public void serverReceivesAndEchoesBackClientMessageToClientSocket() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        Socket socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(socketStub);
        Server server = new Server(stdIO.getIn(), stdIO.getOut(), serverSocketStub);

        server.start();

        assertEquals("Hello\n", socketIO.getOutput());
    }
}
