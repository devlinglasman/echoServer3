import Core.Message;
import Core.Server;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    @Test
    public void serverReceivesClientMessageAndPrintsToTerminal() throws IOException {
        IOHelper stdIOclient1 = new IOHelper("");
        IOHelper socketIOclient1 = new IOHelper("Hello");

        Socket clientSocket1 = new SocketStub(socketIOclient1.getIn(), socketIOclient1.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket1));
        new Server(stdIOclient1.getOut(), serverSocketStub);

        assertEquals(Message.clientConnected() + "\n" + "Hello\n", stdIOclient1.getOutput());
    }

    @Test
    public void serverReceivesAndEchoesBackClientMessageToClientSocket() throws IOException {
        IOHelper stdIOclient1 = new IOHelper("");
        IOHelper socketIOclient1 = new IOHelper("Hello");

        Socket clientSocket1 = new SocketStub(socketIOclient1.getIn(), socketIOclient1.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket1));
        new Server(stdIOclient1.getOut(), serverSocketStub);

        assertEquals("Hello\n", socketIOclient1.getOutput());
    }

    @Test
    public void addClient() throws IOException {
        IOHelper stdIOclient1 = new IOHelper("");
        IOHelper socketIOclient1 = new IOHelper("");

        IOHelper stdIOclient2 = new IOHelper("");
        IOHelper socketIOclient2 = new IOHelper("");

        Socket clientSocket1 = new SocketStub(socketIOclient2.getIn(), socketIOclient2.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket1));
        Server server = new Server(stdIOclient2.getOut(), serverSocketStub);



        assertEquals("Hello\n", socketIOclient2.getOutput());
    }
//    @Test
//    public void serverAcceptsTwoClients() throws IOException {
//        IOHelper socketIOclient1 = new IOHelper("");
//        Socket clientSocket1 = new SocketStub(socketIOclient1.getIn(), socketIOclient1.getOut());
//
//        IOHelper socketIOclient2 = new IOHelper("");
//        Socket clientSocket2 = new SocketStub(socketIOclient2.getIn(), socketIOclient2.getOut());
//
//        List<Socket> clientSockets = Arrays.asList(clientSocket1, clientSocket2);
//
//        ServerSocket serverSocketStub = new ServerSocketStub(clientSockets);
//        IOHelper stdIO = new IOHelper("");
//        new Core.Server(stdIO.getOut(), serverSocketStub);
//
//        assertEquals(Core.Message.clientConnected() + "\n"
//                + Core.Message.clientConnected() + "\n", stdIO.getOutput());
//    }
}
