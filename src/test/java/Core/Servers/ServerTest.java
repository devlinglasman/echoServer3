package Core.Servers;

import Core.IOHelper;
import Core.Message;
import Core.SocketStub;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Executor;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    private final String helloWorld = "Hello World!\n";

    @Test
    public void serverAcceptsTwoClients() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIOclient1 = new IOHelper("");
        IOHelper socketIOclient2 = new IOHelper("");

        Socket clientSocket1 = new SocketStub(socketIOclient1.getIn(), socketIOclient1.getOut());
        Socket clientSocket2 = new SocketStub(socketIOclient2.getIn(), socketIOclient2.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket1, clientSocket2));
        Executor executor = new SynchronousExecutor();

        Server server = new Server(stdIO.getOut(), serverSocketStub, new ServerStatusStub(2), executor);

        server.start();

        assertEquals(Message.clientConnected() + "\n"
                + Message.clientConnected() + "\n", stdIO.getOutput());
    }

    @Test
    public void serverEchoesClientMessage() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIOclient = new IOHelper(helloWorld);

        Socket clientSocket = new SocketStub(socketIOclient.getIn(), socketIOclient.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket));
        Executor executor = new SynchronousExecutor();

        Server server = new Server(stdIO.getOut(), serverSocketStub, new ServerStatusStub(1), executor);

        server.start();

        assertEquals(Message.echoIntro + helloWorld, socketIOclient.getOutput());
    }
}
